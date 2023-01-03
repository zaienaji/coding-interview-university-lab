package id.zaien.lab;

public class Board {
	
	private final int x;
	private final int y;
	private final int[] queen;
	
	public Board(int x, int y) {
		
		this.x = x;
		this.y = y;
		
		this.queen = new int[x];
	}
	
	public int columnSize() {
		return this.x;
	}
	
	public int rowSize() {
		return this.y;
	}
	
	public void addQueen(int x, int position) {
		
		this.queen[x-1] = position;
	}
	
	public boolean isSavePosition(int x, int position) {
		
		return isNoQueenInColumn(x) && isNoQueenInRow(position) && isNoQueenInDiagonal(x, position);
	}

	private boolean isNoQueenInDiagonal(int x, int y) {
		
		return isNoQueenInDecliningDiagonal(x, y) && isNoQueenInUprisingDiagonal(x, y);
	}

	private boolean isNoQueenInUprisingDiagonal(int x, int y) {
		
		return trackToTopRight(x, y) && trackToBottomLeft(x, y);
	}

	private boolean trackToBottomLeft(int x, int y) {
		
		if (x<=0 || y<=0)
			return true;
		
		if (this.queen[x-1]==y)
			return false;
		
		return trackToBottomLeft(x-1, y-1);
	}

	private boolean trackToTopRight(int x, int y) {
		if (x>this.x || y>this.y)
			return true;
		
		if (this.queen[x-1]==y)
			return false;
		
		return trackToTopRight(x+1, y+1);
	}

	private boolean isNoQueenInDecliningDiagonal(int x, int y) {
		
		return trackToTopLeft(x, y) && trackToBottomRight(x, y);
	}

	private boolean trackToBottomRight(int x, int y) {

		if (x>this.x || y<=0)
			return true;
		
		if (this.queen[x-1]==y)
			return false;

		return trackToBottomRight(x+1, y-1);
	}

	private boolean trackToTopLeft(int x, int y) {
		
		if (x<=0 || y>this.y)
			return true;
		
		if (this.queen[x-1]==y)
			return false;
		
		return trackToTopLeft(x-1, y+1);
	}

	private boolean isNoQueenInRow(int position) {
		for (int i : queen) {
			if (i==position)
				return false;
		}
		
		return true;
	}

	private boolean isNoQueenInColumn(int x) {
		return this.queen[x-1]==0;
	}

	public void removeQueen(int x) {	
		this.queen[x-1] = 0;
	}
	
	public void print() {
		for (int i=0; i<this.queen.length; i++) {
			System.out.println(String.format("{%d}: {%d}", i+1, this.queen[i]));
		}
	}
	
	public int[] result() {
		return this.queen;
	}

}
