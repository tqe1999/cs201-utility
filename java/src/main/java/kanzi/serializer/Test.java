package kanzi.serializer;

import java.util.List;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        // String test_file = "./data/yelp_test.json";
        String actual_file = "./data/dataset.json";
        List<String> temp;
        
        /** ArrayList */
        Log.startExperiment("ArrayList", "Transpose");
        ArrayListSerializer arrayListSerializer = new ArrayListSerializer(
            actual_file, 
            "./output/ArrayListTranposed.txt"
        );
        arrayListSerializer.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();
        
        Log.startExperiment("ArrayList", "Get List of Businesses' Names");
        temp = arrayListSerializer.getBusinessNameList();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("ArrayList", "Get Businesses' Names with Specific Stars");
        temp = arrayListSerializer.getBusinessNameListWithSpecificStars(4.5);
        Log.logMemoryUsage();
        Log.endExperiment();
        

        /** Tree */
        Log.startExperiment("Tree", "Transpose");
        TreeSerializer treeSerializer = new TreeSerializer(
            actual_file, 
            "./output/TreeTransposed.txt"
        );
        treeSerializer.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("Tree", "Get List of Businesses' Names");
        temp = treeSerializer.getBusinessNameList();
        Log.endExperiment();

        Log.startExperiment("Tree", "Get Businesses' Names with Specific Stars");
        temp = treeSerializer.getBusinessNameListWithSpecificStars(4.5);
        Log.logMemoryUsage();
        Log.endExperiment();

        /** HashMap with chaining (Hashmap of arraylists) */
        Log.startExperiment("HashMap Chaining", "Transpose");
        HashMapChainingSerializer hashMapChainingSerializer = new HashMapChainingSerializer(actual_file, "./output/HashMapTransposedChaining.txt");
        hashMapChainingSerializer.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Chaining", "Get List of Businesses' Names");
        List<String> temp3 = hashMapChainingSerializer.getBusinessNameList();
        Log.endExperiment();

        Log.startExperiment("HashMap Chaining", "Get Businesses' Names with Specific Stars");
        temp = hashMapChainingSerializer.getBusinessNameListWithSpecificStars(4.5);
        Log.logMemoryUsage();
        Log.endExperiment();

        /**Pure hashmap with collision */
        Log.startExperiment("HashMap", "Transpose");
        HashMapSerializer hashMapSerializer = new HashMapSerializer(actual_file, "./output/HashMapTransposed.txt");
        hashMapSerializer.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap", "Get List of Businesses' Names");
        List<String> temp4 = hashMapSerializer.getBusinessNameList();
        Log.endExperiment();

        Log.startExperiment("HashMap", "Get Businesses' Names with Specific Stars");
        temp = hashMapSerializer.getBusinessNameListWithSpecificStars(4.5);
        Log.logMemoryUsage();
        Log.endExperiment();

        // /**Varied load factor of hashmap */
        Log.startExperiment("HashMap Load factor 0.5", "Transpose");
        HashMapSerializer hashMapSerializerLoadFactor = new HashMapSerializer(actual_file, "./output/HashMapTransposedLoadFactor0.5.txt", new HashMap<>(16, 0.5f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Load factor 0.6", "Transpose");
        hashMapSerializerLoadFactor = new HashMapSerializer(actual_file, "./output/HashMapTransposedLoadFactor0.6.txt", new HashMap<>(16, 0.6f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Load factor 0.7", "Transpose");
        hashMapSerializerLoadFactor = new HashMapSerializer(actual_file, "./output/HashMapTransposedLoadFactor0.7.txt", new HashMap<>(16, 0.7f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Load factor 0.8", "Transpose");
        hashMapSerializerLoadFactor = new HashMapSerializer(actual_file, "./output/HashMapTransposedLoadFactor0.8.txt", new HashMap<>(16, 0.8f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Load factor 0.9", "Transpose");
        hashMapSerializerLoadFactor = new HashMapSerializer(actual_file, "./output/HashMapTransposedLoadFactor0.9.txt", new HashMap<>(16, 0.9f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Load factor 1.0", "Transpose");
        hashMapSerializerLoadFactor = new HashMapSerializer(actual_file, "./output/HashMapTransposedLoadFactor1.0.txt", new HashMap<>(16, 1.0f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        /**Varied hashcode function */

        Log.startExperiment("HashMap Constant hashcode ", "Transpose");
        HashFunctionHashMapSerializer hashMapSerializerConstantHashCode= new HashFunctionHashMapSerializer(actual_file, "./output/HashMapTransposedConstantHashCode.txt");
        hashMapSerializerConstantHashCode.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();
    }
}
