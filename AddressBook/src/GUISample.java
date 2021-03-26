import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUISample extends JFrame implements ActionListener{

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public JInternalFrame mFrame;
	public JInternalFrame cFrame;
	public JTextField f1;
	public JTextField f2;
	public JTextField f3;

	public GUISample()
	{
		super();
		setSize(WIDTH, HEIGHT);
		setLocation(80,80); // setting the location on the screen
		setTitle("Address Book");
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.gray);
		addWindowListener(new WindowDestroyer());

		JDesktopPane dtp = new JDesktopPane();

	   	 	setContentPane(dtp);

	    	JPanel buttonPanel = new JPanel(); // Java panel to hold buttons
	    	buttonPanel.setLayout(new FlowLayout());// Setting the layout of buttons

	    	JPanel textPanel = new JPanel();// Java panel to hold labels and text fields
	    	textPanel.setLayout(new FlowLayout());

	    	JLabel fname = new JLabel("First Name"); //Creates Java label
	    	f1 = new JTextField(10); // Creates Java text field

	    	textPanel.add(fname); //adds the label to the textPanel
	    	textPanel.add(f1); //adds the text field to the textPanel

	    	JPanel textPanel2 = new JPanel();// Java panel to hold labels and text fields
	    	textPanel2.setLayout(new FlowLayout());

			JLabel lname = new JLabel("Last Name"); //Creates Java label
	    	f2 = new JTextField(10); // Creates Java text field

	    	textPanel2.add(lname); //adds the label to the textPanel
	    	textPanel2.add(f2); //adds the text field to the textPanel

	    	JPanel textPanel3 = new JPanel();// Java panel to hold labels and text fields
	    	textPanel3.setLayout(new FlowLayout());

	    	JLabel address = new JLabel("Address"); //Creates Java label
			f3 = new JTextField(10); // Creates Java text field
			textPanel3.add(address); //adds the label to the textPanel
	    	textPanel3.add(f3); //adds the text field to the textPanel

			JPanel MainTextPanel = new JPanel();
			MainTextPanel.setLayout(new BoxLayout(MainTextPanel, BoxLayout.Y_AXIS));

			MainTextPanel.add(textPanel);
			MainTextPanel.add(textPanel2);
			MainTextPanel.add(textPanel3);

	    	mFrame = new JInternalFrame("First frame", true,true, true, true);
	    	mFrame.setLayout(new BorderLayout());

	    	JButton redButton = new JButton("Red");
	    	redButton.addActionListener(this);

	    	JButton greenButton = new JButton("Green");
	    	greenButton.addActionListener(this);

	    	buttonPanel.add(redButton);
	    	buttonPanel.add(greenButton);
	    	mFrame.setSize(400, 300);
	    	mFrame.setLocation(50, 50);

	    	mFrame.add(MainTextPanel, BorderLayout.WEST); // adds textPanel to the main frame of the window
	    	mFrame.add(buttonPanel, BorderLayout.SOUTH);// adds buttons to the main frame
	    	mFrame.setVisible(true);
	    	dtp.add(mFrame);

	    }

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Red"))
			mFrame.setBackground(Color.red);
		else if(e.getActionCommand().equals("Green"))
			mFrame.setBackground(Color.green);
		else if(e.getActionCommand().equals("Blue"))
			cFrame.setBackground(Color.blue);
		else if(e.getActionCommand().equals("Yellow"))
			cFrame.setBackground(Color.yellow);
		else
			System.out.println("Error in button interface");
	}

	public static void main(String[] args)
	{
		GUISample buttonGui = new GUISample();
		buttonGui.setVisible(true);
	}
}

class WindowDestroyer extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
	{
	    System.exit(0);
	}

}
