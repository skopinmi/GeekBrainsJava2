package lesson3;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class MainCollection {
    public static void main(String[] args) {
//        int[] arr = {1,2,3,4};
//        int[] arr2 = new int[5];
//
//        System.arraycopy(arr, 0, arr2, 0, arr.length);
//
//        arr2[4] = 10;
//
//
//        System.out.println(Arrays.toString(arr2));

//        ArrayList<Integer> list = new ArrayList<>();
//
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(null);
//        list.add(4);
//
//      //  list.remove((Integer) 4);
//
//        if (list.contains(2)) {
//            System.out.println("есть 2");
//        }
//
//        System.out.println(list.size());
//        System.out.println(list);


//        ArrayList<String> states = new ArrayList<>();
//
//        states.add("Германия");
//        states.add("Германия");
//        states.add("Франиция");
//        states.add("США");
//        states.add("Россия");

//        for (int i = 0; i < states.size(); i++) {
//            if (states.get(i).equalsIgnoreCase("Германия")) {
//                states.remove(i);
//                i--;
//            }
//        }

//        Iterator<String> iter = states.iterator();
//
//        while (iter.hasNext()) {
//          //  System.out.println(iter.next());
//            if (iter.next().equalsIgnoreCase("Германия")) {
//                iter.remove();
//            }
//        }
//
//       // for (int i = 0, iMax = massiv.lenght(); i < iMax; i++)
//
//
//
//            System.out.println(states);


//        LinkedList<String> ll = new LinkedList<>();
//
//        ll.addLast("z");
//        ll.removeLast();
//
//        ll.addFirst("a");
//        ll.removeFirst();
//
//        ll.add("a");
//        ll.add("b");
//        ll.add("c");
//        ll.add("d");
//
//        ll.get(5);
//
//        System.out.println(ll);

//        String str = "asdfs";
//        System.out.println(str.hashCode());



//        HashMap<Integer, String> hm = new HashMap<>();
//
//        HashMap<String, ArrayList<Integer>> hm1 = new HashMap<>();
//
//        hm.put( 5, "Васька");
//
//        hm.put( 3, "Бобик");
//
//        hm.put( 10, "Васька");
//
//        System.out.println(hm);

//        Set<Map.Entry<String, Integer>> set = hm.entrySet();
//
//        for (Map.Entry<String, Integer> me: set) {
//            System.out.println(me.getKey());
//            System.out.println(me.getValue());
//        }
//
//
//        Integer res = hm.get("Васька");

        //System.out.println(res);


//        Random random = new Random();
//
//        Map<Integer, Integer> hm = new HashMap<>();
//
//        for (int i = 0; i < 100; i++) {
//            int key = random.nextInt(10);
//            Integer res = hm.get(key);
//
////            Integer curent = 0;
////
////            if (res == null) {
////                curent = 1;
////            } else {
////                curent += 1;
////            }
//
//            hm.put(key, res == null ? 1 : res + 1);
//        }
//
//        System.out.println(hm);


//        List<String> al = new ArrayList<>();
//
//        Map<String, String> hm = new HashMap<>();

//        HashSet<String> hs = new HashSet<>();
//
//        hs.add("Омега");
//        hs.add("Альфа");
//        hs.add("Бета");
//        hs.add("Гамма");
//
//        System.out.println(hs);


        TreeSet<Employee> ts = new TreeSet<>();

        ts.add(new Employee(10));
        ts.add(new Employee(20));
        ts.add(new Employee(30));


        System.out.println(ts);


        Map<Employee, Integer> hm = new HashMap<>();

    }
}
