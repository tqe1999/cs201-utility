package kanzi.serializer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.*;

import org.json.JSONObject;

public class HashMapChainingSerializer implements Serializer {
    private List<String> keyList;
    private HashMap<String, ArrayList<String>> objectValueMap;

    private JsonReader reader;
    private BufferedWriter writer;
    
    public HashMapChainingSerializer(String inputFile, String outputFile) {
        keyList = new ArrayList<>();
        objectValueMap = new HashMap<>();
        reader = new JsonReader(inputFile);
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void transpose() {
        JSONObject obj = reader.getNextJsonObject();

        populateKeyList(obj);
        addJsonObjectToObjectValueList(obj);

        // Get values for subsequent objects
        while ((obj = reader.getNextJsonObject()) != null) {
            addJsonObjectToObjectValueList(obj);
        }

        writeToFile();
        cleanUp();
    }

    public void populateKeyList(JSONObject obj) {
        Iterator<String> keys = obj.keys();
        while (keys.hasNext()) {         
            String key = keys.next();
            keyList.add(key);
            objectValueMap.put(key, new ArrayList<String>());
        }

        
    }

    public void addJsonObjectToObjectValueList(JSONObject obj) {
        Iterator<String> keys = obj.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            objectValueMap.get(key).add("" + obj.get(key));
        }
    }

    public void writeToFile() {
        try {
            writer.write("{");
            for (Map.Entry<String, ArrayList<String>> entry : objectValueMap.entrySet()) {
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
        return objectValueMap.get("name");
    }

    public List<String> getBusinessNameListWithSpecificStars(Double numStars) {
        List<String> businessNameList = new ArrayList<>();

        ArrayList<String> starList = objectValueMap.get("stars");
        ArrayList<String> fullBusinessNameList = objectValueMap.get("name");
        for (int i = 0; i < starList.size(); i++) {
            if (Double.valueOf((String) starList.get(i)).equals(numStars)) {
                businessNameList.add(fullBusinessNameList.get(i));
            }
        }
        return businessNameList;
    }
}
