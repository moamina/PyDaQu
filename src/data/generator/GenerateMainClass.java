package data.generator;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

import data.codegeneration.mapping.DBColumn;
import data.codegeneration.mapping.DBTable;
import data.codegeneration.mapping.MethodCondition;
import data.connection.DBConnection;

public class GenerateMainClass {
	
	public String columnVar="{column}";
	public String DatabaseConfiguration="getMySQLDataCourceConfig";
	public String addDataSourceCode(DBConnection dbconn,String fileData)
	{		
		//{UserName},{Password},{IPAddress},{Port},{DatabaseName}
		fileData = fileData.replace("{UserName}","\""+dbconn.getDbUser()+"\"");
		fileData = fileData.replace("{Password}","\""+dbconn.getDbPassword()+"\"");
		fileData = fileData.replace("{IPAddress}","\""+dbconn.getIp()+"\"");
		fileData = fileData.replace("{Port}","\""+dbconn.getPort()+"\"");
		fileData = fileData.replace("{DatabaseName}","\""+dbconn.getDbName()+"\"");
		fileData = fileData.replace("{DatabaseType}",""+dbconn.getDbType().getDBType()+"");
		
		fileData = fileData.replace("{DatabaseConfigurationMethod}",this.DatabaseConfiguration);
		return fileData;
	}
	public String addTable_Columns(DBTable table,String fileData)
	{
		//tableName='sensingdata'
		//columnsList=["id","sensorName","val","timestamp"]
		fileData = fileData.replace("{TableName}","'"+table.getTbaleName()+"'");
//		String cols="[";
//		for (DBColumn col : table.getColumns()) {
//			cols+="\""+col.getColumnName()+"\"";
//			cols+=",";
//		}
//		cols = cols.substring(0, cols.length() - 1);
//		//cols.r
//		cols+="]";
//		fileData = fileData.replace("{Columns}",cols);
		
		return fileData;
	}
	public String addExpectations(DBTable table,String data)
	{
		File templateExpectationFile = new File("./othersFiles/ExpectationsTemplate.txt");
		String template;
		String allCode="";
		try {
			template = FileUtils.readFileToString(templateExpectationFile);
			String copytemplate="";
			
			for (DBColumn col : table.getColumns()) {
				
				allCode+="\n\t#Expectations For Column ["+col.getColumnName()+"]";
				for (Map.Entry<String, MethodCondition> entry : col.getGreat_Expectation_methods().entrySet()) {
					copytemplate=template;
					copytemplate=copytemplate.replace("{expectationMethod}", entry.getKey());
					String params=this.columnVar;
					MethodCondition condition=entry.getValue();
					if(condition!=null)
					{
						if(condition.getVal()!=null && !condition.getVal().equals("")) {
							params+=","+condition.getVal();
							
						}
						else if(condition.getMinVal()!=null && !condition.getMinVal().equals("")) {
							params+=","+condition.getMinVal();
							if(condition.getMaxVal()!=null && !condition.getMaxVal().equals("")) {
								params+=","+condition.getMaxVal();
							}
						}
						
						else if(condition.getDataFormat()!=null && !condition.getDataFormat().equals("")) {
							params+=","+condition.getDataFormat();
						}
						else if(condition.getLength()!=null && !condition.getLength().equals("")) {
							params+=","+condition.getLength();
						}
						else if(condition.getRegex()!=null && !condition.getRegex().equals("")) {
							params+=","+condition.getRegex();
						}
					}
					
					copytemplate=copytemplate.replace("{ExpectationParameter}", params);
					copytemplate=copytemplate.replace(this.columnVar, "\""+col.getColumnName()+"\"");
					allCode+=copytemplate+"\n";
				}
				//break;
			}
			data=data.replace("{ExpectationCode}", allCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		return data;
	}
	
	public static void main(String[] args) {
		File templateFile = new File("./othersFiles/MainCodeClassTemplat.txt");
		File newPythonFile = new File("./othersFiles/myfile.py");
        try {
        	GenerateMainClass obj=new GenerateMainClass();
        	
            String data = FileUtils.readFileToString(templateFile);
            DBConnection dbConn=new DBConnection();
            dbConn.setDbName("test_db");
            dbConn.setDbUser("root");
            dbConn.setDbPassword("root");
            dbConn.setIp("localhost");
            dbConn.setPort("3306");
            data=obj.addDataSourceCode(dbConn, data);
            
            DBTable table=new DBTable();
            table.setTbaleName("sensingdata");
            DBColumn col1=new DBColumn();
            col1.setColumnName("id");
            col1.getGreat_Expectation_methods().put("expect_column_values_to_not_be_null", new MethodCondition());
            MethodCondition md=new MethodCondition();
            md.setMinVal("1");
            md.setMaxVal("2");
            col1.getGreat_Expectation_methods().put("expect_column_proportion_of_unique_values_to_be_between", md);
			
            DBColumn col2=new DBColumn();
            col2.setColumnName("val");
            col2.getGreat_Expectation_methods().put("expect_column_proportion_of_unique_values_to_be_between", md);
            col2.getGreat_Expectation_methods().put("expect_column_values_to_not_be_null", new MethodCondition());
			table.getColumns().add(col1);
			table.getColumns().add(col2);
			data=obj.addTable_Columns(table, data);
            			
			data=obj.addExpectations(table,data);
			
            FileUtils.writeStringToFile(newPythonFile, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
