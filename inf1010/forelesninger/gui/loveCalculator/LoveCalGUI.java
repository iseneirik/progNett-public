import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class LoveCalGUI extends JFrame {
	
	JLabel name1, name2;
	JTextField name1tf, name2tf;
	JButton calcBtn;
	JLabel score;

	Logic log;

	LoveCalGUI(Logic log) {
		this.log = log;

		// Layout
		this.setLayout(new GridLayout(3, 2));
		this.setTitle("Love Calculator IFI Edition");

		// Labels
		name1 = new JLabel();
		name1.setText("Name 1:");
		this.add(name1);

		name2 = new JLabel();
		name2.setText("Name 2:");
		this.add(name2);

		// Textfields
		name1tf = new JTextField();
		this.name1tf.setEditable(true);
		this.name1tf.setBounds(MAXIMIZED_BOTH, MAXIMIZED_BOTH, MAXIMIZED_BOTH, MAXIMIZED_BOTH);
		this.add(name1tf);

		name2tf = new JTextField();
		this.name2tf.setEditable(true);
		this.name2tf.setBounds(MAXIMIZED_BOTH, MAXIMIZED_BOTH, MAXIMIZED_BOTH, MAXIMIZED_BOTH);
		this.add(name2tf);

		// Button and Result
		calcBtn = new JButton();
		this.calcBtn.setText("Generate Score");
		this.calcBtn.setEnabled(true);
		this.calcBtn.addActionListener(new MyActionListener());
		this.add(calcBtn);

		score = new JLabel();
		score.setText("Score: ???");
		this.add(score);

		// Mandatory stuff
		this.setSize(500, 150);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int result = log.generateLoveScore(name1tf.getText(), name2tf.getText());
			score.setText("Score: " + result);
		}	
	}
}