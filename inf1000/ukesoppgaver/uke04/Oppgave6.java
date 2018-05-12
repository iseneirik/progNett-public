class Oppgave6 {
    public static void main(String[] args) {
	Robot robot = new Robot();

	String siHei = "Hallo, jeg er roboten Tux";
	robot.hilsen(siHei);
    }
}

class Robot {
    void hilsen(String s) {
	System.out.println(s);
    }
}