package cidermillfriends;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;

import java.sql.*;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import javax.swing.JLabel;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFormattedTextField;
import javax.swing.BoxLayout;

public class Cidermillfriends extends JFrame {
	public static int peopleRecords;
	public static int donationRecords;
	public static int eventRecords;
	public static int volenteerRecords;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
			throws SQLException, ClassNotFoundException{
		//set up an odbc admin on access
		//Connection connection = DriverMangager.getConnection('jdbc:odbc:')
		//driver = sun.jdbc.odbc.JdbcOdbcDriver
		//incase i need to load the driver
		//try{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		//}catch(Exception e){
			//e.fillInStackTrace();
		//}
		System.out.println("Driver Loaded");
		
		//url = jdbc:odbc:cidermillfriends
		Connection connection = DriverManager.getConnection 
				("jdbc:ucanaccess://src/cidermillfriends/cidermillfriends.accdb");
				
		System.out.println("Database Loaded");
		
		//testing a simple query
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		
		Statement test = null;
		test = connection.createStatement();
		String peopleQuery = "SELECT ID FROM people";
		String donationsQuery = "SELECT ID FROM donation";
		String volenQuery = "SELECT ID FROM volenteer";
		String eventQuery = "SELECT ID FROM event";
		
		rs1 = test.executeQuery(peopleQuery);
		rs2 = test.executeQuery(donationsQuery);
		rs3 = test.executeQuery(volenQuery);
		rs4 = test.executeQuery(eventQuery);
	
		while(rs1.next()){
			peopleRecords ++;
		}
		System.out.println(peopleRecords);
		while(rs2.next()){
			donationRecords ++;
		}
		System.out.println(donationRecords);
		while(rs3.next()){
			volenteerRecords ++;
		}
		System.out.println(volenteerRecords);
		while(rs4.next()){
			eventRecords ++;
		}
		System.out.println(eventRecords);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cidermillfriends frame = new Cidermillfriends();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//close it down
		connection.close();
	}

	/**
	 * Create the frame.
	 */
	public Cidermillfriends() {
		setFont(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Cider Mill Friends");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		
		JTextPane txtpnThings = new JTextPane();
		txtpnThings.setText("Current Reocrds \n People: " + peopleRecords 
				+ "\n Volenteers: " + volenteerRecords 
				+ "\n Donations: " + donationRecords 
				+ "\n Events: " + eventRecords);
		tabbedPane.addTab("Home", null, txtpnThings, null);
		
		JFormattedTextField frmtdtxtfldFirstName = new JFormattedTextField();
		frmtdtxtfldFirstName.setText("First name");
		tabbedPane.addTab("Add Person", null, frmtdtxtfldFirstName, null);
		
		JButton btnLookUpPeople = new JButton("Look Up People");
		tabbedPane.addTab("Look up People", null, btnLookUpPeople, null);
		
		JButton btnLookUpVolenteers = new JButton("Look Up Volenteers");
		tabbedPane.addTab("Look up Volenteers", null, btnLookUpVolenteers, null);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tabbedPane}));
		}
}
