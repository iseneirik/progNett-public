import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;


class GUI {
	// Starts up a window when mainloop is called
	public static void main(String[] args) {
		new Window();
	}
}

class Window extends JFrame {
	// Variables for reading and storing board-data
	private String fileName;
	private SudokuReader sudokuReader;
	private Board problem;
	private int boardSize;

	// Variables to hold solutiondata
	private SudokuContainer container;
	private Solution nextSolution;

	// Panel for menu (buttons)
	private JPanel menuPanel;

	// Panel and Labelarray for board
	private JPanel boardPanel;
	private JLabel[][] squares;

	// Label to show solutionInfo
	private JLabel solutionInfo;
	private int solutionNum;
	private int totalSolutionNum;
	private int maxSolutions;

	Window() {
		this.setLayout(new FlowLayout());

		// menuPanel = buttons
		menuPanel = new JPanel();
		// boardPanel = SudokuBoard
		boardPanel = new JPanel();
		// solutionInfo = number of solutions
		solutionInfo = new JLabel();


		// Button for updating board with next solution
		final JButton nextSolutionBtn = new JButton("Next Solution");
		// Actionlistener calls "nextSolution" and greys out if none are left
		nextSolutionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextSolution();
				Window.this.remove(solutionInfo);
				String solutionString = String.format("Solution %d of %d  (total solutions: %d)",
				++solutionNum, (totalSolutionNum > maxSolutions ? maxSolutions : totalSolutionNum), totalSolutionNum);
				solutionInfo = new JLabel(solutionString);
				Window.this.add(solutionInfo);
				if (nextSolution == null) {
					nextSolutionBtn.setEnabled(false);
					nextSolutionBtn.setText("No more!");
				}
			}
		});
		// Upon starting program, disable the button
		nextSolutionBtn.setEnabled(false);


		// Button used for generating solutions and fillng "container"
		final JButton findSolutions = new JButton("Find Solution(s)");
		// Actionlistener calls solve(), sets up info on where to find the next solution
		// Then it clicks the "nextSolutionBtn" one time to show the first (if any) solution.
		findSolutions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// don't solve problem if already solved (just starts browsing from beginning)
				if (totalSolutionNum < 1) {
					problem.solve();
				}
				solutionNum = 0;
				totalSolutionNum = container.getSolutionCount();
				maxSolutions = container.getMaxSolutionsStored();
				nextSolution = container.get();
				nextSolutionBtn.setText("Next Solution");
				nextSolutionBtn.setEnabled(true);
				nextSolutionBtn.doClick();
			}
		});
		// Upon starting program, disable the button
		findSolutions.setEnabled(false);


		// Button used for opening a sudoku-problem txt file
		JButton openFile = new JButton("Open...");
		openFile.addActionListener(new ActionListener() {
			// Action listener stores board-data and sets up window-size
  			public void actionPerformed(ActionEvent e) {
  				getFileName();
				if (fileName != null) {
					// reset total solutions
					totalSolutionNum = 0;

					// Remove boardpanel and/or solutionInfo if it is present
					Window.this.remove(solutionInfo);
					Window.this.remove(boardPanel);

					// initiate the sudokuReader and container
					sudokuReader = new SudokuReader(fileName);
					container = new SudokuContainer();

					// initiate problem with a sudokuboard
					problem = sudokuReader.getProblem();
					problem.setContainer(container);
					problem.linkSquares();

					// initiate boardsize and show the problem
					boardSize = problem.getSize();
					initSquares();
					
					// add the updated boardPanel
					Window.this.setBounds(700, 300, (boardSize*50)+90, (boardSize*50)+100);
					Window.this.add(boardPanel);
					Window.this.revalidate();
					Window.this.repaint();


					// enable the findSolutions button
					findSolutions.setEnabled(true);
					nextSolutionBtn.setEnabled(false);
				}
  			}
  		});      

		// Ads all buttons from left to right
		menuPanel.add(openFile);
		menuPanel.add(findSolutions);
		menuPanel.add(nextSolutionBtn);

		// some window default-settings		
		this.add(menuPanel);
		this.setBounds(700, 300, 390, 70);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void getFileName() {
		JFileChooser chooser = new JFileChooser(".");
    	FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Text files", "txt");
    	chooser.setFileFilter(filter);
    	int returnVal = chooser.showOpenDialog(this);
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
       		fileName = chooser.getSelectedFile().getName();
    	}
	}

	private void initSquares() {
		// init boardPanel
		boardPanel = new JPanel(new GridLayout(boardSize, boardSize));
		boardPanel.setPreferredSize(new Dimension(50*boardSize, 50*boardSize));
		
		// init board dimensions
		int boxLength = problem.getBoxLength();
		int boxHeight = problem.getBoxHeight();
		squares = new JLabel[boardSize][boardSize];

		// init variables for boarder configuration
		int north = 1;
		int south = 1;
		int east = 1;
		int west = 1;
		
		// Iterate through the board and add numbers as JLabels
		for (int yPos = 0; yPos < boardSize; yPos++) {
			// reset north and south border
			north = 1; south = 1;

			// make top of board thick, and every north and south border to a box
			if (yPos % (boardSize/boxLength) == 0) { north = 2;	}
			if (yPos == 0) { north = 4; }
			if (yPos % (boardSize/boxLength) == (boxHeight - 1)) { south = 2; }
			if (yPos == (boardSize - 1)) { south = 4; }

			for (int xPos = 0; xPos < boardSize; xPos++) {
				// Reset west and east border
				west = 1; east = 1;

				// make sides of board thick, and every east and west border to a box
				if (xPos % (boardSize/boxHeight) == 0) { west = 2; }
				if (xPos == 0) { west = 4; }
				if (xPos % (boardSize/boxHeight) == (boxLength - 1)) { east = 2; }
				if (xPos == (boardSize - 1)) { east = 4; }

				// Replace all '0' with blank spaces
				int value = problem.getSquare(xPos, yPos).getValue();
				squares[yPos][xPos] = new JLabel((value == 0 ? "" : Integer.toString(value, 36).toUpperCase() + ""));

				// Set font and placement of text
				squares[yPos][xPos].setFont(new Font("SansSerif", Font.BOLD, 32));
				squares[yPos][xPos].setHorizontalAlignment(SwingConstants.CENTER);
				
				// set the boarderthicknes
				squares[yPos][xPos].setBorder(BorderFactory.createMatteBorder(north, west, south, east, Color.black));

				// set background to yellow in every other box
				if (((yPos/boxHeight) + (xPos/boxLength)) % 2 == 0) {
					squares[yPos][xPos].setBackground(Color.yellow);
				} else {
					squares[yPos][xPos].setBackground(Color.white);
				}
				// Make background visible
				squares[yPos][xPos].setOpaque(true);

				// add square to boardpanel
				boardPanel.add(squares[yPos][xPos]);
			}
		}
 	}

 	public void nextSolution() {
 		// get solution as an array of ints
		int[][] solution = nextSolution.getNumArray();

		// Update Labels
		for (int yPos = 0; yPos < boardSize; yPos++) {
			for (int xPos = 0; xPos < boardSize; xPos++) {
				squares[yPos][xPos].setText(Integer.toString(solution[yPos][xPos], 36).toUpperCase() + "");
			}
		}

		// update what solution to print next
		nextSolution = nextSolution.next;
	}
}