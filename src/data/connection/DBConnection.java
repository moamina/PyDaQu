package data.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import data.connection.imp.MySQLConnection;

public class DBConnection implements Connectionable{
	
	private String storageName;
	private String ip;
	private String port;
	private String dbName;
	private String dbUser;
	private String dbPassword;
	private String url;
	private DatabaseType dbType;
	
	private Connection dbConnection;
	
	public DBConnection() {
		setDbName("testdb");
		setDbPassword("root");
		setDbType(DatabaseType.MySQL);
		setDbUser("root");
		setStorageName("Storage Name");
		setUrl("");
		setIp("127.0.0.1");
		setPort("3306");
		
		// TODO Auto-generated constructor stub
	}
	
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
			//jdbc:sqlserver
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
	public String getUrl() {
		return url;
	}

	public DatabaseType getDbType() {
		return dbType;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDbType(DatabaseType dbType) {
		this.dbType = dbType;
	}

	public String getIp() {
		return ip;
	}

	public String getDbName() {
		return dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}


	public Connection getDbConnection() {
		return dbConnection;
	}


	public void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}


	public String getStorageName() {
		return storageName;
	}


	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}
}
