package saysiemka.server.serverDb;

import java.util.LinkedList;

public class MockDb {
    LinkedList<User> users;

    public MockDb(){
        users = new LinkedList<>();
        users.add(new User("a","a"));
        users.add(new User("b","b"));
        users.add(new User("c","c"));
    }

    public boolean contains(String name, String password){
        return users.contains(new User(name,password));
    }

    public boolean signIn(String name, String password) {
        if (users.contains(new User(name,password)))return false;
        users.add(new User(name,password));
        return true;
    }
}
