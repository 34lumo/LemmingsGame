package tp1.logic;

import java.util.Objects;

/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */

public class Position {

	private int col;
	private int row;
	
	//CONSTRUCTORA
	public Position(int col, int row) {
		this.col = col;
		this.row = row;
	}
	
	
	//METODOS	
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
	
	public Position move(Direction d) {
		return new Position(this.col+d.getX(), this.row+d.getY());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(col, row);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		return col == other.col && row == other.row;
	}
	
	public boolean isInBoard() {
		boolean esta = false;
		if(this.col >= 0 && this.col < Game.DIM_X && this.row >= 0 && this.row < Game.DIM_Y) {
			esta = true;
		}
		return esta;
	}

}
