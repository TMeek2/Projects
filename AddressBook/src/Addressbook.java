import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Addressbook extends JFrame implements ActionListener {


	    public static final int WIDTH = 1440;
		public static final int HEIGHT = 535;
		public JInternalFrame mFrame;
		public JInternalFrame sFrame;
		private int personID;
		
		// names table
		public JTextField f1;
		public JTextField l1;

		// addresses table
		public JTextField address;
		public JTextField address2;
		public JTextField city;
		public JTextField state;
		public JTextField zipCode;

		// emailAddresses table
		public JTextField emailAddress;

		// phoneNumbers table
		public JTextField phoneNumber;

		// public ImageIcon icon = new javax.swing.ImageIcon("images\\addressbook.jpg");
		private Image addressIcon = Toolkit.getDefaultToolkit().getImage("images\\addressbook_icon.png");
		private ImageIcon searchIcon = new ImageIcon("images\\search_icon.png", "search");
		private ImageIcon editIcon = new ImageIcon("images\\edit_icon.png", "search");

		

		public String firstname;
		public String lastname;
		public String Address1;
		public String Address2;
		public String City;
		public String State;
		public String ZipCode;
		public String EmailAddress;
		public String PhoneNumber;


	public Addressbook()
    {
      	super();
		setSize(WIDTH, HEIGHT);
		setLocation(100,100); // setting the location on the screen
		setTitle("Address Book");
		setIconImage(addressIcon);
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.decode("#87ADDE"));

		addWindowListener(new WindowDestroyer());

		JDesktopPane dtp = new JDesktopPane();
		setContentPane(dtp);

    	JPanel buttonPanel = new JPanel(); // Java panel to hold buttons
    	buttonPanel.setLayout(new GridLayout(3,2)); // Setting the layout of buttons

    	JPanel textPanel = new JPanel(); // Java panel to hold labels and text fields
    	// textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
		// textPanel.setLayout(new GridLayout(9, 1));
		textPanel.setLayout(new GridLayout(10,1));



    	JPanel FirstName = new JPanel();
    	FirstName.setLayout(new FlowLayout());
		// FirstName.setLayout(new GridLayout());
    	JLabel fname = new JLabel("First Name");
    	f1 = new JTextField(10);

    	JPanel LastName = new JPanel();
    	LastName.setLayout(new FlowLayout());
    	JLabel lname = new JLabel("Last Name");
    	l1 = new JTextField(10);


    	JPanel addrPanel = new JPanel();
    	addrPanel.setLayout(new FlowLayout());
    	JLabel addr = new JLabel("Address");
    	address = new JTextField(10);

    	JPanel addrPanel2 = new JPanel();
		addrPanel2.setLayout(new FlowLayout());
		JLabel addr2 = new JLabel("Address 2");
    	address2 = new JTextField(10);

		JPanel cityPanel = new JPanel();
    	cityPanel.setLayout(new FlowLayout());
    	JLabel cityName = new JLabel("City");
    	city = new JTextField(10);

		JPanel statePanel = new JPanel();
    	statePanel.setLayout(new FlowLayout());
    	JLabel stateName = new JLabel("State");
    	state = new JTextField(10);

		JPanel zipPanel = new JPanel();
    	zipPanel.setLayout(new FlowLayout());
    	JLabel zip = new JLabel("ZipCode");
    	zipCode = new JTextField(10);

		JPanel phonePanel = new JPanel();
    	phonePanel.setLayout(new FlowLayout());
    	JLabel phone = new JLabel("Phone");
    	phoneNumber = new JTextField(10);
			// formatting info here: https://docs.oracle.com/javase/tutorial/uiswing/components/formattedtextfield.html


		JPanel emailPanel = new JPanel();
    	emailPanel.setLayout(new FlowLayout());
    	JLabel email = new JLabel("Email");
    	emailAddress = new JTextField(10);

    	FirstName.add(fname);
    	FirstName.add(f1);
		FirstName.setBackground(Color.decode("#87ADDE"));

    	LastName.add(lname);
    	LastName.add(l1);
		LastName.setBackground(Color.decode("#87ADDE"));

    	addrPanel.add(addr);
    	addrPanel.add(address);
		addrPanel.setBackground(Color.decode("#87ADDE"));

    	addrPanel2.add(addr2);
    	addrPanel2.add(address2);
		addrPanel2.setBackground(Color.decode("#87ADDE"));

		cityPanel.add(cityName);
		cityPanel.add(city);
		cityPanel.setBackground(Color.decode("#87ADDE"));

		statePanel.add(stateName);
		statePanel.add(state);
		statePanel.setBackground(Color.decode("#87ADDE"));

		zipPanel.add(zip);
		zipPanel.add(zipCode);
		zipPanel.setBackground(Color.decode("#87ADDE"));

		phonePanel.add(phone);
		phonePanel.add(phoneNumber);
		phonePanel.setBackground(Color.decode("#87ADDE"));

		emailPanel.add(email);
		emailPanel.add(emailAddress);
		emailPanel.setBackground(Color.decode("#87ADDE"));



    	//continue adding the text fields

    	textPanel.add(FirstName);
    	textPanel.add(LastName);
    	textPanel.add(addrPanel);
    	textPanel.add(addrPanel2);
		textPanel.add(cityPanel);
		textPanel.add(statePanel);
		textPanel.add(zipPanel);
		textPanel.add(phonePanel);
		textPanel.add(emailPanel);
		textPanel.setBackground(Color.decode("#87ADDE"));
		// textPanel.setLayout(new GridLayout(9,1));

    	mFrame = new JInternalFrame("Address Book Operations", true,true, true, true);
    	mFrame.setLayout(new BorderLayout());

    	JButton insertButton = new JButton("Insert");
    	insertButton.addActionListener(this);
		insertButton.setToolTipText("A new entry will be placed in the address book database, with the values in the text fields.");

    	JButton deleteButton = new JButton("Delete");
    	deleteButton.addActionListener(this);
		deleteButton.setToolTipText("This button will delete all information for the top search result. The search function must be used prior to using delete.");

    	JButton newButton = new JButton("New");
    	newButton.addActionListener(this);
		newButton.setToolTipText("This button will clear all text fields.");

		JButton searchButton = new JButton("Search");
    	searchButton.addActionListener(this);
		searchButton.setToolTipText("This function will search for and display the information of all users in the address book database. You can search by first name, last name, or leave both empty to show all entries.");;

		JButton updateButton = new JButton("Update");
    	updateButton.addActionListener(this);
		updateButton.setToolTipText("After using the search function, the information for the top search result will be updated with the information in the text fields.");

		JButton fillFormButton = new JButton("Fill Form");
    	fillFormButton.addActionListener(this);
		fillFormButton.setToolTipText("After using the search function, the information of the top search result can be used to fill the text fields.");

		mFrame.setBackground(Color.decode("#87ADDE"));
		mFrame.setForeground(Color.decode("#87ADDE"));
    	
		buttonPanel.add(newButton);
    	buttonPanel.add(insertButton);
    	buttonPanel.add(deleteButton);
		buttonPanel.add(searchButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(fillFormButton);
		

    	mFrame.setSize(325, 500);
    	mFrame.setLocation(0, 0);


    	mFrame.add(textPanel, BorderLayout.WEST); // adds textPanel to the main frame of the window
    	mFrame.add(buttonPanel, BorderLayout.SOUTH);// adds buttons to the main frame
		mFrame.setFrameIcon(editIcon);
		mFrame.setVisible(true);
    	dtp.add(mFrame);


		// Search Pane
		sFrame = new JInternalFrame("Search Results", true,true, true, true);
		sFrame.setLayout(new BorderLayout());
		sFrame.setSize(1100, 500);
    	sFrame.setLocation(325, 0);
		sFrame.setBackground(Color.decode("#deb887"));
		sFrame.setFrameIcon(searchIcon);
		sFrame.setVisible(true);
		// JTextArea textArea = new JTextArea();
		// sFrame.add(textArea);
		dtp.add(sFrame);


   }
   public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Insert"))
		{
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				
				Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Tim Meek\\Desktop\\UTPB Spring 2021\\Information Systems\\AddressBook\\AddressBook\\src\\addressbook.accdb");

		        Statement statement = connection.createStatement();

		        firstname = f1.getText(); //gets the value from the text field (first name)
		        lastname = l1.getText(); // gets the value from the text field (last name)
				Address1 = address.getText();
				Address2 = address2.getText();
				City = city.getText();
				State = state.getText();
				ZipCode = zipCode.getText();
				EmailAddress = emailAddress.getText();
				PhoneNumber = phoneNumber.getText();

				if(firstname.isBlank() && lastname.isBlank())
					JOptionPane.showMessageDialog(this,"No name was entered. Please try again.");

				else{
					statement.executeUpdate("INSERT INTO names (firstName, lastName) VALUES ('"+firstname+"','"+lastname+"')"); //inserts a record into names table
					

					PreparedStatement PersonIDStatement = connection.prepareStatement("SELECT personID FROM names WHERE firstName LIKE '"+firstname+"' AND lastName LIKE '"+lastname+"'");
					ResultSet getPersonID = PersonIDStatement.executeQuery();

					personID = 0;
					if(getPersonID.next())
						personID = getPersonID.getInt(1);

					statement.executeUpdate("INSERT INTO addresses (personID, address1, address2, city, state, zipcode) VALUES ('"+personID+"','"+Address1+"','"+Address2+"','"+City+"','"+State+"','"+ZipCode+"')");
					statement.executeUpdate("INSERT INTO emailAddresses (personID, emailAddress) VALUES ('"+personID+"','"+EmailAddress+"')");
					statement.executeUpdate("INSERT INTO phoneNumbers (personID, phoneNumber) VALUES ('"+personID+"','"+PhoneNumber+"')");

					statement.close();
					connection.close();

					// display that insertion was successful
					JOptionPane.showMessageDialog(this,"The entry of " +firstname+ " " +lastname+ " was successful.");  
				}
			}
			catch ( SQLException sqlException ) {
	         	JOptionPane.showMessageDialog( null,
	            sqlException.getMessage(), "Database Error",
	            JOptionPane.ERROR_MESSAGE );
	         	System.exit( 1 );
	     	}

	     	catch(ClassNotFoundException cnfex) {
	          	System.out.println("Problem in loading or " + "registering MS Access JDBC driver");
	          	cnfex.printStackTrace();
	      	}
		}


		else if(e.getActionCommand().equals("New"))
		{
			f1.setText(""); //gets the value from the text field (first name)
		    l1.setText(""); // gets the value from the text field (last name)
			address.setText("");
			address2.setText("");
			city.setText("");
			state.setText("");
			zipCode.setText("");
			emailAddress.setText("");
			phoneNumber.setText("");
		}


		else if(e.getActionCommand().equals("Delete"))
		{
			try {

				if(personID == 0)
					JOptionPane.showMessageDialog(this,"Please use the search function to find the entry you want to delete.");

				else{

					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					
					Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Tim Meek\\Desktop\\UTPB Spring 2021\\Information Systems\\AddressBook\\AddressBook\\src\\addressbook.accdb");

					Statement statement = connection.createStatement();

					// ID number and insert it into prompt
					int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the user with a personID of '"+personID+"'?", "Confirmation", JOptionPane.YES_NO_OPTION);

					if (response == JOptionPane.YES_OPTION) {
						// System.out.println("Yes button clicked");
						// statement.executeUpdate("DELETE * FROM names, addresses, emailAddresses, phoneNumbers WHERE personID = '"+personID+"'"); //deletes the record from all tables
						statement.executeUpdate( "DELETE * FROM names WHERE personID = '"+personID+"'");
						statement.executeUpdate( "DELETE * FROM addresses WHERE personID = '"+personID+"'");
						statement.executeUpdate( "DELETE * FROM emailAddresses WHERE personID = '"+personID+"'");
						statement.executeUpdate( "DELETE * FROM phoneNumbers WHERE personID = '"+personID+"'");

						// display that insertion was successful
						JOptionPane.showMessageDialog(this,"The deletion of " +firstname+ " " +lastname+ " was successful."); 
					}
					else if (response == JOptionPane.NO_OPTION) {
						// System.out.println("No button clicked");
					} 
					else if (response == JOptionPane.CLOSED_OPTION) {
						// System.out.println("JOptionPane closed");
					}
					
					statement.close();
					connection.close();

					
				} 
			} // try

			catch ( SQLException sqlException ) {
				JOptionPane.showMessageDialog( null,
				sqlException.getMessage(), "Database Error",
				JOptionPane.ERROR_MESSAGE );
				System.exit( 1 );
			}

			catch(ClassNotFoundException cnfex) {
					System.out.println("Problem in loading or " + "registering MS Access JDBC driver");
					cnfex.printStackTrace();
				}
		}

		
		else if(e.getActionCommand().equals("Search"))
		{
			try {
			   	// load database driver class
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	  
			   	// connect to database
			   	Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Tim Meek\\Desktop\\UTPB Spring 2021\\Information Systems\\AddressBook\\AddressBook\\src\\addressbook.accdb");
	  
			   	// create Statement to query database
			   	Statement statement = connection.createStatement();
	  
			   	ResultSet resultSet = null;

			   	firstname = f1.getText(); //gets the value from the text field (first name)
		    	lastname = l1.getText(); // gets the value from the text field (last name)

			   // query database
			   	if (!firstname.isBlank() && !lastname.isBlank()){
					resultSet = statement.executeQuery( "SELECT names.personID, firstName, lastName, address1, address2, city, state, zipcode, phoneNumber, emailAddress FROM names INNER JOIN (addresses INNER JOIN ( emailAddresses INNER JOIN phoneNumbers ON emailAddresses.personID = phoneNumbers.personID) ON addresses.personID = emailAddresses.personID) ON names.personID = addresses.personID WHERE firstName LIKE '"+firstname+"' AND lastName LIKE '"+lastname+"'");
					// resultSet = statement.executeQuery( "SELECT * FROM names WHERE firstName LIKE '"+firstname+"' AND lastName LIKE '"+lastname+"'");
			   	}
				else if (!firstname.isBlank() && lastname.isBlank()){
					resultSet = statement.executeQuery( "SELECT names.personID, firstName, lastName, address1, address2, city, state, zipcode, phoneNumber, emailAddress FROM names INNER JOIN (addresses INNER JOIN ( emailAddresses INNER JOIN phoneNumbers ON emailAddresses.personID = phoneNumbers.personID) ON addresses.personID = emailAddresses.personID) ON names.personID = addresses.personID WHERE firstName LIKE '"+firstname+"'");
			   	}
			   	else if (firstname.isBlank() && !lastname.isBlank()){
					resultSet = statement.executeQuery(  "SELECT names.personID, firstName, lastName, address1, address2, city, state, zipcode, phoneNumber, emailAddress FROM names INNER JOIN (addresses INNER JOIN ( emailAddresses INNER JOIN phoneNumbers ON emailAddresses.personID = phoneNumbers.personID) ON addresses.personID = emailAddresses.personID) ON names.personID = addresses.personID WHERE lastName LIKE '"+lastname+"'");
		  		}
				else {
					resultSet = statement.executeQuery(  "SELECT names.personID, firstName, lastName, address1, address2, city, state, zipcode, phoneNumber, emailAddress FROM names INNER JOIN (addresses INNER JOIN ( emailAddresses INNER JOIN phoneNumbers ON emailAddresses.personID = phoneNumbers.personID) ON addresses.personID = emailAddresses.personID) ON names.personID = addresses.personID ");
				}

			   	// process query results
			   	StringBuffer results = new StringBuffer();
			   	ResultSetMetaData metaData = resultSet.getMetaData();
			   	int numberOfColumns = metaData.getColumnCount();
			   	for ( int i = 1; i <= numberOfColumns; i++ ) {
					results.append(metaData.getColumnName( i ) + "\t" );
					// results.append(String.format("|%-20.15s|", metaData.getColumnName(i))); //formatting test
			   	}
			   	results.append( "\n" );
				
			   	while ( resultSet.next() ) {
					for ( int i = 1; i <= numberOfColumns; i++ ) {
						results.append( resultSet.getObject( i ) + "\t" );
						if(resultSet.getObject(i) == "")
							results.append("\t");
						// else if (resultSet.getObject(i).length() > 13)


						// int columnWidth = metaData.getColumnName(i).length() + "\t".length() ;
						// results.append(String.format("%-"+columnWidth+"s", resultSet.getObject( i )));//formatting test
						// results.append(String.format("|%-20.15s|", resultSet.getObject( i )));//formatting test
					}
					results.append( "\n" );
			   	}
			   	
	  
			   	// set up GUI and display window for Search results
			   	JTextArea textArea = new JTextArea(results.toString() );
				textArea.setBackground(Color.decode("#deb887"));
			   	sFrame.add(textArea);
				sFrame.revalidate();


				// retrieves personID used for deletion or update commands
				PreparedStatement PersonIDStatement;
				ResultSet getPersonID = null;

				if (!firstname.isBlank() && !lastname.isBlank())
			   		PersonIDStatement = connection.prepareStatement("SELECT personID FROM names WHERE firstName LIKE '"+firstname+"' AND lastName LIKE '"+lastname+"'");

				else if (!firstname.isBlank() && lastname.isBlank())
			   		PersonIDStatement = connection.prepareStatement("SELECT personID FROM names WHERE firstName LIKE '"+firstname+"'");

				else if (firstname.isBlank() && !lastname.isBlank())
			   		PersonIDStatement = connection.prepareStatement("SELECT personID FROM names WHERE lastName LIKE '"+lastname+"'");

				else
				   	PersonIDStatement = null;

				if (PersonIDStatement != null)
					getPersonID = PersonIDStatement.executeQuery();

				personID = 0;
				if(getPersonID != null)
					if(getPersonID.next())
						personID = getPersonID.getInt(1);
				
				// close statement and connection
				statement.close();
			   	connection.close();

			}  // end try
	  
			// detect problems interacting with the database
			catch ( SQLException sqlException ) {
			   JOptionPane.showMessageDialog( null,
				  sqlException.getMessage(), "Database Error",
				  JOptionPane.ERROR_MESSAGE );
			   System.exit( 1 );
			}
	  
			catch(ClassNotFoundException cnfex) {
	  
				System.out.println("Problem in loading or "
						+ "registering MS Access JDBC driver");
				cnfex.printStackTrace();
			}
		}  

		else if(e.getActionCommand().equals("Update"))
		{
			try {
				firstname = f1.getText(); //gets the value from the text field (first name)
		        lastname = l1.getText(); // gets the value from the text field (last name)
				Address1 = address.getText();
				Address2 = address2.getText();
				City = city.getText();
				State = state.getText();
				ZipCode = zipCode.getText();
				EmailAddress = emailAddress.getText();
				PhoneNumber = phoneNumber.getText();

				if(firstname.isBlank() && lastname.isBlank())
					JOptionPane.showMessageDialog(this,"No name was entered. Please try again.");

				else{
					
					if(personID == 0)
						JOptionPane.showMessageDialog(this,"Please use the search function to find the entry you want to update.");

					else{

						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						
						Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Tim Meek\\Desktop\\UTPB Spring 2021\\Information Systems\\AddressBook\\AddressBook\\src\\addressbook.accdb");

						Statement statement = connection.createStatement();

						// ID number and insert it into prompt
						int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to update the information of the user with a personID of '"+personID+"'?", "Confirmation", JOptionPane.YES_NO_OPTION);

						if (response == JOptionPane.YES_OPTION) {
							// statement.executeUpdate("DELETE * FROM names, addresses, emailAddresses, phoneNumbers WHERE personID = '"+personID+"'"); //deletes the record from all tables
							statement.executeUpdate( "UPDATE names SET firstName='"+firstname+"', lastName='"+lastname+"' WHERE personID = '"+personID+"'");
							statement.executeUpdate( "UPDATE addresses SET address1='"+Address1+"', address2='"+Address2+"', city='"+City+"', state='"+State+"', zipcode='"+ZipCode+"' WHERE personID = '"+personID+"'");
							statement.executeUpdate( "UPDATE emailAddresses SET emailAddress='"+EmailAddress+"' WHERE personID = '"+personID+"'");
							statement.executeUpdate( "UPDATE phoneNumbers SET phoneNumber='"+PhoneNumber+"' WHERE personID = '"+personID+"'");


							// display that insertion was successful
							JOptionPane.showMessageDialog(this,"The update of " +firstname+ " " +lastname+ " was successful."); 
						}
						else if (response == JOptionPane.NO_OPTION) {
							// System.out.println("No button clicked");
						} 
						else if (response == JOptionPane.CLOSED_OPTION) {
							// System.out.println("JOptionPane closed");
						}
						
						statement.close();
						connection.close();

						
					}
				}
			} // try

			catch ( SQLException sqlException ) {
				JOptionPane.showMessageDialog( null,
				sqlException.getMessage(), "Database Error",
				JOptionPane.ERROR_MESSAGE );
				System.exit( 1 );
			}

			catch(ClassNotFoundException cnfex) {
					System.out.println("Problem in loading or " + "registering MS Access JDBC driver");
					cnfex.printStackTrace();
				}
		}


		else if(e.getActionCommand().equals("Fill Form"))
		{
			try {
				
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	  
				Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Tim Meek\\Desktop\\UTPB Spring 2021\\Information Systems\\AddressBook\\AddressBook\\src\\addressbook.accdb");
	  
			   	// Statement statement = connection.createStatement();
				
				if(personID == 0)
					JOptionPane.showMessageDialog(this,"Please use the search function to find the information you want to be filled in the form.");

				else{

					PreparedStatement gatheredInformatioStatement;
					ResultSet gatheredInformation = null;

					gatheredInformatioStatement = connection.prepareStatement("SELECT names.personID, firstName, lastName, address1, address2, city, state, zipcode, phoneNumber, emailAddress FROM names INNER JOIN (addresses INNER JOIN ( emailAddresses INNER JOIN phoneNumbers ON emailAddresses.personID = phoneNumbers.personID) ON addresses.personID = emailAddresses.personID) ON names.personID = addresses.personID WHERE personID LIKE '"+personID+"'");

					gatheredInformation = gatheredInformatioStatement.executeQuery();

					if(gatheredInformation != null){
						if(gatheredInformation.next()){
							personID = gatheredInformation.getInt(1);
							String firstN = gatheredInformation.getString(2);
							String secondN = gatheredInformation.getString(3);
							String firstAd = gatheredInformation.getString(4);
							String secondAd = gatheredInformation.getString(5);
							String cName = gatheredInformation.getString(6);
							String sName = gatheredInformation.getString(7);
							String zCode = gatheredInformation.getString(8);
							String phoneN = gatheredInformation.getString(9);
							String emailAd = gatheredInformation.getString(10);
						
							f1.setText(firstN); //gets the value from the text field (first name)
							l1.setText(secondN); // gets the value from the text field (last name)
							address.setText(firstAd);
							address2.setText(secondAd);
							city.setText(cName);
							state.setText(sName);
							zipCode.setText(zCode);
							phoneNumber.setText(phoneN);
							emailAddress.setText(emailAd);
						}
					}
				}
				// statement.close();
				connection.close();
			} // try

			catch ( SQLException sqlException ) {
				JOptionPane.showMessageDialog( null,
				sqlException.getMessage(), "Database Error",
				JOptionPane.ERROR_MESSAGE );
				System.exit( 1 );
			}

			catch(ClassNotFoundException cnfex) {
					System.out.println("Problem in loading or " + "registering MS Access JDBC driver");
					cnfex.printStackTrace();
				}


			
		}
		
		else
			System.out.println("Error in button interface");
			
	}



   public static void main( String args[] )
   {
      Addressbook window = new Addressbook();
      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      window.setVisible(true);

   }
}  // end class DbConnection

class WindowDestroyer extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
	{
	    System.exit(0);
	}

}
