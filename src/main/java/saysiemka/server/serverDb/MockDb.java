package saysiemka.server.serverDb;

import java.util.LinkedList;

public class MockDb {
    LinkedList<User> users;

    public MockDb(){
        users = new LinkedList<>();
        users.add(new User("a","a"));
        users.add(new User("b","b"));
        users.add(new User("c","c"));
        addPoints("a",10);
        addPoints("b",16);
        addPoints("c",6);

    }

    public int getPoint(String userName){
        for (User u: users){
            if (u.getName().equals(userName))return u.getPoints();
        }
        return 0;
    }

    public void addPoints(String name, int points){
        for(User user : users){
            if(user.getName().equals(name.trim())){
                user.addPoints(points);
            }
        }
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
