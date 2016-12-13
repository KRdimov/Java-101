package life;

public class Cell {
	private boolean isAlive;
	private char symbol;
	
	public Cell(boolean isAlive, char symbol) {
		setStatus(isAlive);
		setSymbol(symbol);
	}
	
	public void setStatus(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public boolean isAlive() {
		return this.isAlive;
	}
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	public char getSymbol() {
		return this.symbol;
	}
	
	public String toString() {
		return symbol + "";
	}
}
