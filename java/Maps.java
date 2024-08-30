import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Maps {
    public static void main(String args[]) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(100, "Amit");
        map.put(101, "Vijay");
        map.put(102, "Rahul");

        // Returns a Set view of the mappings contained in this map
        map.entrySet()
                // Returns a sequential Stream with this collection as its source
                .stream()
                // Sorted according to the provided Comparator (by default ascending)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                // Performs an action for each element of this stream
                .forEach(System.out::println);

        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);

        for (Map.Entry m : map.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }
    }
}