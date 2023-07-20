package data.dat.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import cap.model.entity.Port;

import data.dat.entities.DataArchitecture;

import data.dat.entities.DataQualityElement;
import data.dat.entities.QualityMetrics;
import data.utils.XMLUtil;

public class XMLParser {

	public static void main(String[] args) {
		XMLParser xp = new XMLParser("./files/2DB.dataarch");
		DataArchitecture da=xp.loadDataFromXML();
		
	}

	private String xmlFile;

	public XMLParser(String xmlFile) {
		// TODO Auto-generated constructor stub
		this.xmlFile = xmlFile;
	}

	public DataArchitecture loadDataFromXML() {
		Document doc = XMLUtil.getDocument(xmlFile);
		if (doc == null)
			return null;

		return FillDataArch(doc);		
	}

	private DataArchitecture FillDataArch(Document doc) {

		DataArchitecture da = DataArchitecture.getDataArchitecture();
		da.getElements().clear();
		// get list of elements as Node
		NodeList nList = doc.getElementsByTagName("behaviouralElements");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element currentElement = (Element) nNode;
				// create new DataQualityElement
				DataQualityElement dataElement = new DataQualityElement();
				// fill attributes of elements
				if (currentElement.getAttribute("xsi:type").equals("DA:VerifyData")) {
					dataElement.setType(currentElement.getAttribute("xsi:type"));
					dataElement.setName(currentElement.getAttribute("name"));
					getDataStorageInfor(dataElement,currentElement,nList);
					addQualityMetrics(currentElement.getElementsByTagName("QualityMetrics"), dataElement);
					da.getElements().add(dataElement);
				}

			}

		}
		return da;
	}

	private void getDataStorageInfor(DataQualityElement dataElement,Element currentElement, NodeList nList) {
		
		int index=Integer.parseInt( currentElement.getAttribute("outgoing").split("@behaviouralElements.")[1]);
		int dbIndex=Integer.parseInt(((Element)nList.item(index)).getAttribute("target").split("@behaviouralElements.")[1]);
		String dbName=((Element)nList.item(dbIndex)).getAttribute("name");
		dataElement.setStorageName(dbName);
		
	}

	private void addQualityMetrics(NodeList qualityMetrics, DataQualityElement dataElement) {
		// TODO Auto-generated method stub
		for (int temp = 0; temp < qualityMetrics.getLength(); temp++) {

			Node nNode = qualityMetrics.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				String metric = eElement.getTextContent();
				if (metric.equals(QualityMetrics.Accuracy.toString()))
					dataElement.getQualityMetrics().add(QualityMetrics.Accuracy);
				else if (metric.equals(QualityMetrics.Completeness.toString()))
					dataElement.getQualityMetrics().add(QualityMetrics.Completeness);
				else if (metric.equals(QualityMetrics.Validity.toString()))
					dataElement.getQualityMetrics().add(QualityMetrics.Validity);
				else if (metric.equals(QualityMetrics.Consistency.toString()))
					dataElement.getQualityMetrics().add(QualityMetrics.Consistency);
				else if (metric.equals(QualityMetrics.Uniqueness.toString()))
					dataElement.getQualityMetrics().add(QualityMetrics.Uniqueness);
				else if (metric.equals(QualityMetrics.Timeliness.toString()))
					dataElement.getQualityMetrics().add(QualityMetrics.Timeliness);
			}
		}
	}

//	private List<String> addReceiverNames(NodeList names,
//			BehaviouralElements e) {
//		// TODO Auto-generated method stub
//		List<String> ls=new ArrayList<String>();
//		for (int i=0;i<names.getLength();i++) {
//			Node node=names.item(i);
//			ls.add(node.getTextContent());
//		}
//		return ls;
//	}

	private void addPorts(NodeList ports, DataQualityElement element) {

		for (int temp = 0; temp < ports.getLength(); temp++) {

			Node nNode = ports.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				// create new Port
//				Port port = new Port();
//				port.setName(eElement.getAttribute("name"));
//				port.setType(eElement.getAttribute("xsi:type"));
//				
//				element.getPorts().add(port);

			}
		}
	}

	public String getXmlFile() {
		return xmlFile;
	}

	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}

}
