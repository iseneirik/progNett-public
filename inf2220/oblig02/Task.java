import java.util.LinkedList;


class Task {
	// Status of the task when checking for loops
	private final int FINISHED = 0;
	private final int NOT_VISITED = -1;
	private final int IN_PROCESS = -2;
	private int status;

	// Values initialized by constructor
	int id, time, staff;
	String name;
	int[] dependencies;
	int numPredecessors;
	boolean collected;
	
	// Other info
	LinkedList<Task> children;
	int earliestStart, latestStart;

	// Statics for total time and free staff
	private static int totalTime;
	private static int totalStaff;
	private static int freeStaff;

	Task(String data) {
		String[] values = data.split("[\\s]+");

		// Values provided by the input file
		id = Integer.parseInt(values[0]);
		name = values[1];
		time = Integer.parseInt(values[2]);
		staff = Integer.parseInt(values[3]);

		// The remaining values are dependencies
		numPredecessors = values.length - 5;
		dependencies = new int[numPredecessors];
		for (int i = 4; (i-4) < dependencies.length; i++) 
			dependencies[i-4] = Integer.parseInt(values[i]);

		// Various status variables
		children = new LinkedList<Task>();
		earliestStart = -1;
		status = NOT_VISITED;
		collected = false;
	} // end Constructor()

	// Adds itself as a child to it's dependencies
	public boolean addChild(Task child) {
		return children.add(child);
	} // end addChild()

	public void calcEST(){
		calcEST(0);
	} // end calcEST()

	// Recursively calculates the earliest startTime of tasks
	public void calcEST(int startTime) {
		if (startTime > earliestStart) earliestStart = startTime;
		else return; // Interrupt recursion if longer path is not found
		for (Task t : children) t.calcEST(earliestStart + time);
		if (earliestStart + time > totalTime) totalTime = earliestStart + time;
	} // end calcEST()

	// Recursively calulates the latest startTime of tasks
	public int calcLST() {
		latestStart = totalTime-time;
		for (Task t : children) {
			int latest = t.calcLST() - time;
			if (latest < latestStart) latestStart = latest;
		}
		return latestStart;
	} // end calcLST()

	// Recursively checks the graph for loops 
	// if a loop is found, return id of cause
	public int findLoop() {
		if (status == IN_PROCESS) return id;
		if (status == FINISHED) return FINISHED;
		for (Task t : children) {
			status = IN_PROCESS;
			status = t.findLoop();
			if (status > FINISHED) return status;
		}
		return FINISHED;
	} // end findLoop()

	public int getTotal() {
		return totalTime;
	} // end getTotal()

	public String childrenString() {
		String s = "";
		for (Task t : children) {
			s += t.id + "  ";
		}
		if (s.length() == 0) {
			return "none";
		}
		return s;
	} // end childrenString()

	public void setStaff(int manpower) {
		if (manpower == 0) {
			totalStaff = Integer.MAX_VALUE;
		} else {
			totalStaff = manpower;
		}
		freeStaff = totalStaff;
	} // end setMax()

	public boolean start() {
		if (staff > totalStaff) {
			System.out.printf("\nTOTAL MANPOWER NOT ENOUGH TO COMPLETE THIS TASK! ID: %2d\n\n", id);
			System.exit(1);
		}
		if (freeStaff >= staff) {
			freeStaff -= staff;
			return true;
		}
		return false;
	} // end start()

	public void free() {
		freeStaff += staff;
		for (Task t : children) {
			t.numPredecessors--;
		}
	} // end free()

	public boolean isCritical() {
		return (latestStart - earliestStart == 0);
	} // end isCritical()

	public int occupiedStaff() {
		return totalStaff - freeStaff;
	} // end occupiedStaff()

} // end Task