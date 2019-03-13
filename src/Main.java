import java.util.Map;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(5);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        System.out.println(map.containsKey(2));
        map.remove(2);
        System.out.println(map.get(2));
        map.put(2, 8);
        System.out.println(map.get(2));
        map.put(3, 10);
        System.out.println(map.get(3));
        for(Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        map.clear();
        System.out.println(map.getSize());
    }
}
