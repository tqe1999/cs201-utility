package kanzi.serializer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.*;

import org.json.JSONObject;

public class LRUHashGridNovelSerializer implements Serializer {
    private List<String> keyList;
    private HashMap<String, Integer> indexMap;
    private HashMap<String, ArrayList<String>> objectValueMap;

    private JsonReader reader;
    private BufferedWriter writer;

    class DoublyLinkedListNode {
        DoublyLinkedListNode prev;
        DoublyLinkedListNode next;
        String key;
        HashMap <String, String> value;
      }

    private Map<String, DoublyLinkedListNode> cache = new HashMap<>();

    private DoublyLinkedListNode header;
    private DoublyLinkedListNode trailer;

    
    public LRUHashGridNovelSerializer(String inputFile, String outputFile) {
        keyList = new ArrayList<>();
        objectValueMap = new HashMap<>();
        indexMap = new HashMap<>();
        reader = new JsonReader(inputFile);
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

        header = new DoublyLinkedListNode();
        trailer = new DoublyLinkedListNode();

        header.next = trailer;
        trailer.prev = header;

    }


    public void transpose() {
        JSONObject obj = reader.getNextJsonObject();
        int counter = 0;
        populateKeyList(obj);
        addJsonObjectToObjectValueList(obj);
        indexMap.put("" + obj.get("business_id"), counter);
        addJsonObjectToDoublyLinkedList(obj);
        counter++;
        // Get values for subsequent objects
        while ((obj = reader.getNextJsonObject()) != null) {
            addJsonObjectToObjectValueList(obj);
            indexMap.put("" + obj.get("business_id"), counter);
            addJsonObjectToDoublyLinkedList(obj);
            counter++;
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

    public void addJsonObjectToDoublyLinkedList(JSONObject obj) {
        DoublyLinkedListNode addedNode = new DoublyLinkedListNode();

        addedNode.key = "" + obj.get("business_id");
        addedNode.value = getBusinessIdInfoWithoutShift("" + obj.get("business_id"));

        addNodeToFront(addedNode);
        cache.put(addedNode.key, addedNode);
    }

    public void writeToFile() {
        try {
            writer.write("{");
            for (Map.Entry<String, ArrayList<String>> entry : objectValueMap.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
            writer.write("{");
            for (Map.Entry<String, Integer> entry : indexMap.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
            }
            writer.write("}");
            writer.write("{");
            for (Map.Entry<String, DoublyLinkedListNode> entry : cache.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
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

    private void addNodeToFront(DoublyLinkedListNode node) {
        node.next = header.next;
        node.prev = header;

        header.next.prev = node;
        header.next = node;
    }

    private void moveToFront(DoublyLinkedListNode node) {
        removeNode(node);
        addNodeToFront(node);
    }

    private void removeNode(DoublyLinkedListNode node) {
        DoublyLinkedListNode futureNode = node.next;
        DoublyLinkedListNode previousNode = node.prev;
        
        futureNode.prev = previousNode;
        previousNode.next = futureNode;
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

    //get specific business id

    public HashMap<String, String> getBusinessIdInfo(String businessId) {
        DoublyLinkedListNode node = cache.get(businessId);
        if (node == null) {
            return null;
        }
        moveToFront(node);
        return getBusinessIdInfoWithoutShift(businessId);

    }

    private HashMap<String, String> getBusinessIdInfoWithoutShift(String businessId)  {
        HashMap<String, String> businessIdInfo = new HashMap<>();

        int reference = indexMap.get(businessId);

        for (String key : objectValueMap.keySet()) { //this is done in O(1) as there are only fixed number of attributes
            String value = objectValueMap.get(key).get(reference);
            businessIdInfo.put(key, value);
        }
        return businessIdInfo;
    }

    //get top K most accessed businesses
    
    public ArrayList<HashMap<String, String>> getTopKMostAccessedBusinesses(int kTopMostAccessed) {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        DoublyLinkedListNode temp = header;
        while (kTopMostAccessed > 0 && temp.next != trailer) {
            temp = temp.next;
            result.add(temp.value);
            kTopMostAccessed--;

        }

        return result;
    }

    
}
