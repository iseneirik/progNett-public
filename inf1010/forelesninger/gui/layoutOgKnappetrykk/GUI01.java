import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GUI01 {
	public static void main(String[] args) {
		new Window();
	}
}

class Window extends JFrame {
	JLabel label;
	JTextField textField;
	JButton button;
	Listener buttonListener;

	Window() {
		// Set the title of the JFrame
		this.setTitle("Test Window");

		// set the bounds of the JFrame
		this.setBounds(700, 200, 500, 200);

		// set the layout of the JFrame
		this.setLayout(new FlowLayout());

		// init the JLabel and add to the JFrame
		label = new JLabel("Write in a name");
		this.add(label);

		// init the JTextField and add to the JFrame
		textField = new JTextField(30);
		this.add(textField);

		// init the JButton with Listener and add to the JFrame
		button = new JButton("Finished");
		buttonListener = new Listener();
		button.addActionListener(buttonListener);
		this.add(button);

		/*********************************** evt. ***********************
		* 		button.addActionListener(new ActionListener() {			*
		*     		public void actionPerformed(ActionEvent e) {		*
		*	      		// stuff to be done								*
		*     		}													*
	    * 		});														*
	    ****************************************************************/

		// Set close operation and make window appear
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("WTF?!?");
		}
	}
}