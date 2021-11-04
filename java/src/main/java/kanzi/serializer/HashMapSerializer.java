package kanzi.serializer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.*;

import org.json.JSONObject;

public class HashMapSerializer implements Serializer {
    private List<String> keyList;
    private HashMap<String, HashMap<String, String>> objectValueMap;

    private JsonReader reader;
    private BufferedWriter writer;
    
    public HashMapSerializer(String inputFile, String outputFile) {
        System.out.println("SERIALIZING...");
        System.out.println("Input file: " + inputFile);
        System.out.println("Serialized file: " + outputFile);
        keyList = new ArrayList<>();
        objectValueMap = new HashMap<>();
        reader = new JsonReader(inputFile);
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMapSerializer(String inputFile, String outputFile, HashMap<String, HashMap<String, String>> loadFactorHashMap) {
        keyList = new ArrayList<>();
        objectValueMap = loadFactorHashMap;
        reader = new JsonReader(inputFile);
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
 


    public void transpose() {
        JSONObject obj = reader.getNextJsonObject();

        addJsonObjectToObjectValueList(obj);

        // Get values for subsequent objects
        while ((obj = reader.getNextJsonObject()) != null) {
            addJsonObjectToObjectValueList(obj);
        }

        writeToFile();
        cleanUp();
    }


    public void addJsonObjectToObjectValueList(JSONObject obj) {
        Iterator<String> keys = obj.keys();
        String business_id = "" + obj.get("business_id");
        objectValueMap.put(business_id, new HashMap<>());
        while (keys.hasNext()) {
            String key = keys.next(); 
            if (!key.equals("business_id")) {
                String result = "" + obj.get(key);
                objectValueMap.get(business_id).put(key, result);
            }
        }
    }

    public void writeToFile() {
        try {
            writer.write("{");
            for (Map.Entry<String, HashMap<String, String>> entry : objectValueMap.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
            writer.write("}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cleanUp() {
        try {
            writer.flush();
            writer.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getBusinessNameList() {
        List<String> businessNameList = new ArrayList<>();
        for (Map.Entry<String, HashMap<String, String>> entry : objectValueMap.entrySet()) {
            businessNameList.add(entry.getValue().get("name"));
        }
        return businessNameList;
    }

    public List<String> getBusinessNameListWithSpecificStars(Double numStars) {
        List<String> businessNameList = new ArrayList<>();
        for (Map.Entry<String, HashMap<String, String>> entry : objectValueMap.entrySet()) {
            if (Double.valueOf((String) entry.getValue().get("stars")).equals(numStars) ) {
                businessNameList.add(entry.getValue().get("name"));
            }
        }

        return businessNameList;
    }
}
