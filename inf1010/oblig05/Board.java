class Board {
	// Variables on size
	private int boxHeight;
	private int boxLength;
	private int boardSize;

	// Arrays for board setup
	protected Square[][] squareArray;
	private Row[] rowArray;
	private Column[] columnArray;
	private Box[] boxArray;

	// Container for solutions
	private SudokuContainer container;


	Board(int boxHeight, int boxLength) {
		// initialize container
		this.container = container;

		// initialize sizes
		this.boxHeight = boxHeight;
		this.boxLength = boxLength;
		boardSize = boxHeight * boxLength;

		// initialize board setup arrays
		squareArray = new Square[boardSize][boardSize];
		rowArray = new Row[boardSize];
		columnArray = new Column[boardSize];
		boxArray = new Box[boardSize];

		// initialize square collections
		for (int i = 0; i < boardSize; i++) {
			rowArray[i] = new Row(boardSize);
			columnArray[i] = new Column(boardSize);
			boxArray[i] = new Box(boardSize);
		}
	} // end Constructor

	public void setContainer(SudokuContainer container) {
		this.container = container;
	} // end setContainer

	public void putSquare(int xPos, int yPos, Square square) {
		// add square to the board
		squareArray[yPos][xPos] = square;

		// update used values in rows, columns and boxes
		rowArray[yPos].putNum(square.getValue());
		columnArray[xPos].putNum(square.getValue());
		int boxNr = findBox(xPos, yPos);
		boxArray[boxNr].putNum(square.getValue());		

		// send row, column and box to square
		square.setCollections(rowArray[yPos], columnArray[xPos], boxArray[boxNr], this);
	} // end putSquare()

	public void linkSquares() {
		int end = boardSize-1;
		// start with the square in the top left corner
		Square currSquare = squareArray[end][end];

		// iterate through the board and link the squares
		for (int yPos = end; yPos != -1; yPos--) {
			for (int xPos = end; xPos != -1; xPos--) {
				currSquare.next = squareArray[yPos][xPos];
				currSquare = currSquare.next;
			}
		}
	} // end linkSquares()

	public int findBox(int xPos, int yPos) {
		// (squareRow, squareCol) are 0-based box-coordinates
		int squareRow = yPos/boxHeight;
		int squareCol = xPos/boxLength;
		
		// numSquareCols is the number of squares per squareRow
		int numSqaureCols = boardSize/boxLength;

		// boxes are numbered from 0 (top left) to n (bottom right) horizontally
		return squareCol + (squareRow * numSqaureCols);
	} // end findBox()

	public void solve() {
		squareArray[boardSize-1][boardSize-1].fillRestOfBoard();
	} // end solve()

	public void saveSolution() {
		container.insert(this);
		// container.insert(new Solution(this));
	} // end saveSolution()
	
	public Square getSquare(int xPos, int yPos) {
		return squareArray[yPos][xPos];
	} // end getSquare()

	public int getSize() {
		return boardSize;
	} // end getSize()

	public int getBoxHeight() {
		return boxHeight;
	} // end getBoxHeight()
	
	public int getBoxLength() {
		return boxLength;
	} // end getBoxLength()

	public SudokuContainer getSolutions() {
		return container;
	} // end getSolutions()
} // end Board
