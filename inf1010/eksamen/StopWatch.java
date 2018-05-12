import java.awt.*;
import java.awt.event.*;
import java.io.*;

class StopWatch extends Thread {
	private volatile boolean stop = false;

	public void run() {
		int time = 0;
		
		while (!stop) {
			System.out.println(time++);
			try {
				sleep(1000);
			} catch (InterruptedException e) {}
		}
	}

	public void stopTime() {
		stop = true;
	}
}

class TestProgram {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Press enter to start, and enter to stop!");

		bf.readLine();

		StopWatch timer = new StopWatch();
		timer.start();

		bf.readLine();
		timer.stopTime();
	}
}