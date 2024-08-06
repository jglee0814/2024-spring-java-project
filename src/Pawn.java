
public class Pawn {
	
	// Stores index in space button
	private int row;
	private int col;
	private int idx;
	public int fenceNum; // maximum 4, remaining number of fence
	private String name;  // Name of the pawn - P1, P2, P3, P4
	
	public Pawn(int row, int col, int idx) {
		super();
		this.row = row;
		this.col = col;
		this.idx = idx;
		this.fenceNum = 4;
		this.name = "P" + (idx + 1);
	}
	
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	

}
