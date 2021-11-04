package kanzi.serializer;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class SerializerFactory {

  public Serializer getSerializer(Map<String, Object> map) throws NoSuchAlgorithmException{

    String inputFile = (String) map.get("inputName");
    String outputFile = "output/serialized.txt";
    String type = (String) map.get("serializer");
    if (type == null){
      return null;
    }

    if (type.equals("ARRAYLIST"))
      return new ArrayListSerializer(inputFile, outputFile);

    if (type.equals("HASHFUNCTION"))
      return new HashFunctionHashMapSerializer(inputFile, outputFile);

    if (type.equals("HASHGRID"))
      return new HashGridNovelSerializer(inputFile, outputFile);

    if (type.equals("HASHMAPCHAINING"))
      return new HashMapChainingSerializer(inputFile, outputFile);

    if (type.equals("HASHMAP"))
      return new HashMapSerializer(inputFile, outputFile);
      
    throw new NoSuchAlgorithmException("Invalid serializer input: " + type);
  }

}