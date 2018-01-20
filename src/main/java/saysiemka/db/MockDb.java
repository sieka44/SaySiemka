package saysiemka.db;

import java.util.LinkedList;

public class MockDb {
    static LinkedList<String> users;

    public MockDb(){
        users = new LinkedList<>();
        users.add("zbychu");
        users.add("krzychu");
        users.add("a");
        users.add("admin");
        users.add("b");
        users.add("Grzegorz Brzęczyszczykiewicz");
    }

    public static boolean contains(String name, String password){
        return users.contains(name);
    }

    public static boolean add(String name, String password) {
        if (!contains(name, password)) {
            users.addLast(name);
            return true;
        }
        else {
            return false;
        }
    }
}
