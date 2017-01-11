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
			Command command = commands.get(cm);
			if(command == null) {
				command = commands.get("help");
			}
			command.run();
		} while (!cm.equals("exit"));
	}

	private static void init() {
		commands = new HashMap<>();
		scanner = new Scanner(System.in);
		db = new DataBase("localhost/reservation_system", "usr-name", "pass"); // Danger!! TODO
		fillCommands();
	}

	private static void fillCommands() {
		commands.put("exit",  new Command() {
			
            public void run() {
            	System.out.println("Bye!");
            }
            
            public String showDescription() {
            	return "shuts down the programm";
            }
        });
		
		commands.put("help", new Command() {
			
            public void run() {
            	for (Map.Entry<String, Command> command : commands.entrySet()) {
					System.out.println(command.getKey() + " - " + command.getValue().showDescription());
				}
            }
            
            public String showDescription() {
            	return "prints all commands and their functionalities";
            }
        });
		
		commands.put("show movies", new Command() {
			
            public void run() {
            	System.out.println(db.selectFrom("Movies", "rating", false));
            }
            
            public String showDescription() {
            	return "prints all movies ordered by rating";
            }
        });
		
		//TODO
	}
}
