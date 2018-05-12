import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


class ReadProject {
	private String file;
	private Task[] tasks;

	ReadProject(String filename, int manpower) {
		file = filename;
		readFile();
		linkDependencies();
		tasks[0].setStaff(manpower);

		// Find any loops and calculate EarliestStartTime
		for (Task t : tasks) {
			if (t.numPredecessors == 0) {
				int loopID = t.findLoop();
				if (loopID > 0) reportLoop(loopID);
				t.calcEST();
			}
		}
		// Calculate LatestStartTime
		for (Task t : tasks) { 
			if (t.numPredecessors == 0)	
				t.calcLST();
		}

		printTasks();
	} // end Constructor()

	private void printTasks() {
		System.out.println("\nTASKS TO BE EXECUTED\n");
		System.out.println("-----------------------------------------");
		for (Task t : tasks) {
			System.out.printf("ID: %3d \tNAME: %s\n\tTime:     %3d\n\tManpower: %3d\n\tSlack:    %3d\n\tLST:      %3d\n\tChildren: %s\n", 
				t.id, t.name, t.time, t.staff, (t.latestStart - t.earliestStart), 
				t.latestStart, t.childrenString());
			System.out.println("-----------------------------------------");
		}
	} // end printTasks()

	private void readFile() {
		try (Scanner input = new Scanner(new File(file))) {
			tasks = new Task[input.nextInt()];
			int numTasks = 0;
			while (input.hasNextLine()) {
				String newTask = input.nextLine();
				if (newTask.length() == 0 || newTask.charAt(0) == ' ') continue;
				tasks[numTasks++] = new Task(newTask);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
			System.exit(1);
		}
	} // end readFile()

	private void linkDependencies() {
		// All nodes add themselvs as children to their dependencies
		for (Task t : tasks) {
			for (int i = 0; i < t.numPredecessors; i++) {
				tasks[t.dependencies[i]-1].addChild(t);
			}
		}
	} // end linkDependencies()

	private void reportLoop(int id) {
		System.out.println("A loop was found!");
		System.out.println("TaskID: " + id);
		System.exit(0);
	} // end reportLoop()

	public Task[] getTasks() {
		return tasks;
	} // end getTasks()

} // end class ReadProject