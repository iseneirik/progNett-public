class Oblig2 {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Correct usage: java Oblig2 <projectName.txt> <manpower>");
			System.out.println("For more info: cat README.txt");
		} else {
			TaskManager tm = new TaskManager(args[0], Integer.parseInt(args[1]));
			tm.run();
		}
	}
}