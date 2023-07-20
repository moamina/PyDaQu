package data.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.connection.DBConnection;
import data.dat.entities.DataQualityElement;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DBSettingsForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtIP;
	private JTextField txtPort;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JTextField txtDBName;
	
	//private DBConnection dbConnection;
	private JTextField txtStorageName;
	private DataQualityElement dqObj;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBSettingsForm frame = new DBSettingsForm(new DataQualityElement());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DBSettingsForm(DataQualityElement dqObj) {
		setDqObj(dqObj);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 410, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIP = new JLabel("IP Address");
		lblIP.setBounds(39, 42, 83, 24);
		contentPane.add(lblIP);
		
		txtIP = new JTextField();
		txtIP.setBounds(132, 42, 219, 24);
		contentPane.add(txtIP);
		txtIP.setColumns(10);
		
		JLabel lblPort = new JLabel("IP Port");
		lblPort.setBounds(39, 79, 83, 24);
		contentPane.add(lblPort);
		
		txtPort = new JTextField();
		txtPort.setColumns(10);
		txtPort.setBounds(132, 79, 219, 24);
		contentPane.add(txtPort);
		
		JLabel lblusername = new JLabel("User Name");
		lblusername.setBounds(39, 114, 83, 24);
		contentPane.add(lblusername);
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(132, 114, 219, 24);
		contentPane.add(txtUserName);
		
		JLabel lblPasword = new JLabel("Pasword");
		lblPasword.setBounds(39, 149, 83, 24);
		contentPane.add(lblPasword);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(132, 149, 219, 24);
		contentPane.add(txtPassword);
		
		JLabel lblDatabaeName = new JLabel("Databae Name");
		lblDatabaeName.setBounds(39, 184, 83, 24);
		contentPane.add(lblDatabaeName);
		
		txtDBName = new JTextField();
		txtDBName.setColumns(10);
		txtDBName.setBounds(132, 184, 219, 24);
		contentPane.add(txtDBName);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dqObj.getDbConnection().setDbName(txtDBName.getText());
				dqObj.getDbConnection().setDbUser(txtUserName.getText());
				dqObj.getDbConnection().setDbPassword(txtPassword.getText());
				dqObj.getDbConnection().setIp(txtIP.getText());
				dqObj.getDbConnection().setPort(txtPort.getText());
				dqObj.getDbConnection().setStorageName(txtStorageName.getText());
				DAT2GEUI.getInstance().getSelectedDQObj().setDbConnection(dqObj.getDbConnection());
				setVisible(false);
				
			}
		});
		btnNewButton.setBounds(132, 220, 219, 24);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Storage Name");
		lblNewLabel.setBounds(39, 11, 83, 24);
		contentPane.add(lblNewLabel);
		
		txtStorageName = new JTextField();
		txtStorageName.setColumns(10);
		txtStorageName.setBounds(132, 11, 219, 24);
		contentPane.add(txtStorageName);
	}

	public DataQualityElement getDqObj() {
		return dqObj;
	}

	public void setDqObj(DataQualityElement dqObj) {
		this.dqObj = dqObj;
	}

	
}
