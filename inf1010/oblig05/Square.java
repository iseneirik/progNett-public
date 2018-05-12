abstract class Square {
	// Hold the value of the square
	protected int value;

	// Next square to fill in
	protected Square next;

	// Board-data
	protected Row row;
	protected Column column;
	protected Box box;
	protected Board board;

	
	Square(int value) {
		this.value = value;
	} // end Constructor

	public int getValue() {
		return value;
	} // end getValue()

	public void setNext(Square square) {
		next = square;
	} // end setNext()

	public void setCollections(Row row, Column column, Box box, Board board) {
		this.row = row;
		this.column = column;
		this.box = box;
		this.board = board;
	} // end setCollections()

	public boolean checkValue(int value) {
		// check if value is already used in a collection
		if (!row.numUsed(value) && !column.numUsed(value) && !box.numUsed(value)) {
				return true;
		} 
		// if it is, return false
		return false;
	} // end checkValue()

	public void tryValue(int value) {
		// if value is to be changed, undo in collections
		if (this.value != 0) {
			row.removeNum(this.value);
			column.removeNum(this.value);
			box.removeNum(this.value);
		}

		// update value
		this.value = value;

		// update collections unless it is a reset
		if (value != 0) {	
			row.putNum(value);
			column.putNum(value);
			box.putNum(value);
		}
	} // end tryValue()

	abstract void fillRestOfBoard();
} // end Square


class PredefinedSquare extends Square {
	
	PredefinedSquare(int value) {
		super(value);
	} // end Constructor

	public void fillRestOfBoard() {
		if (next != null) {
			next.fillRestOfBoard();
		} else {
			board.saveSolution();
		}
	} // end fillRestOfBoard()
} // end PredefinedSquare


class UndefinedSquare extends Square {
	
	UndefinedSquare(int value) {
		super(value);
	} // end Constructor

	public void setValue(int value) {
		this.value = value;
	} // end setValue

	public void fillRestOfBoard() {
		for (int value = 1; value <= box.taken.length; value++) {
			if (checkValue(value)) {
				tryValue(value);
				// continue unless end of board is reached
				if (next != null) {	
					next.fillRestOfBoard(); 
				} else {
					board.saveSolution();
				}
			} 
		}
		// if no values fit, reset value and go back a step
		tryValue(0);
	} // end fillRestOfBoard()
} // end UndefinedSquare