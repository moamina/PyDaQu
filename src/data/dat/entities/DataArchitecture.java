package data.dat.entities;

import java.util.ArrayList;
import java.util.List;

public class DataArchitecture {

	private static DataArchitecture daObject;
	private List<DataQualityElement> elements=null;
	
	private DataArchitecture() {
		elements=new ArrayList<DataQualityElement>();
	}
	
	public static DataArchitecture getDataArchitecture()
	{
		if(daObject==null)
			daObject= new DataArchitecture();
		return daObject;
	}

	public List<DataQualityElement> getElements() {
		return elements;
	}

	public void setElements(List<DataQualityElement> elements) {
		this.elements = elements;
	}
	
}
