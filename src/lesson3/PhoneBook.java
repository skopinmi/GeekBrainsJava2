package lesson3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class PhoneBook {

    private HashMap<String, ArrayList<String>> array = new HashMap<>();

    public void get (String name) {
        System.out.println(name);

        if (array.containsKey(name)) {
            ArrayList<String> phones = array.get(name);
            System.out.println(phones);
        } else {
            System.out.println("Нет такого имени.");
        }
    }

    public void add (String name, String phone) {
//        ArrayList<String> phones;
//        if (array.containsKey(name)) {
//            phones = array.get(name);
//        } else {
//            phones = new ArrayList<String>();
//        }
        ArrayList<String> phones = array.containsKey(name) ? array.get(name) : new ArrayList<String>();
        phones.add(phone);
        array.put(name, phones);
    }
}
class PhoneBookTest {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
/*
    заполнение PhoneBook
 */
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            phoneBook.add("Person" + random.nextInt(10), "8(945)" + random.nextInt(10000000));
        }
/*

 */
        phoneBook.get("Person1");
        phoneBook.get("Person420");
        phoneBook.get("Person3");
        phoneBook.get("Person4");
    }
}