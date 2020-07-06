package lesson3;


import java.util.*;

public class HomeWork {
    public static void main(String[] args) {

        ArrayList<String> array = createArray(15);
        printUniqueWord(array);
        printCountOfWords(array);
    }

    public static ArrayList<String> createArray (int number) {
        Random random = new Random();
        ArrayList<String> array = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            array.add("Word " + random.nextInt(number));
        }
        return array;
    }

    private static HashMap<String, Integer> putInMap (ArrayList<String> array) {
        HashMap <String, Integer> myMap = new HashMap<>();
        for (String a: array) {
//            if(myMap.containsKey(a)) {
//                myMap.put(a, (myMap.get(a)+1));
//            } else {
//                myMap.put(a, 1);
//            }
            myMap.put(a, myMap.containsKey(a)? myMap.get(a) + 1 : 1 );
        }
        return myMap;
    }

    public static void printUniqueWord (ArrayList<String> array) {
        Set<Map.Entry<String, Integer>> set = putInMap(array).entrySet();
        for (Map.Entry<String, Integer> me : set) {
            System.out.print(me.getKey() + " ");
        }
        System.out.println("");
    }

    public static void printCountOfWords (ArrayList<String> array) {
        System.out.println(putInMap(array));
    }

}
