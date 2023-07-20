package data.connection;

public enum DatabaseType {
	
	MySQL("DataBaseType.MySQL"),
	Postgres("DataBaseType.Postgres"),
	SqlServer(""),
	Oracle("");
	private String dbType="";
	private DatabaseType(String dbType) {
		// TODO Auto-generated constructor stub
		this.dbType=dbType;
	}
	public String getDBType() {
		return dbType;
	}	
	
	
}
