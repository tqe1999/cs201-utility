package kanzi.serializer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import net.datastructures.Entry;
import net.datastructures.TreeMap;

public class TreeSerializer implements Serializer {
    private List<String> keyList;
    private TreeMap<Double, List<List<String>>> treeMap;

    private JsonReader reader;
    private BufferedWriter writer;

    public TreeSerializer(String inputFile, String outputFile) {
        keyList = new ArrayList<>();
        treeMap = new TreeMap<Double, List<List<String>>>();
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
        addJsonObjectToTreeMap(obj);
        
        // Get values for subsequent objects
        while ((obj = reader.getNextJsonObject()) != null) {
            addJsonObjectToTreeMap(obj);
        }

        writeToFile();
        cleanUp();
    }

    public void addJsonObjectToTreeMap(JSONObject obj) {
        Iterator<String> keys = obj.keys();
        List<String> objectValues = new ArrayList<>();
        Double numStars = -1.0; // For objects without number of stars

        keys = obj.keys();
        objectValues = new ArrayList<>();
        
        while (keys.hasNext()) {
            String key = keys.next();
            if (key.equals("stars")) {
                if (obj.get(key).equals(""))
                    numStars = -1.0;
                else
                    numStars = Double.valueOf("" + obj.get(key));
            }
            objectValues.add("" + obj.get(key));
        }

        List<List<String>> valueList = treeMap.get(numStars);
        if (valueList == null) {
            valueList = new ArrayList<List<String>>();
        }
        valueList.add(objectValues);
        treeMap.put(numStars, valueList);
    }

    public void populateKeyList(JSONObject obj) {
        Iterator<String> keys = obj.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            keyList.add(key);
        }
    }

    public void writeToFile() {
        try {
            writer.write("" + keyList);
            writer.newLine();

            Iterable<Entry<Double, List<List<String>>>> entryList = treeMap.entrySet();
            for (Entry<Double, List<List<String>>> entry : entryList) {
                Double numStars = entry.getKey();
                writer.write("\"" + numStars + " stars\": {[");
                writer.newLine();
                
                List<List<String>> objectValueList = entry.getValue();
                for (List<String> valueArray : objectValueList) {
                    writer.write("" + valueArray);
                    writer.newLine();
                }
                writer.write("]}");
                writer.newLine();
            }
            
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

        int nameIndex = 0;
        for (String key: keyList) {
            if (key.equals("name"))
                break;
            nameIndex++;
        }

        Iterable<Entry<Double, List<List<String>>>> entryList = treeMap.entrySet();
        for (Entry<Double, List<List<String>>> entry : entryList) {
            List<List<String>> objectValueList = entry.getValue();
            for (List<String> valueArray : objectValueList) {
                String name = valueArray.get(nameIndex);
                businessNameList.add(name);
            }
        }
        
        return businessNameList;
    }

    public List<List<String>> getBusinessNameListWithSpecificStars(Double numStars) {
        return treeMap.get(numStars);
    }

    public List<String> getBusinessWithId(String id) {
        int idIndex = 0;
        for (String key: keyList) {
            if (key.equals("business_id"))
                break;
            idIndex++;
        }

        Iterable<Entry<Double, List<List<String>>>> entryList = treeMap.entrySet();
        for (Entry<Double, List<List<String>>> entry : entryList) {
            List<List<String>> objectValueList = entry.getValue();
            for (List<String> valueArray : objectValueList) {
                String business_id = valueArray.get(idIndex);
                if (business_id.equals(id))
                    return valueArray;
            }
        }

        return null;
    }
}
