package data.dat.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoadMapperJSONFile {

	public static HashSet<String> GreatExpectationsMethods=new HashSet<>();
	public static void loadJSONFile(String mapper,ArrayList<String> qualityD){
		JSONParser parser = new JSONParser();
		GreatExpectationsMethods.clear();
        try {     
            Object obj = parser.parse(new FileReader(mapper));

            JSONObject jsonObject =  (JSONObject) obj;
            if(jsonObject!=null)
            {
            	for(int i=0;i<qualityD.size();i++)
            	{
            		 JSONArray qd = (JSONArray) jsonObject.get(qualityD.get(i));
            		 if(qd!=null)
            			 GreatExpectationsMethods.addAll(qd);
                     //System.out.println(accuracy);
            	}
            }
            System.out.println(GreatExpectationsMethods);
           
//
//            String city = (String) jsonObject.get("city");
//            System.out.println(city);
//
//            String job = (String) jsonObject.get("job");
//            System.out.println(job);
//
//            // loop array
//            JSONArray cars = (JSONArray) jsonObject.get("cars");
//            Iterator<String> iterator = cars.iterator();
//            while (iterator.hasNext()) {
//             System.out.println(iterator.next());
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
//		ArrayList<String> ls=new ArrayList<>();
//		ls.add("Completeness");
//		ls.add("Uniqueness");
//		LoadMapperJSONFile.loadJSONFile("./othersFiles/mapper_dqd_ge.json", ls);
	}
}
