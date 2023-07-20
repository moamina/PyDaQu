package data.codegeneration.mapping;

import java.util.ArrayList;

public class DBTable {
	private String tbaleName;
	private ArrayList<DBColumn>columns=new ArrayList<>();
	
	public DBTable() {
		// TODO Auto-generated constructor stub
	}
	
	public String getTbaleName() {
		return tbaleName;
	}
	public void setTbaleName(String tbaleName) {
		this.tbaleName = tbaleName;
	}
	public ArrayList<DBColumn> getColumns() {
		return columns;
	}
	public void setColumns(ArrayList<DBColumn> columns) {
		this.columns = columns;
	}
	
	

}
