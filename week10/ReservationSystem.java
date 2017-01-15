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
			String[] commandWithArgs = parseCommand(cm);
			Command command = commands.get(commandWithArgs[0]);
			if(command == null) {
				command = commands.get("help");
				command.run();
				continue;
			}
			
			command.run(commandWithArgs[1].trim());
		} while (!cm.equals("exit"));
	}

	private static String[] parseCommand(String cm) {
		for (String command : commands.keySet()) {
			if(cm.startsWith(command)) {
				String[] commandWithArgs = new String[] {
					command, cm.substring(command.length(), cm.length())	
				};
				return commandWithArgs;
			}
		}
		return new String[] {null};
	}

	private static void init() {
		commands = new HashMap<>();
		scanner = new Scanner(System.in);
		db = new DataBase("localhost/reservation_system", "root", "pass"); 
		CommandManager.fillCommands(commands, db, scanner);
	}
}
