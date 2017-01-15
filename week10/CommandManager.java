package week10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CommandManager {
	public static void fillCommands(Map<String, Command> commands, DataBase db, Scanner scanner) {
		commands.put("exit",  new Command() {

			@Override
            public void run(String... i) {
            	System.out.println("Bye!");
            }
            
            public String showDescription() {
            	return "shuts down the programm";
            }
        });
		
		commands.put("help", new Command() {

			@Override
            public void run(String... i) {
            	for (Map.Entry<String, Command> command : commands.entrySet()) {
					System.out.println(command.getKey() + " - " + command.getValue().showDescription());
				}
            }
            
            public String showDescription() {
            	return "prints all commands and their functionalities";
            }
        });
		
		commands.put("show movies", new Command() {

			@Override
            public void run(String... i) {
            	System.out.println(db.selectFrom("Movie", "rating", false));
            }
            
            public String showDescription() {
            	return "prints all movies ordered by rating";
            }
        });
		
		commands.put("show movie projections", new Command() {
			private int movieId;
			private String date;

			@Override
            public void run(String...args) {
				setIdAndDate(args);
				if(movieId != -1){
					if(date != null) {
						System.out.println(db.selectFrom("Projection", "movie_id=" + movieId + " AND date='" + date + "'", "date", true));
					} else {				
						System.out.println(db.selectFrom("Projection", "movie_id=" + movieId, "date", true));		
					}
				} else {
					Command cm = commands.get("help");
					cm.run();
				}
            }

			private void setIdAndDate(String[] args) {
				movieId = -1;
				date = null;
				Integer id = new Integer(-1);
				if(args == null) {
					movieId =  id.intValue();
					return;
				}
				String[] commandArgs = args[0].split(" ");
				try {
					id = Integer.parseInt(commandArgs[0]);
				} catch (NumberFormatException e) {
					movieId = id.intValue();
					return;
				}
				movieId = id.intValue();
				if(commandArgs.length > 1 && commandArgs[1] != null) {
					date = commandArgs[1];
				}
			}

			@Override
            public String showDescription() {
            	return "prints all movie projections or all movie projections on a certain date. "
            			+ "Example:\n show movie projections <movieId> [date] (Note: date is not obligatory and date format is 'yyyy-mm-dd')";
            }
        });
		
		commands.put("cancel reservation", new Command() {

			@Override
            public void run(String... args) {
				Command cm = commands.get("help");
				if(args[0].equals("") || args[0] == null){
					cm.run();
					return;
				}
				String username = args[0];
				if(!db.deleteFrom("Reservation", username)){
					cm.run();
				}
            }
            
            public String showDescription() {
            	return "cancels the reservation of the given person";
            }
        });
		
		commands.put("show reservations", new Command() {

			@Override
            public void run(String... args) {
				System.out.println(db.selectFrom("Reservation"));
            }
            
            public String showDescription() {
            	return "shows all made reservations";
            }
        });
		
		commands.put("make reservation", new Command() {
			private String username;
			private int tickets;
			private int movieId;
			private int projectionId;
			private boolean errorOccured;
			private char[][] seats;
			private int takenSeats;
			private List<Integer> rowsChoosen;
			private List<Integer> colsChoosen;
			boolean givenUp;

			@Override
            public void run(String... args) {
				initCommandState();
				do {
					//Step 1
					chooseName();
					if(checkIfGivenUp(username)) {
						System.out.println("Giving up...");
						return;
					}
					chooseTicketsNumber();
					if(givenUp) {
						System.out.println("Giving up...");
						return;
					}
				} while (errorOccured);
				do {
					//Step 2
					chooseMovieId();
					if(givenUp) {
						System.out.println("Giving up...");
						return;
					}
				} while (errorOccured);
				do {
					//Step 3 part 1
					chooseProjectionId();
					if(givenUp) {
						System.out.println("Giving up...");
						return;
					}
				} while (errorOccured);
				do {
					//Step 3 part 2
					checkIfProjectionHasEnoughSeats();
					if(errorOccured) {
						chooseTicketsNumber();
						if(givenUp) {
							System.out.println("Giving up...");
							return;
						}
					}
				} while (errorOccured);
				
				//Step 4
				showAllSeats();
				
				//Step 5
				chooseSeats();
				if(givenUp) {
					System.out.println("Giving up...");
					return;
				}
				
				//Step 6
				showAllInfo();
				
				//Step 7
				promptForFinalization();
				if(givenUp) {
					System.out.println("Giving up...");
					return;
				}
				
            }
            
            private void initCommandState() {
            	errorOccured = false;
    			seats = new char[5][5];
    			takenSeats = 0;
    			rowsChoosen = new ArrayList<>();
    			colsChoosen = new ArrayList<>();
    			givenUp = false;
			}

			private boolean checkIfGivenUp(String input) {
				return input.equals("give up");
			}

			private void promptForFinalization() {
            	System.out.println("Write 'finalize' to confirm the reservation");
            	do {
                	String input = scanner.nextLine();
                	if(!input.equals("finalize")) {
                		if(checkIfGivenUp(input)) {
                			givenUp = true;
                			return;
                		}
                		System.out.println("Invalid input, 'finalize' expected.");
                		errorOccured = true;
                	} else {
                		errorOccured = false;
                	}
				} while (errorOccured);
            	saveReservation();
			}

			private void saveReservation() {
				Map<String, String> columnsValues = new HashMap<>();
				for (int i = 0; i < rowsChoosen.size(); i++) {
					columnsValues.put("username", "'" + username + "'");
					columnsValues.put("projection_id", projectionId + "");
					columnsValues.put("row", rowsChoosen.get(i) + "");
					columnsValues.put("col", colsChoosen.get(i) + "");
					db.insertInto("Reservation", columnsValues);
					columnsValues = new HashMap<>();
				}
			}

			private void showAllInfo() {
				String output = "Username: " + username + "\n";
				output += "Movie: " + db.selectFrom("Movie", "id=" + movieId, "rating", true) + "\n";
				output += "Projection: " + db.selectFrom("Projection", "id=" + projectionId, "id", true) + "\n";
				for (int i = 0; i < rowsChoosen.size(); i++) {
					output += "Seat: (" + (rowsChoosen.get(i) + 1) + ", " + (colsChoosen.get(i) + 1) + ")\n";
				}
				showAllSeats();
				System.out.println(output);
			}

			private void chooseSeats() {
				for (int i = 0; i < tickets; i++) {
					Integer row;
					Integer col;
					do {
						System.out.println("Choose row: ");
						row = choosePlace() - 1;
						if(givenUp) {
							return;
						}
						System.out.println("Choose col: ");
						col = choosePlace() - 1;
						if(givenUp) {
							return;
						}
						boolean insideSeatsMatrix = (row >= 0 && row < seats.length) && (col >= 0 && col < seats.length);
						boolean isNotTaken = false;
						if(insideSeatsMatrix) {
							isNotTaken = seats[row][col] != 'X';
						}
						if(insideSeatsMatrix && isNotTaken) {
							rowsChoosen.add(row);
							colsChoosen.add(col);
							errorOccured = false;
						} else {
							errorOccured = true;
						}
					} while (errorOccured);
					fillChosenSeats();
				}
			}

			private void fillChosenSeats() {
				for (int i = 0; i < rowsChoosen.size(); i++) {
					seats[rowsChoosen.get(i)][colsChoosen.get(i)] = '#';
				}
			}

			private Integer choosePlace() {
				Integer place = new Integer(-1);
				String input = "";
				do {
					try {
						input = scanner.nextLine();
						place = Integer.parseInt(input);
						errorOccured = false;
					} catch (NumberFormatException e) {
						if(checkIfGivenUp(input)){
							givenUp = true;
							return place;
						} else {
							System.out.println("Invalid place input.");
							errorOccured = true;
						}
					}
				} while (errorOccured);
				return place;
			}

			private void showAllSeats() {
				for (int i = 0; i < seats.length; i++) {
					for (int j = 0; j < seats.length; j++) {
						System.out.print(seats[i][j]);
					}
					System.out.println();
				}
			}

			private void checkIfProjectionHasEnoughSeats() {
				List<List<String>> reservations = db.selectFrom("Reservation", "projection_id='" + projectionId + "'", "projection_id", true);
				setEmptySeats();
				for (List<String> reservation : reservations) {
					int row = Integer.parseInt(reservation.get(reservation.size() - 2));
					int col = Integer.parseInt(reservation.get(reservation.size() - 1));
					seats[row][col] = 'X';
					takenSeats ++;
				}
				if(seats.length * seats.length < takenSeats + tickets) {
					System.out.println("The projection has not enough tickets left. Sorry.");
					errorOccured = true;
				} else {
					errorOccured = false;
				}
			}

			private void setEmptySeats() {
				for (int i = 0; i < seats.length; i++) {
					for (int j = 0; j < seats.length; j++) {
						seats[i][j] = '.';
					}
				}
			}

			private void chooseProjectionId() {
				Command showMovies = commands.get("show movie projections");
				showMovies.run(movieId + "");
				String input = "";
				System.out.println("Choose projection id: ");
				try {
					input = scanner.nextLine();
					projectionId = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					if(checkIfGivenUp(input)) {
						givenUp = true;
					} else {
						System.out.println("Invalid projection Id. Aborting...");
						errorOccured = true;
						return;
					}
				}
				errorOccured = false;
			}

			private void chooseMovieId() {
				Command showMovies = commands.get("show movies");
				showMovies.run();
				String input = "";
				System.out.println("Choose movie id: ");
				try {
					input = scanner.nextLine();
					movieId = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					if(checkIfGivenUp(input)) {
						givenUp = true;
					} else {
						System.out.println("Invalid movie id input.");
						errorOccured = true;
						return;
					}
					System.out.println("Invalid movie Id. Aborting...");
					errorOccured = true;
					return;
				}
				errorOccured = false;
			}

			private void chooseName() {
				System.out.println("Choose name for reservation: ");
				username = scanner.nextLine();
				if(username.isEmpty()){
					System.out.println("Invalid name.");
					errorOccured = true;
					return;
				}
				errorOccured = false;
			}
			
			private void chooseTicketsNumber() {
				System.out.println("Choose how many tickets: ");
				String input = "";
				try {
					input = scanner.nextLine();
					tickets = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					if(checkIfGivenUp(input)) {
						givenUp = true;
					} else {
						System.out.println("Invalid tickets number input.");
						errorOccured = true;
						return;
					}
				}
				errorOccured = false;
			}
			
			@Override
			public String showDescription() {
            	return "shows all made reservations";
            }
        });
	}
}
