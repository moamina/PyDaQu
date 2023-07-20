package data.codegeneration.mapping;

import java.util.ArrayList;
import java.util.Hashtable;

import data.dat.entities.QualityMetrics;

public class DBColumn {
	private String columnName;
	
	
	//key : method name
	//value : constrains
	private Hashtable<String, MethodCondition> great_Expectation_methods=new Hashtable<String, MethodCondition>();
	
	public DBColumn() {
		// TODO Auto-generated constructor stub
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public Hashtable<String, MethodCondition> getGreat_Expectation_methods() {
		return great_Expectation_methods;
	}
	public void setGreat_Expectation_methods(Hashtable<String, MethodCondition> great_Expectation_methods) {
		this.great_Expectation_methods = great_Expectation_methods;
	}
	
	

}
