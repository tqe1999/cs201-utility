package kanzi.serializer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

public class ArrayListSerializer implements Serializer {
    private List<String> keyList;
    private List<List<String>> objectValueList;

    private JsonReader reader;
    private BufferedWriter writer;
    
    public ArrayListSerializer(String inputFile, String outputFile) {
        keyList = new ArrayList<>();
        objectValueList = new ArrayList<>();
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
        }
    }

    public void addJsonObjectToObjectValueList(JSONObject obj) {
        Iterator<String> keys = obj.keys();
        List<String> objectValues = new ArrayList<>();
        
        while (keys.hasNext()) {
            String key = keys.next();
            objectValues.add("" + obj.get(key));
        }
        objectValueList.add(objectValues);
    }

    public void writeToFile() {
        try {
            writer.write("" + keyList);

            for (List<String> valueArray : objectValueList) {
                writer.newLine();
                writer.write("" + valueArray);
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

        for (List<String> objectValues : objectValueList) {
            String name = objectValues.get(nameIndex);
            businessNameList.add(name);
        }

        return businessNameList;
    }

    public List<String> getBusinessNameListWithSpecificStars(Double numStars) {
        List<String> businessNameList = new ArrayList<>();

        int counterIndex = 0;
        int nameIndex = 0;
        int starsIndex  = 0;
        for (String key: keyList) {
            if (key.equals("name"))
                nameIndex = counterIndex;
            if (key.equals("stars"))
                starsIndex = counterIndex;
            counterIndex++;
        }

        for (List<String> objectValues : objectValueList) {
            Double objectStars = Double.valueOf((String) objectValues.get(starsIndex));
            if (!objectStars.equals(numStars))
                continue;
            
            String name = objectValues.get(nameIndex);
            businessNameList.add(name);
        }
        return businessNameList;
    }
}
