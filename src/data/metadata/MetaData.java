package data.metadata;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import data.connection.DBConnection;
import data.connection.imp.MySQLConnection;

public class MetaData {
	
		
	public  List<String> getTableList(DBConnection dbConnection)
	{
		DatabaseMetaData metaData;
		List<String> tableList=new ArrayList<String>();
		try {
			metaData = dbConnection.getDbConnection().getMetaData();
		
	      String[] types = {"TABLE"};
	      
	      //Retrieving the columns in the database
	      ResultSet tables = metaData.getTables(null, null, "%", types);
	      while (tables.next()) {
	         System.out.println(tables.getString("TABLE_NAME"));
	         tableList.add(tables.getString("TABLE_NAME"));
	      }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return tableList;
	}
	
	public List<String> getTables(DBConnection dbConnection) throws SQLException
	{
		DatabaseMetaData dbMeta = dbConnection.getDbConnection().getMetaData();
		//con.getCatalog() returns database name
		ResultSet rs = dbMeta.getTables(dbConnection.getDbConnection().getCatalog(), "", null, new String[]{"TABLE"});
		ArrayList<String> tables = new ArrayList<String>();
		while(rs.next()) {
		    String tableName = rs.getString("TABLE_NAME");
		    tables.add(tableName);
		}
		return tables;
	}
	
	public List<String> getColumnList(DBConnection dbConnection,String tableName) throws SQLException
	{
		Statement stmt = dbConnection.getDbConnection().createStatement();
		List<String> columnsList=new ArrayList<String>();
		ResultSet rs = stmt.executeQuery("select * from "+tableName+" limit 1");
	      //Retrieving the ResultSetMetadata object
	      ResultSetMetaData rsMetaData = rs.getMetaData();
	      System.out.println("List of column names in the current table: ");
	      //Retrieving the list of column names
	      int count = rsMetaData.getColumnCount();
	      for(int i = 1; i<=count; i++) {
	         System.out.println(rsMetaData.getColumnName(i));
	         columnsList.add(rsMetaData.getColumnName(i));
	      }
	      return columnsList;
	}
	
	public static void main(String[] args) {
		
		MySQLConnection conn=new MySQLConnection();
		MetaData md=new MetaData();
		md.getTableList( conn);
		try {
			md.getColumnList(conn,"radusergroup");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
