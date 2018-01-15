package saysiemka.db;

import java.util.LinkedList;

public class MockDb {
    LinkedList<String> users;

    public MockDb(){
        users = new LinkedList<>();
        users.add("zbychu");
        users.add("krzychu");
        users.add("a");
        users.add("admin");
        users.add("b");
        users.add("Grzegorz Brzęczyszczykiewicz");
    }

    public boolean contains(String name, String password){
        return users.contains(name);
    }

    
}
