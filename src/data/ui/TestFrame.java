package data.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;


public class TestFrame extends JFrame{
	public TestFrame() {
	}

	/**
	 * @param args
	 */
	int numberOfCheckBoxes=0;  //this is used to get the number of items in the combobox
	JCheckBox[] checkBoxList;   //the list that contains the checkboxes
	
	String[] columns=new String[]{"Column 1","Column 2","Column 3"};  //something for the table to display
	Object data[][]={
			{true,new Integer(10),new String("String 1")},
			{false,new Integer(20),new String("String 2")},
			{true,new Integer(30),new String("String 3")}
	};

	JTable table;
	JPanel firstPanel=new JPanel();  //this is just for gui to look better
	JPanel secondPanel=new JPanel();
	
	@SuppressWarnings("unchecked")
	public void initialize(){
		
		table=new JTable(data,columns);
		this.setTitle("Tabel");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,200);
		
		
		firstPanel.setLayout(new BoxLayout(firstPanel,BoxLayout.Y_AXIS));
		firstPanel.setBorder(new TitledBorder("DBTable"));
		
		
		secondPanel.setLayout(new BoxLayout(secondPanel,BoxLayout.Y_AXIS));
		secondPanel.setBorder(new TitledBorder("GUI Items"));
		
		firstPanel.add(table.getTableHeader(),BorderLayout.PAGE_START); //if you don't use this line, the column names won't be displayed in the window

		firstPanel.add(table);
						
		ArrayList<String> columnNames=new ArrayList<String>(); //list for containg the column names
		
		for(int i=0;i<table.getColumnCount();i++){
		columnNames.add(table.getColumnName(i));
		}
		
		JComboBox<?> comboBox=new JComboBox(columnNames.toArray());		
		numberOfCheckBoxes=comboBox.getItemCount();  //get the number of items in combobox
		
		secondPanel.add(comboBox);
		
		checkBoxes();  //method for creating the checkboxes
		
		getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		getContentPane().add(firstPanel);
		getContentPane().add(secondPanel);
		
		
	}
	
	void checkBoxes(){
		checkBoxList=new JCheckBox[numberOfCheckBoxes];
		
		for(int i=0;i<checkBoxList.length;++i){
			checkBoxList[i]=new JCheckBox("CheckBox #"+i); //create the check boxes and add them to the jframe
			secondPanel.add(checkBoxList[i]);
						
		}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestFrame program=new TestFrame();
			program.initialize();
	}

}