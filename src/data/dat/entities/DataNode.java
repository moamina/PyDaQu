package data.dat.entities;

import java.util.List;

public class DataNode {
	
	private String name;
	private String source;
	private String target;
	private List<DataPort> ports;
	private List<DataQualityElement> dataElements;
	
	public DataNode() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<DataPort> getPorts() {
		return ports;
	}

	public void setPorts(List<DataPort> ports) {
		this.ports = ports;
	}

	public List<DataQualityElement> getDataElements() {
		return dataElements;
	}

	public void setDataElements(List<DataQualityElement> dataElements) {
		this.dataElements = dataElements;
	}
	
	

}
