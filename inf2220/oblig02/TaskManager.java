import java.util.LinkedList;

class TaskManager {
	ReadProject rp;
	Task[] tasks;
	int time;

	TaskManager(String filename, int manpower) {
		rp = new ReadProject(filename, manpower);
		tasks = rp.getTasks();
		time = 0;
	}

	public void run() {
		System.out.println("\nNO LOOPS WHERE FOUND, RUN TASKS\n");
		System.out.println("-----------------------------------------");

		LinkedList<Task> critical = new LinkedList<Task>();
		LinkedList<Task> slack = new LinkedList<Task>();
		LinkedList<Task> inProgress = new LinkedList<Task>();
		LinkedList<String> status = new LinkedList<String>();

		do {
			String currStat = String.format("Time: %3d\n\t", time);

			// Frees manpower from finished tasks
			int progLength = inProgress.size();
			for (int i = 0; i < progLength; i++) {
				Task currTask = inProgress.pollFirst();
				if (currTask.time <= 0) {
					currStat += String.format("Finished: %3d\n\t", currTask.id);
					currTask.free();
				} else {
					inProgress.add(currTask);
				}
			}

			/* Add all tasks to either the critical list,
			** or slack, if they have slack */
			for (Task t : tasks) {
				if (t.numPredecessors == 0 && !t.collected) {
					t.collected = true;
					if (t.isCritical()) critical.add(t);
					else slack.add(t);
				}
			}

			/* Tries to start critical tasks, puts them back
			** in the que if there is not enough manpower */
			int critLength = critical.size();
			for (int i = 0; i < critLength; i++) {
				Task currTask = critical.pollFirst();
				if(!currTask.start()) {
					critical.add(currTask);
				} else {
					inProgress.add(currTask);
					currStat += String.format("Started: %3d\n\t", currTask.id);
					/* Task has been started */
				}
			}

			/* Tries to start tasks with slack, puts them
			** into critical if they no longer have slack or
			** back into slack if there is not enough manpower */
			int slackLength = slack.size();
			for (int i = 0; i < slackLength; i++) {
				Task currTask = slack.pollFirst();
				if (!currTask.start()) {
					if (time >= currTask.latestStart) {
						critical.add(currTask);
					} else {
						slack.add(currTask);
					}
				} else {
					currStat += String.format("Started: %3d\n\t", currTask.id);
					inProgress.add(currTask);
					/* Task has been started */
				}
			}

			// Runs through and update tasks in progress
			for (Task t : inProgress) {
				t.time--;
			}	

			// currStat is only interesting if any actions have occured (when length is greater than 11) 
			if (currStat.length() > 11) {
				currStat += String.format("Current staff: %3d\n", tasks[0].occupiedStaff());
				status.add(currStat);
			}

			// Incrementation of time
			time++;
		} while (inProgress.size() != 0);

		// Print out all the interesting data
		for (String s : status) {
			System.out.println(s);
		}
		System.out.printf("\n**** Shortest possible project execution is %3d ****\n\n", time-1);
	}
	
}

