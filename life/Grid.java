package life;

public class Grid {
	private Cell[][] grid;
	private final int size;
	private int generation;
	
	public Grid(int size) {
		this.size = size;
		generation = 0;
		initialize();
	}
	
	private void initialize() {
		this.grid = new Cell[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.grid[i][j] = new Cell(false, '□');
			}
		}
	}

	public void setAlive(int i, int j) {
		this.grid[i][j].setStatus(true);
		this.grid[i][j].setSymbol('■');
	}

	public void age() {
		Grid tempGrid = new Grid(size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(getsToLive(grid[i][j], i, j)) {
					tempGrid.setAlive(i, j);
				}
			}
		}
		grid = tempGrid.grid;
		generation++;
	}
	
	private int getAliveNeighboursCount(int i, int j) {
		int count = 0;
		for (int row = i - 1; row <= i + 1; row++) {
			for (int col = j - 1; col <= j + 1; col++) {
				boolean isInGrid = row >= 0 && row < size && col >= 0 && col < size;
				if(!(row == i && col == j) && isInGrid) {
					if(grid[row][col].isAlive())
						count++;
				}
			}
		}
		return count;
	}

	private boolean getsToLive(Cell cell, int i, int j) {
		int aliveNeighbours = getAliveNeighboursCount(i, j);
		if(cell.isAlive()) {
			if(aliveNeighbours == 2 || aliveNeighbours == 3)
				return true;
			
			return false;
		} else {
			if(aliveNeighbours == 3)
				return true;
			
			return false;
		}
	}

	public String toString() {
		String output = "Generation: " + generation + "\n";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				output += grid[i][j] + " ";
			}
			output += "\n";
		}
		return output;
	}
}
