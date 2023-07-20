package data.dat.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.connection.DBConnection;

public class DataQualityElement {
	
	private String name;
	private String type;
	private DataPort fromDataPorts;
	private DataPort toDataPorts;
	private String outgoing;
	private String incoming;
	
	private String storageName;
	private String storageType;
	
	List<QualityMetrics> qualityMetrics;
	private DBConnection dbConnection;
	List<String> tablesNames;

	//HashMap<K, V>

	public DataQualityElement() {
		// TODO Auto-generated constructor stub
		qualityMetrics=new ArrayList<QualityMetrics>();
		tablesNames=new ArrayList<String>();
		dbConnection=new DBConnection();
	}

	
	public DataPort getFromDataPorts() {
		return fromDataPorts;
	}

	public void setFromDataPorts(DataPort fromDataPorts) {
		this.fromDataPorts = fromDataPorts;
	}

	public DataPort getToDataPorts() {
		return toDataPorts;
	}

	public void setToDataPorts(DataPort toDataPorts) {
		this.toDataPorts = toDataPorts;
	}

	public List<QualityMetrics> getQualityMetrics() {
		return qualityMetrics;
	}

	public void setQualityMetrics(List<QualityMetrics> qualityMetrics) {
		this.qualityMetrics = qualityMetrics;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public String getOutgoing() {
		return outgoing;
	}

	public void setOutgoing(String outgoing) {
		this.outgoing = outgoing;
	}

	public String getIncoming() {
		return incoming;
	}

	public void setIncoming(String incoming) {
		this.incoming = incoming;
	}


	public String getStorageName() {
		return storageName;
	}


	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}


	public String getStorageType() {
		return storageType;
	}


	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}
	
	public List<String> getTablesNames() {
		return tablesNames;
	}


	public void setTablesNames(List<String> columnsNames) {
		this.tablesNames = columnsNames;
	}


	public DBConnection getDbConnection() {
		return dbConnection;
	}


	public void setDbConnection(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	
}
