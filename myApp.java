package myApp;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.util.ArrayList;


public class myApp extends JFrame {
	final static int MAX_QTY = 6; 

	
	private ContactQueries contactQueries;
	private ArrayList<Contact> allContacts;
	private Contact currentContact;
	
	static JTable tableContact; 
	static JButton btnAddContact;
	static DefaultTableModel myContactTableModel;
	
	public myApp(){
		super("My Phone Book");

		contactQueries = new ContactQueries(); 
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null); 
		setBounds(0,0,436,331); 
		setLocationRelativeTo(null); 

		JLabel lblMyContacts = new JLabel("My Contacts");
		lblMyContacts.setBounds(10, 11, 187, 14);
		getContentPane().add(lblMyContacts);
		
		tableContact = new JTable();
		tableContact.setRowSelectionAllowed(false);
		
		tableContact.setModel(createContactTableModel()); 
		
		tableContact.setBounds(10, 36, 240, 100);
		getContentPane().add(tableContact);
		
		btnAddContact = new JButton("Add Contact");
		btnAddContact.setBounds(270, 36, 89, 23);
		getContentPane().add(btnAddContact);
		
		MyEventHandler commandHandler = new MyEventHandler();
		btnAddContact.addActionListener(commandHandler);
	}

	
	private DefaultTableModel createContactTableModel()
	{
		allContacts = contactQueries.getAllContacts();

		Object[][] data = new Object[allContacts.size()][3];
		String[] columns = new String[] {"First Name", "Last Name", "Phone Number"};
		
		
		for (int row=0; row<allContacts.size(); row++){
			
			currentContact = allContacts.get(row); 
			
			data[row][0] = currentContact.getFirstName();  
			data[row][1] = currentContact.getLastName();  
			data[row][2] = currentContact.getPhoneNumber();  
		}

		myContactTableModel = new DefaultTableModel(data, columns) 
				{
					@Override
					public boolean isCellEditable(int row, int column)  
					{
						return false;
					}
				};
		
		return myContactTableModel;
	}

	private class MyEventHandler implements ActionListener
	{
		public void actionPerformed (ActionEvent myEvent)
		{
			if (myEvent.getSource() == btnAddContact){
				if (allContacts.size() < MAX_QTY){ 
					getNewContactFromUser();
					tableContact.setModel(createContactTableModel()); 
				}
				else{
					JOptionPane.showMessageDialog(null, "You can not add more contacts in your collection", "Info", JOptionPane.INFORMATION_MESSAGE);
				}			
			}
		}
	}
	
	private void getNewContactFromUser(){
		JTextField firstNameField = new JTextField(10);
	    JTextField lastNameField = new JTextField(10);
	    JTextField phoneNumberField = new JTextField(10);
 
	    JPanel myPanel = new JPanel();
	    
	    myPanel.add(new JLabel("First Name:"));
	    myPanel.add(firstNameField);
	    
	    myPanel.add(new JLabel("Last Name:"));
	    myPanel.add(lastNameField);

	    myPanel.add(new JLabel("Phone Number:"));
	    myPanel.add(phoneNumberField);

	    
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter details of your new contact", JOptionPane.OK_CANCEL_OPTION);
	    
	    if (result == JOptionPane.OK_OPTION) {	    	
	    	contactQueries.addContact(firstNameField.getText(), lastNameField.getText(), Integer.parseInt(phoneNumberField.getText()));
	    }
	}
	
	public static void main(String[] args) {
		myApp frame = new myApp();
		frame.setVisible(true);
	}
}