import javax.swing.*;
import java.awt.*;

class Oppgave1 {
	public static void main(String[] args) {
		int[][] board = {
			{1, 2, 3, 4, 5, 6},
			{1, 2, 3, 4, 5, 6},
			{1, 2, 3, 4, 5, 6},
			{1, 2, 3, 4, 5, 6},
			{1, 2, 3, 4, 5, 6},
			{1, 2, 3, 4, 5, 6},
			
		};
		new ButtonGrid(board);
	}
}

class ButtonGrid {
	int[][] board;

	ButtonGrid(int[][] board) {
		this.board = board;
		new Grid3x3Demo(board, 3, 3);
	}

}


class Grid3x3Demo {
	JFrame frame;

	Grid3x3Demo(int[][] board, int x, int y) {
		frame = new JFrame("Grid of buttons (JButton)");
		int north, west, south, east;
		int numRows = x;
		int numCols = y;

		JPanel panel = new JPanel();

		int rows = board.length;
		int columns = board[0].length;
		frame.setSize(50*rows, 50*columns);

		panel.setLayout(new GridLayout(rows, columns));

		for (int i = 0; i < rows; i++) {
			north = 1; west = 1; south = 1; east = 1;
			if (i%numRows == 0 && i > 0) {
				north = 6;
			}
			for (int j = 0; j < columns; j++) {
				west = 1;
				if(j%numCols == 0 && j > 0) {
					west = 6;
				}
				JTextField square = new JTextField();
				square.setFont(new Font("SansSerif", Font.BOLD, 32));
				square.setText(""+board[i][j]);
				square.setBorder(BorderFactory.createMatteBorder(north, west, south, east, Color.black));

				System.out.println("(" + i + "/" + (numRows) + ")+(" + j + "/" + numCols + ") = " + (i/numRows+j/numCols) + "");
				if((i/numRows+j/numCols) % 2 == 0) {
					square.setBackground(Color.yellow);
				} else {
					square.setBackground(Color.white);
				}

				panel.add(square);
			}
		}

		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}