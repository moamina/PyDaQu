package data.generator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import data.utils.FileUtility;

public class GenericGenerator {

	
	public static void generatePythonCode(String templateLocation,String newPythonFileName)
	{
		File templateFile = new File(templateLocation);
		try {
			String data = FileUtils.readFileToString(templateFile);
			FileUtility.createAndWriteToFile(newPythonFileName, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {

		GenericGenerator.generatePythonCode("./othersFiles/ExpectationUtilClassTemplate.txt","expectationUtiles.py");		
		GenericGenerator.generatePythonCode("./othersFiles/BtachClassTemplate.txt","batches.py");		
		GenericGenerator.generatePythonCode("./othersFiles/CheckPointConfigClassTemplate.txt","checkpoints.py");
		GenericGenerator.generatePythonCode("./othersFiles/DataSourceClassTemplate.txt","datasources.py");
		GenericGenerator.generatePythonCode("./othersFiles/ExpectationSuiteClassTemplate.txt","expectationsuites.py");
	}
}
