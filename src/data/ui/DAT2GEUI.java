package data.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FileUtils;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import data.codegeneration.mapping.DBColumn;
import data.codegeneration.mapping.DBTable;
import data.codegeneration.mapping.MethodCondition;
import data.connection.DBConnection;
import data.connection.DatabaseType;
import data.connection.imp.MySQLConnection;
import data.dat.entities.DataArchitecture;
import data.dat.entities.DataQualityElement;
import data.dat.entities.QualityMetrics;
import data.dat.parser.LoadMapperJSONFile;
import data.dat.parser.XMLParser;
import data.generator.GenerateMainClass;
import data.generator.GenericGenerator;
import data.metadata.MetaData;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.SystemColor;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JToolBar;

import java.awt.Component;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.ImageIcon;

public class DAT2GEUI extends JFrame {

	private JPanel contentPane;

	private JTextField txtlfile;
	private ArrayList<String> filterls;
	private JTable storageTable;
	private DataArchitecture dArch;
	private DataQualityElement selectedDQObj;
	private int selectedRowIndex;

	private static DAT2GEUI mainUI;
	private JTextField txtIPAddress;
	private JTextField txtPort;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JTextField txtDBName;
	private JTextField txt_StorageName;
	private JComboBox jcbx_dbType;
	private JList jList_Tables;
	private JList qm_List;
	private JButton btn_LoadCol;
	private JList jlist_Col;
	//private Hashtable<String, ArrayList<String>> tables;
	private JList jlist_ge;
	public static String Mapper_QD_GE = "./othersFiles/mapper_dqd_ge.json";
	private ArrayList<DBTable> generationList = new ArrayList<>();
	private Hashtable<String, DBTable> tables=new Hashtable<String, DBTable>();
	private JTextField txtLength;
	private JTextField textField_1;
	private JTextField txtRegex;
	private JTextField txtMaxVal;
	private JTextField txtMinVal;
	private JTextField txtVal;
	private JTextField txtFormat;
	private DBTable dbTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// GUIUtilities.GetSubstanceMistAquaLookAndFeel();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DAT2GEUI frame = new DAT2GEUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static DAT2GEUI getInstance() {
		if (mainUI == null) {
			mainUI = new DAT2GEUI();
		}
		return mainUI;
	}

	/**
	 * Create the frame.
	 */
	private DAT2GEUI() {
		setForeground(new Color(0, 0, 0));
		setFont(new Font("Dialog", Font.BOLD, 12));

		selectedDQObj = null;
		selectedRowIndex = -1;
		filterls = new ArrayList<String>();
		
		setTitle("PyDaQu");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				setVisible(false);
				System.exit(0);
			}
		});
		setBackground(SystemColor.desktop);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 1063, 637);
		// if(user.getUserLevel()==2)
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);

			JMenu mnFile = new JMenu("File");
			menuBar.add(mnFile);

			JMenuItem ExitMItem = new JMenuItem("Exit");
			ExitMItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					System.exit(0);
				}
			});

			JMenuItem new_Data_Storage = new JMenuItem("New Data Storage");
			mnFile.add(new_Data_Storage);
			mnFile.add(ExitMItem);
		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel loadDAT = new JPanel();
		loadDAT.setBounds(5, 5, 1032, 70);
		loadDAT.setBorder(null);
		loadDAT.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.add(loadDAT);
		loadDAT.setPreferredSize(new Dimension(825, 70));
		loadDAT.setMaximumSize(new Dimension(825, 70));
		loadDAT.setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 1022, 70);
		toolBar.setPreferredSize(new Dimension(825, 100));
		loadDAT.add(toolBar);
		toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);

		JPanel storages = new JPanel();
		storages.setBounds(5, 75, 360, 143);
		storages.setBorder(new LineBorder(new Color(0, 0, 0)));
		storages.setPreferredSize(new Dimension(400, 150));
		storages.setMaximumSize(new Dimension(400, 150));
		contentPane.add(storages);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(340, 134));
		storages.add(scrollPane_1);

		storageTable = new JTable();
		storageTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedRowIndex = storageTable.getSelectedRow();
				// int selectedColumnIndex = storageTable.getSelectedColumn();
				// Object selectedObject = (Object)
				// storageTable.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
				System.out.println(selectedRowIndex);
				if (selectedRowIndex >= 0 && dArch != null && dArch.getElements() != null
						&& dArch.getElements().size() > 0) {

					selectedDQObj = dArch.getElements().get(selectedRowIndex);
					System.out.println(selectedDQObj.getName() + " : " + selectedDQObj.getType());
					if (selectedDQObj.getDbConnection() == null)
						selectedDQObj.setDbConnection(new DBConnection());
					txt_StorageName.setText(selectedDQObj.getDbConnection().getStorageName());
					txtIPAddress.setText(selectedDQObj.getDbConnection().getIp());
					txtDBName.setText(selectedDQObj.getDbConnection().getDbName());
					txtPort.setText(selectedDQObj.getDbConnection().getPort());
					txtUserName.setText(selectedDQObj.getDbConnection().getDbUser());
					txtPassword.setText(selectedDQObj.getDbConnection().getDbPassword());

					selectQualityMetrics();
				}
				// loadDBType();

			}

		});
		storageTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		storageTable.setModel(
				new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "Storage Name", "Describe" }));
		storageTable.getColumnModel().getColumn(0).setPreferredWidth(127);
		scrollPane_1.setViewportView(storageTable);

		JPanel panel_1 = new JPanel();
		toolBar.add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("Load Model");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String path = txtlfile.getText().trim();
				if (path == null || path.equals(""))
					return;
				XMLParser daml = new XMLParser(path);
				dArch = daml.loadDataFromXML();
				populateTable(dArch);

			}

		});
		btnNewButton.setBounds(791, 11, 100, 42);
		panel_1.add(btnNewButton);

		JButton btnOpen = new JButton("Open DAML File");
		btnOpen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOpen.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fc = new JFileChooser(new File("."));
				// fc.setCurrentDirectory(new File("/~"));
				int returnVal = fc.showOpenDialog(DAT2GEUI.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					// This is where a real application would open the file.
					txtlfile.setText(file.getPath());
				} else {
					txtlfile.setText("No File Selected");
					// log.append("Open command cancelled by user." + newline);
				}

			}
		});
		btnOpen.setBounds(667, 11, 119, 42);
		panel_1.add(btnOpen);

		txtlfile = new JTextField();
		txtlfile.setToolTipText("File Path");
		txtlfile.setEditable(false);
		txtlfile.setBounds(124, 11, 533, 42);
		panel_1.add(txtlfile);
		txtlfile.setColumns(10);

		JPanel Details = new JPanel();
		Details.setBounds(5, 223, 360, 290);
		Details.setBorder(new LineBorder(new Color(0, 0, 0)));
		Details.setMaximumSize(new Dimension(360, 290));
		Details.setPreferredSize(new Dimension(380, 85));
		contentPane.add(Details);
		Details.setLayout(null);

		JLabel lblIP = new JLabel("IP Address");
		lblIP.setBounds(23, 75, 83, 24);
		Details.add(lblIP);

		txtIPAddress = new JTextField();
		txtIPAddress.setText("127.0.0.1");
		txtIPAddress.setColumns(10);
		txtIPAddress.setBounds(116, 75, 219, 24);
		Details.add(txtIPAddress);

		JLabel lblPort = new JLabel("IP Port");
		lblPort.setBounds(23, 105, 83, 24);
		Details.add(lblPort);

		txtPort = new JTextField();
		txtPort.setText("3306");
		txtPort.setColumns(10);
		txtPort.setBounds(116, 105, 219, 24);
		Details.add(txtPort);

		JLabel lblusername = new JLabel("User Name");
		lblusername.setBounds(23, 135, 83, 24);
		Details.add(lblusername);

		txtUserName = new JTextField();
		txtUserName.setText("root");
		txtUserName.setColumns(10);
		txtUserName.setBounds(116, 135, 219, 24);
		Details.add(txtUserName);

		JLabel lblPasword = new JLabel("Pasword");
		lblPasword.setBounds(23, 165, 83, 24);
		Details.add(lblPasword);

		txtPassword = new JTextField();
		txtPassword.setText("root");
		txtPassword.setColumns(10);
		txtPassword.setBounds(116, 165, 219, 24);
		Details.add(txtPassword);

		JLabel lblDatabaeName = new JLabel("Databae Name");
		lblDatabaeName.setBounds(23, 195, 83, 24);
		Details.add(lblDatabaeName);

		txtDBName = new JTextField();
		txtDBName.setText("testdb");
		txtDBName.setColumns(10);
		txtDBName.setBounds(116, 195, 219, 24);
		Details.add(txtDBName);

		txt_StorageName = new JTextField();
		txt_StorageName.setText("Storage Name");
		txt_StorageName.setColumns(10);
		txt_StorageName.setBounds(116, 45, 219, 24);
		Details.add(txt_StorageName);

		JButton btnNewButton_2 = new JButton("Save");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DBConnection dbconn = new DBConnection();

				dbconn.setDbName(txtDBName.getText());
				dbconn.setDbUser(txtUserName.getText());
				dbconn.setDbPassword(txtPassword.getText());
				dbconn.setIp(txtIPAddress.getText());
				dbconn.setPort(txtPort.getText());
				dbconn.setStorageName(txt_StorageName.getText());
				dbconn.setDbType((DatabaseType)jcbx_dbType.getSelectedItem());
				selectedDQObj.setDbConnection(dbconn);

				dArch.getElements().set(selectedRowIndex, selectedDQObj);
				populateTable(dArch);
			}
		});
		btnNewButton_2.setBounds(115, 254, 83, 25);
		Details.add(btnNewButton_2);

		JLabel lblNewLabel = new JLabel("Storage Name");
		lblNewLabel.setBounds(23, 45, 83, 24);
		Details.add(lblNewLabel);

		jcbx_dbType = new JComboBox();
		jcbx_dbType.setBounds(116, 225, 219, 24);
		Details.add(jcbx_dbType);

		loadDBType();
		JLabel lblDatabaeType = new JLabel("Databae Type");
		lblDatabaeType.setBounds(23, 225, 83, 24);
		Details.add(lblDatabaeType);

		JButton btnNewButton_2_1 = new JButton("Test Connectivity");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConnection dbConn = selectedDQObj.getDbConnection();
				// MySQLConnection myConn=(MySQLConnection) dbConn;
				if (dbConn.openConnection()) {
					JOptionPane.showMessageDialog(null, "Connection Successfuly");
				}
			}
		});
		btnNewButton_2_1.setBounds(208, 254, 126, 25);
		Details.add(btnNewButton_2_1);

		JButton btnNewButton_1 = new JButton("Database Setting");
		btnNewButton_1.setBounds(23, 11, 312, 24);
		Details.add(btnNewButton_1);

		// populateTable();

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(5, 513, 1032, 60);
		panel_2.setMaximumSize(new Dimension(825, 60));
		panel_2.setPreferredSize(new Dimension(825, 60));
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JButton btnConvertSapml = new JButton("Generat GE Testing Code");
		btnConvertSapml.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnConvertSapml.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConvertSapml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File workingDirectory = new File(System.getProperty("user.dir"));
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Choose a directory to save your file >>>");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setCurrentDirectory(workingDirectory);
				int userSelection = fileChooser.showSaveDialog(null);
				File fileToSave = null;
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					fileToSave = fileChooser.getSelectedFile();
					System.out.println("Save as file: " + fileToSave.getAbsolutePath());
					if (fileToSave.isDirectory()) {
						System.out.println("You selected the directory: " + fileToSave);
					}
				}
				File mainCodeTemplateFile = new File("./othersFiles/MainCode.txt");
				File tableCodeTemplateFile = new File("./othersFiles/TableCode.txt");
				File newPythonFile = new File(fileToSave+"\\DataQualityCheck.py");
				
				GenerateMainClass obj = new GenerateMainClass();

				String mainFiledata = "";
				String tableCodeData="";
				try {
					mainFiledata = FileUtils.readFileToString(mainCodeTemplateFile);
					tableCodeData = FileUtils.readFileToString(tableCodeTemplateFile);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				mainFiledata = obj.addDataSourceCode(selectedDQObj.getDbConnection(), mainFiledata);
				String tableCode="";
				String allTablesCode="";
				//for (int i = 0; i < tables.keySet().size(); i++) 
				for (Entry<String, DBTable> entry : tables.entrySet())	
				{
					String key = entry.getKey();
					DBTable table = entry.getValue();
					tableCode=tableCodeData;
					tableCode = obj.addTable_Columns(table, tableCode);

					System.out.println("Table Name:" + table.getTbaleName());
					for (int j = 0; j < table.getColumns().size(); j++) {
						System.out.println("Column Name>>> " + table.getColumns().get(j).getColumnName());
						tableCode = obj.addExpectations(table, tableCode);
						
					}
					allTablesCode+=tableCode;
					tableCode="";
				}
				
				try {
					mainFiledata=mainFiledata.replace("{TableCode}", allTablesCode);
					FileUtils.writeStringToFile(newPythonFile, mainFiledata);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				GenericGenerator.generatePythonCode("./othersFiles/DataBasesTypeTemplate.txt",fileToSave+"\\databases.py");
				GenericGenerator.generatePythonCode("./othersFiles/ExpectationUtilClassTemplate.txt",fileToSave+"\\expectationUtiles.py");		
				GenericGenerator.generatePythonCode("./othersFiles/BtachClassTemplate.txt",fileToSave+"\\batches.py");		
				GenericGenerator.generatePythonCode("./othersFiles/CheckPointConfigClassTemplate.txt",fileToSave+"\\checkpoints.py");
				GenericGenerator.generatePythonCode("./othersFiles/DataSourceClassTemplate.txt",fileToSave+"\\datasources.py");
				GenericGenerator.generatePythonCode("./othersFiles/ExpectationSuiteClassTemplate.txt",fileToSave+"\\expectationsuites.py");

			}
		});
		btnConvertSapml.setBounds(10, 8, 1012, 42);
		panel_2.add(btnConvertSapml);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(373, 223, 211, 290);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton_1_1 = new JButton("Load Tables");
		btnNewButton_1_1.setBounds(16, 11, 180, 24);
		panel.add(btnNewButton_1_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 47, 180, 232);
		panel.add(scrollPane);

		jList_Tables = new JList();
		jList_Tables.setToolTipText("Tables");
		jList_Tables.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(jList_Tables);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DBConnection dbConn = selectedDQObj.getDbConnection();
				MetaData md = new MetaData();
				ArrayList<String> tableList = null;
				try {
					tableList = (ArrayList<String>) md.getTables(dbConn);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DefaultListModel dlm = new DefaultListModel();
				for (String col : tableList) {
					dlm.addElement(col.toString());
				}
				jList_Tables.setModel(dlm);
			}
		});

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(594, 223, 211, 290);
		contentPane.add(panel_3);

		btn_LoadCol = new JButton("Load Columns");
		btn_LoadCol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get selected Tables
				// System.out.println( jList_Tables.getSelectedValue());
				// System.out.println(jlist_Col.getSelectedValues());
				String selectedCol = (String) jList_Tables.getSelectedValue();
				try {
					ArrayList<String> cols = (ArrayList<String>) new MetaData()
							.getColumnList(selectedDQObj.getDbConnection(), selectedCol);
					DefaultListModel dlm = new DefaultListModel();
					for (String col : cols) {
						dlm.addElement(col.toString());
					}
					jlist_Col.setModel(dlm);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_LoadCol.setBounds(16, 11, 180, 24);
		panel_3.add(btn_LoadCol);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(16, 46, 180, 233);
		panel_3.add(scrollPane_3);

		jlist_Col = new JList();
		jlist_Col.setToolTipText("Columns");
		scrollPane_3.setViewportView(jlist_Col);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(373, 75, 211, 143);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 186, 121);
		panel_4.add(scrollPane_2);

		qm_List = new JList();
		qm_List.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				DefaultListModel model = new DefaultListModel();
				model.clear();
				jlist_ge.setModel(model);
				jlist_ge.removeAll();
				if (qm_List != null && qm_List.getSelectedIndices().length > 0) {

					System.out.println(qm_List.getSelectedValuesList());
					ArrayList<String> ls = (ArrayList<String>) qm_List.getSelectedValuesList();
					LoadMapperJSONFile.loadJSONFile(DAT2GEUI2_copy1.Mapper_QD_GE, ls);

					DefaultListModel dlm = new DefaultListModel();

					for (String gem : LoadMapperJSONFile.GreatExpectationsMethods) {
						dlm.addElement(gem);
					}

					jlist_ge.setModel(dlm);
				}
			}
		});
		qm_List.setToolTipText("All Quality Metrices");
		scrollPane_2.setViewportView(qm_List);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(815, 223, 222, 290);
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		JButton btn_LoadCol_1 = new JButton("Add To Generation");
		btn_LoadCol_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jList_Tables.getSelectedIndices().length > 0 && jlist_Col.getSelectedIndices().length > 0
						&& jlist_ge.getSelectedIndices().length > 0) {
					//if(tables.get((String) jList_Tables.getSelectedValue())
					DBTable dbTable =tables.get((String) jList_Tables.getSelectedValue());
					if(dbTable==null)
						dbTable = new DBTable();
					 String tableName=(String) jList_Tables.getSelectedValue();
					dbTable.setTbaleName(tableName);

					for (int i = 0; i < jlist_Col.getSelectedValuesList().size(); i++) {
						DBColumn col = new DBColumn();
						String colName = jlist_Col.getSelectedValuesList().get(i).toString();
						col.setColumnName(colName);

						for (int j = 0; j < jlist_ge.getSelectedValuesList().size(); j++) {
							String expectationName = jlist_ge.getSelectedValuesList().get(j).toString();
							MethodCondition conditions = new MethodCondition();
							conditions.setDataFormat(txtFormat.getText());
							;
							conditions.setVal(txtVal.getText());
							conditions.setMinVal(txtMinVal.getText());
							conditions.setMaxVal(txtMaxVal.getText());
							conditions.setLength(txtLength.getText());
							conditions.setRegex(txtRegex.getText());
							col.getGreat_Expectation_methods().put(expectationName, conditions);
						}

						dbTable.getColumns().add(col);
					}
					//generationList.add(dbTable);
					tables.put(tableName, dbTable);
				} else {
					// JOptionPane.showMessageDialog(lblNewLabel_1_2, e, Mapper_QD_GE, ABORT, null)
					JOptionPane.showMessageDialog(null, "Make sure to select at lease one table and one column");
				}
			}
		});
		btn_LoadCol_1.setBounds(10, 255, 202, 24);
		panel_5.add(btn_LoadCol_1);

		txtLength = new JTextField();
		txtLength.setColumns(10);
		txtLength.setBounds(78, 217, 134, 24);
		panel_5.add(txtLength);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Length");
		lblNewLabel_1_1_1_1_1.setBounds(10, 217, 57, 24);
		panel_5.add(lblNewLabel_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Value Set");
		lblNewLabel_1_1_1_1.setBounds(10, 182, 57, 24);
		panel_5.add(lblNewLabel_1_1_1_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(78, 182, 134, 24);
		panel_5.add(textField_1);

		txtRegex = new JTextField();
		txtRegex.setColumns(10);
		txtRegex.setBounds(78, 147, 134, 24);
		panel_5.add(txtRegex);

		JLabel lblNewLabel_1_1_1 = new JLabel("Regex");
		lblNewLabel_1_1_1.setBounds(10, 147, 57, 24);
		panel_5.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1 = new JLabel("Max Value");
		lblNewLabel_1_1.setBounds(10, 117, 63, 24);
		panel_5.add(lblNewLabel_1_1);

		txtMaxVal = new JTextField();
		txtMaxVal.setColumns(10);
		txtMaxVal.setBounds(78, 117, 134, 24);
		panel_5.add(txtMaxVal);

		txtMinVal = new JTextField();
		txtMinVal.setColumns(10);
		txtMinVal.setBounds(78, 87, 134, 24);
		panel_5.add(txtMinVal);

		JLabel lblNewLabel_1 = new JLabel("Min Value");
		lblNewLabel_1.setBounds(10, 87, 57, 24);
		panel_5.add(lblNewLabel_1);

		JLabel lblNewLabel_1_2 = new JLabel("Value");
		lblNewLabel_1_2.setBounds(10, 57, 57, 24);
		panel_5.add(lblNewLabel_1_2);

		txtVal = new JTextField();
		txtVal.setColumns(10);
		txtVal.setBounds(78, 57, 134, 24);
		panel_5.add(txtVal);

		txtFormat = new JTextField();
		txtFormat.setColumns(10);
		txtFormat.setBounds(78, 22, 134, 24);
		panel_5.add(txtFormat);

		JLabel lblFormat = new JLabel("Format");
		lblFormat.setBounds(10, 22, 57, 24);
		panel_5.add(lblFormat);

		JLabel lblNewLabel_2 = new JLabel("Expectation Conditions");
		lblNewLabel_2.setBounds(40, 4, 134, 14);
		panel_5.add(lblNewLabel_2);

		JPanel pnl_Expectations = new JPanel();
		pnl_Expectations.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_Expectations.setBounds(594, 75, 443, 143);
		contentPane.add(pnl_Expectations);
		pnl_Expectations.setLayout(null);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(16, 11, 417, 117);
		pnl_Expectations.add(scrollPane_4);

		jlist_ge = new JList();
		scrollPane_4.setViewportView(jlist_ge);
		jlist_ge.setToolTipText("Columns");
		loadQualityMetrics();

	}

	private void loadQualityMetrics() {
		// TODO Auto-generated method stub
		// qm_List.setModel(new DefaultListModel<>(QualityMetrics.values()));

		DefaultListModel dlm = new DefaultListModel();
		QualityMetrics[] qms = QualityMetrics.values();
		for (QualityMetrics qm : qms) {
			dlm.addElement(qm.toString());
		}
		qm_List.setModel(dlm);

	}

	private void selectQualityMetrics() {
		// TODO Auto-generated method stub
		// qm_List.setModel(new DefaultListModel<>(QualityMetrics.values()));

		ArrayList<QualityMetrics> ls = (ArrayList<QualityMetrics>) selectedDQObj.getQualityMetrics();
		System.out.println(ls);
		int[] selectedMetrics = new int[ls.size()];
		for (int i = 0; i < ls.size(); i++) {
			selectedMetrics[i] = ls.get(i).getIndex();
		}
		qm_List.setSelectedIndices(selectedMetrics);

	}

	protected void checkSecondFilter(ArrayList<String> filter) {
		// this.filteredPies.clear();

	}

//	protected ArrayList<String> getCheckedRows() {
//		// TODO Auto-generated method stub
//		ArrayList<String> ls=new ArrayList<String>();
//		for (int i = 0; i < dArch.getElements().size(); i++) {
//			try
//			{
//		     Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 2).toString());
//
//		     if (isChecked) {
//		        //get the values of the columns you need.
//		    	 ls.add(table.getValueAt(i, 1).toString());
//		    } else {
//		        System.out.printf("Row %s is not checked \n", i);
//		    }
//		     }catch(Exception ex){
//		    	 System.out.println("Value is "+table.getValueAt(i, 2));
//		     }
//		}
//		System.out.println(ls.size());
//		return ls;
//	}

//	private void populateTableDefault() {
//		String[][] groupArr = new String[1][3];
//
//		//for (int i = 0; i < getFilteredPies().size(); i++) {
//			groupArr[0][0] =  "";
//			groupArr[0][1] = "";
//			groupArr[0][2] = null;
//
//		//}
//
//		table.setModel(new DefaultTableModel(groupArr, new String[] { "ID",
//				"Name", " " }) {
//			Class[] columnTypes = new Class[] { Object.class, Object.class,
//					Boolean.class };
//
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//		});
//	}

	private void deleteAllRows(final DefaultTableModel model) {

		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);

		}
	}

	@SuppressWarnings("serial")
	public void populateTable(DataArchitecture da) {

		Object[][] groupArr = new Object[da.getElements().size()][4];

		for (int i = 0; i < da.getElements().size(); i++) {
			groupArr[i][0] = da.getElements().get(i).getName();
			groupArr[i][1] = da.getElements().get(i).getType();

//			JPanel temp= new JPanel();
//			
//			JButton dbSettings =new JButton("DB Setting");
//			dbSettings.setBounds(2, 2, 100, 42);
//			JButton LoadTables =new JButton("Load Tables");
//			LoadTables.setBounds(45, 2, 100, 42);
//			temp.add(dbSettings);			
//			temp.add(LoadTables);
//			
//			groupArr[i][3] = temp;

		}
		storageTable.setModel(new DefaultTableModel(groupArr, new String[] { "Storage Name", "Describe" }));

		// populateTableDefault();
		// deleteAllRows((DefaultTableModel) table.getModel());

//		String[][] groupArr = new String[getFilteredPies().size()][3];
//
//		for (int i = 0; i < getFilteredPies().size(); i++) {
//			groupArr[i][0] = i + "";
//			groupArr[i][1] = getFilteredPies().get(i).getName();
//			groupArr[i][2] = null;
//
//		}

//		table.setModel(new DefaultTableModel(groupArr, new String[] { "ID",
//				"Name", " " }) {
//			Class[] columnTypes = new Class[] { Object.class, Object.class,
//					Boolean.class };
//
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//		});

	}

	public ArrayList<String> getFilterls() {
		return filterls;
	}

	public void setFilterls(ArrayList<String> filterls) {
		this.filterls = filterls;
	}

	public DataArchitecture getdArch() {
		return dArch;
	}

	public void setdArch(DataArchitecture dArch) {
		this.dArch = dArch;
	}

	public DataQualityElement getSelectedDQObj() {
		return selectedDQObj;
	}

	public void setSelectedDQObj(DataQualityElement dqObj) {
		this.selectedDQObj = dqObj;
	}

	private class RowListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				return;
			}

		}

	}

	private void loadDBType() {
		// jcbx_dbType.setModel(new DefaultComboBoxModel<>([]));
		jcbx_dbType.setModel(new DefaultComboBoxModel(DatabaseType.values()));

	}
}
