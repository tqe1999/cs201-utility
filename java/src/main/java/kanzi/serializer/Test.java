package kanzi.serializer;

import java.util.List;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        String input_file = "./data/dataset.json";
        List<String> temp;
        
        /** ArrayList */
        Log.startExperiment("ArrayList", "Transpose");
        ArrayListSerializer arrayListSerializer = new ArrayListSerializer(
            input_file, 
            "./output/ArrayListTranposed.txt"
        );
        arrayListSerializer.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();
        
        Log.startExperiment("ArrayList", "Get List of Businesses' Names");
        arrayListSerializer.getBusinessNameList();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("ArrayList", "Get Businesses' Names with Specific Stars");
        arrayListSerializer.getBusinessNameListWithSpecificStars(4.5);
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("ArrayList", "Get Businesses With id");
        arrayListSerializer.getBusinessWithId("4XDuQFiM8G9l0LARivzmKw");
        Log.logMemoryUsage();
        Log.endExperiment();

        arrayListSerializer = null;
        

        /** Tree */
        Log.startExperiment("Tree", "Transpose");
        TreeSerializer treeSerializer = new TreeSerializer(
            input_file, 
            "./output/TreeTransposed.txt"
        );
        treeSerializer.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("Tree", "Get List of Businesses' Names");
        treeSerializer.getBusinessNameList();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("Tree", "Get Businesses' Names with Specific Stars");
        treeSerializer.getBusinessNameListWithSpecificStars(4.5);
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("Tree", "Get Businesses With id");
        treeSerializer.getBusinessWithId("4XDuQFiM8G9l0LARivzmKw");
        Log.logMemoryUsage();
        Log.endExperiment();

        treeSerializer = null;

        /** HashMap with chaining (Hashmap of arraylists) */
        Log.startExperiment("HashMap Chaining", "Transpose");
        HashMapChainingSerializer hashMapChainingSerializer = new HashMapChainingSerializer(input_file, "./output/HashMapTransposedChaining.txt");
        hashMapChainingSerializer.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Chaining", "Get List of Businesses' Names");
        hashMapChainingSerializer.getBusinessNameList();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Chaining", "Get Businesses' Names with Specific Stars");
        hashMapChainingSerializer.getBusinessNameListWithSpecificStars(4.5);
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Chaining", "Get specific business_id details");
        hashMapChainingSerializer.getBusinessIdInfo("4XDuQFiM8G9l0LARivzmKw");
        Log.logMemoryUsage();
        Log.endExperiment();


        hashMapChainingSerializer = null;

        /**Pure hashmap with collision */
        Log.startExperiment("HashMap", "Transpose");
        HashMapSerializer hashMapSerializer = new HashMapSerializer(input_file, "./output/HashMapTransposed.txt");
        hashMapSerializer.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap", "Get List of Businesses' Names");
        hashMapSerializer.getBusinessNameList();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap", "Get Businesses' Names with Specific Stars");
        hashMapSerializer.getBusinessNameListWithSpecificStars(4.5);
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap", "Get specific business_id details");
        hashMapSerializer.getBusinessIdInfo("4XDuQFiM8G9l0LARivzmKw");
        Log.logMemoryUsage();
        Log.endExperiment();

        hashMapSerializer = null;

        // /**Varied load factor of hashmap */

        Log.startExperiment("HashMap Load factor 0.1", "Transpose");
        HashMapSerializer hashMapSerializerLoadFactor = new HashMapSerializer(input_file, "./output/HashMapTransposedLoadFactor0.1.txt", new HashMap<>(16, 0.1f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Load factor 0.2", "Transpose");
        hashMapSerializerLoadFactor = new HashMapSerializer(input_file, "./output/HashMapTransposedLoadFactor0.2.txt", new HashMap<>(16, 0.2f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Load factor 0.3", "Transpose");
        hashMapSerializerLoadFactor = new HashMapSerializer(input_file, "./output/HashMapTransposedLoadFactor0.3.txt", new HashMap<>(16, 0.3f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Load factor 0.4", "Transpose");
        hashMapSerializerLoadFactor = new HashMapSerializer(input_file, "./output/HashMapTransposedLoadFactor0.4.txt", new HashMap<>(16, 0.4f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Load factor 0.5", "Transpose");
        hashMapSerializerLoadFactor = new HashMapSerializer(input_file, "./output/HashMapTransposedLoadFactor0.5.txt", new HashMap<>(16, 0.5f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Load factor 0.6", "Transpose");
        hashMapSerializerLoadFactor = new HashMapSerializer(input_file, "./output/HashMapTransposedLoadFactor0.6.txt", new HashMap<>(16, 0.6f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Load factor 0.7", "Transpose");
        hashMapSerializerLoadFactor = new HashMapSerializer(input_file, "./output/HashMapTransposedLoadFactor0.7.txt", new HashMap<>(16, 0.7f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Load factor 0.75", "Transpose");
        hashMapSerializerLoadFactor = new HashMapSerializer(input_file, "./output/HashMapTransposedLoadFactor0.75.txt", new HashMap<>(16, 0.75f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Load factor 0.8", "Transpose");
        hashMapSerializerLoadFactor = new HashMapSerializer(input_file, "./output/HashMapTransposedLoadFactor0.8.txt", new HashMap<>(16, 0.8f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Load factor 0.9", "Transpose");
        hashMapSerializerLoadFactor = new HashMapSerializer(input_file, "./output/HashMapTransposedLoadFactor0.9.txt", new HashMap<>(16, 0.9f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashMap Load factor 1.0", "Transpose");
        hashMapSerializerLoadFactor = new HashMapSerializer(input_file, "./output/HashMapTransposedLoadFactor1.0.txt", new HashMap<>(16, 1.0f));
        hashMapSerializerLoadFactor.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        hashMapSerializerLoadFactor = null;

        /**Novel/Innovative Data structure */

        Log.startExperiment("HashGrid", "Transpose");
        HashGridNovelSerializer hashMapChainingNovelSerializer = new HashGridNovelSerializer(input_file, "./output/HashGridNovel.txt");
        hashMapChainingNovelSerializer.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashGrid", "Get List of Businesses' Names");
        hashMapChainingNovelSerializer.getBusinessNameList();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashGrid", "Get Businesses' Names with Specific Stars");
        hashMapChainingNovelSerializer.getBusinessNameListWithSpecificStars(4.5);
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("HashGrid", "Get specific business_id details");
        hashMapChainingNovelSerializer.getBusinessIdInfo("tCbdrRPZA0oiIYSmHG3J0w");
        Log.logMemoryUsage();
        Log.endExperiment();

        hashMapChainingNovelSerializer = null;

        Log.startExperiment("LRUHashGrid", "Transpose");
        LRUHashGridNovelSerializer hashMapLinkedGridNovelSerializer = new LRUHashGridNovelSerializer(input_file, "./output/HashLinkedGridNovel.txt");
        hashMapLinkedGridNovelSerializer.transpose();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("LRUHashGrid", "Get List of Businesses' Names");
        hashMapLinkedGridNovelSerializer.getBusinessNameList();
        Log.logMemoryUsage();
        Log.endExperiment();

        Log.startExperiment("LRUHashGrid", "Get Businesses' Names with Specific Stars");
        hashMapLinkedGridNovelSerializer.getBusinessNameListWithSpecificStars(4.5);
        Log.logMemoryUsage();
        Log.endExperiment();


        Log.startExperiment("LRUHashGrid", "Get top 2 most frequent businesses");
        hashMapLinkedGridNovelSerializer.getTopKMostAccessedBusinesses(2);
        Log.logMemoryUsage();
        Log.endExperiment();

        //Get specific business id as a curious user

        Log.startExperiment("LRUHashGrid", "Get specific business_id details");
        hashMapLinkedGridNovelSerializer.getBusinessIdInfo("tCbdrRPZA0oiIYSmHG3J0w");
        Log.logMemoryUsage();
        Log.endExperiment();

        //Now we try to call the most frequent businesses again

        Log.startExperiment("LRUHashGrid", "Get top 2 most frequent businesses");
        hashMapLinkedGridNovelSerializer.getTopKMostAccessedBusinesses(2);
        Log.logMemoryUsage();
        Log.endExperiment();

        hashMapLinkedGridNovelSerializer = null;


        
    }
}
