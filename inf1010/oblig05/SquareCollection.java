abstract class SquareCollection {
	protected boolean[] taken;

	SquareCollection(int size) {
		taken = new boolean[size];
	} // end Constructor

	public boolean numUsed(int number) {
		return taken[number-1];
	} // end numTaken()

	public void putNum(int number) {
		if (number != 0) {
			taken[number-1] = true;
		}
	} // end putNum

	public void removeNum(int number) {
		taken[number-1] = false;
	} // end removeNum()
} // end SquareCollection


class Box extends SquareCollection {
	Box(int size) {
		super(size);
	} // end Constructor
} // end Box


class Column extends SquareCollection {
	Column(int size) {
		super(size);
	} // end Constructor
} // end Column


class Row extends SquareCollection {
	Row(int size) {
		super(size);
	} // end Constructor
} // end Row