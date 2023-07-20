package data.connection.imp;

import java.sql.DriverManager;

import data.connection.Connectionable;
import data.connection.DBConnection;
import data.connection.DatabaseType;




public class MySQLConnection extends DBConnection implements Connectionable{


	public MySQLConnection() {

		this.setIp( "localhost");
		this.setDbName("testdb");
		this.setDbUser("root"); 
		this.setDbPassword("root"); 
		this.setDbType(DatabaseType.MySQL);
		this.setPort("3306");
		try {
			this.setUrl("jdbc:mysql://" + this.getIp() + ":"+this.getPort()+"/" + this.getDbName() + "?user="
					+ this.getDbUser() + "&password=" + this.getDbPassword()
					+ "&useUnicode=true&characterEncoding=UTF8");  
			// ?user=root&password=somepass&useUnicode=true&characterEncoding=UTF-8
			//driver loader
			this.setDbConnection(DriverManager.getConnection(this.getUrl()));
			Class.forName("com.mysql.cj.jdbc.Driver");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MySQLConnection(String ip,String port, String dbName, String dbUser, String dbPassword) {
		
		this.setIp( ip);
		this.setDbName(dbName);
		this.setDbUser(dbUser); 
		this.setDbPassword(dbPassword); 
		this.setPort(port);
		
	}


//	public void prepareConnection()
//	{
//		
//	}
	
	@Override
	public boolean closeConnection() {
		try {
			if (this.getDbConnection() != null) {
				this.getDbConnection().close();
				this.setDbConnection(null);
				
			}
			return true;
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return false;
	}	

	public static void main(String[] args) {
		MySQLConnection gd=new MySQLConnection();
		gd.closeConnection();
		System.out.println("True");
	}

	@Override
	public boolean openConnection() {
		try {
			this.setUrl("jdbc:mysql://" + this.getIp() + ":"+this.getPort()+"/" + this.getDbName() + "?user="
					+ this.getDbUser() + "&password=" + this.getDbPassword()
					+ "&useUnicode=true&characterEncoding=UTF8");
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.setDbConnection(DriverManager.getConnection(this.getUrl()));
			return true;
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
}