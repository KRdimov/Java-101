package week10;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReservationSystem {
	private static Map<String, Command> commands;
	private static Scanner scanner;
	private static DataBase db;
	
	public static void main(String[] args) {
		init();
		runSystem();
	}

	private static void runSystem() {
		String cm;
		do {
			cm = scanner.nextLine();
			Command command = parseCommand(cm);
			if(command == null) {
				command = commands.get("help");
				command.run();
				continue;
			}
			String[] args = getArguments(cm);
			
			
			command.run();
		} while (!cm.equals("exit"));
	}

	private static Command parseCommand(String cm) {
		for (Map.Entry<String, Command> command : commands.entrySet()) {
			String strCmd = command.getKey();
			if(cm.contains(strCmd)) {
				return command.getValue();
			}
		}
		return null;
	}

	private static void init() {
		commands = new HashMap<>();
		scanner = new Scanner(System.in);
		db = new DataBase("localhost/reservation_system", "usr-name", "pass"); // Danger!! TODO
		fillCommands();
	}

	private static void fillCommands() {
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
            	System.out.println(db.selectFrom("Movies", "rating", false));
            }
            
            public String showDescription() {
            	return "prints all movies ordered by rating";
            }
        });
		
		commands.put("show movie projections", new Command() {

			@Override
            public void run(String...args) {
            	System.out.println(db.selectFrom("Movies", "rating", false));
            }

			@Override
            public String showDescription() {
            	return "prints all movies ordered by rating";
            }
        });
		
		//TODO
	}
}
