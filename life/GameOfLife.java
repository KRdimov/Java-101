package life;

import java.util.Scanner;

public class GameOfLife {
	private static Grid grid;
//	public static void main(String[] args) throws InterruptedException {
//		initializeGame();
//		startGame();
//	}

	private static void startGame() throws InterruptedException {
		while(true) {
			System.out.println(grid);
			grid.age();
			Thread.sleep(1000);
		}
	}

	private static void initializeGame() {
		Scanner sc = new Scanner(System.in);
		int size = Integer.parseInt(sc.nextLine());
		grid = new Grid(size);
		
		int n = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < n; i++) {
			String position = sc.nextLine();
			String[] strXY = position.split(" ");
			int x = Integer.parseInt(strXY[0]);
			int y = Integer.parseInt(strXY[1]);
			grid.setAlive(x, y);
		}
	}
}
