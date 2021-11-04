package kanzi.serializer;

import java.io.BufferedReader;
import java.io.FileReader;

import org.json.JSONObject;

public class JsonReader {
    private String file;
    private BufferedReader reader;
    
    public JsonReader(String file) {
        try {
            this.file = file;
            reader = new BufferedReader(new FileReader(this.file));
        } catch (Exception e) {
            System.out.println("Error constructing JsonReader");
            e.printStackTrace();
        }
    }

    public JSONObject getNextJsonObject() {
        String jsonString = getNextLine();
        if (jsonString == null)
            return null;

        JSONObject obj = new JSONObject(jsonString);
        return obj;
    }
    
    public String getNextLine() {
        String ret = null;
        try {
            ret = reader.readLine();
        } catch (Exception e) {
            System.out.println("Error getting next line");
            e.printStackTrace();
        }
        return ret;
    }

    public void close() {
        try {
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
