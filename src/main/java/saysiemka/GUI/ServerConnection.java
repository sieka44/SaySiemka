package saysiemka.GUI;

import javafx.application.Platform;
import saysiemka.userInfo;

import java.util.Arrays;

public class ServerConnection implements Runnable{
    Controller controller;
    private Thread run, listen;
    private Client client;

    private Boolean loggedIn;
    private boolean running = false;

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void login(String name, String password, String address, int port) {
        client = new Client(name, address, port);
        boolean connect = client.openConnection(address);
        if (!connect) {
            System.err.println("Connection failed!");
            console("Connection failed!");
        }
        console("Attempting a connection to " + address + ":" + port + ", user: " + name);
        String connection = "/c/ " + name + " /p/ " + password + " /e/";
        client.send(connection.getBytes());
        running = true;
        run = new Thread(this, "Running");
        run.start();
    }

    public void signUp(String name, String password, String address, int port){
        client = new Client(name,address,port);
        boolean connect = client.openConnection(address);
        if (!connect) {
            System.err.println("Connection failed!");
            console("Connection failed!");
        }
        console("Attempting a connection to " + address + ":" + port + ", user: " + name);
        String connection = "/s/ " + name + " /p/ " + password + " /e/";
        client.send(connection.getBytes());
        running = true;
        run = new Thread(this, "Running");
        run.start();
    }

    public void run() {
        listen();
    }


    public void send(String message, boolean isMessage) {//True for message
        if (message.equals("")) return;
        if (isMessage) {
            message = client.getName() + ": " + message;
            message = "/m/" + message + "/e/";
        }
        client.send(message.getBytes());
    }

    public void listen() {
        listen = new Thread("Listen") {
            public void run() {
                while (running) {
                    String message = client.receive();
                    if (message.startsWith("/c/")) {
                        client.setID(Integer.parseInt(message.split("/c/|/e/")[1]));
                        console("Successfully connected to server! ID: " + client.getID());
                        loggedIn = true;
                    } else if (message.startsWith("/f/")) {
                        client.setID(Integer.parseInt(message.split("/f/|/e/")[1]));
                        console("Failed to connect to server!");
                        running = false;
                        loggedIn = false;
                        client.close();
                    } else if (message.startsWith("/m/")) {
                        if(controller!=null) {
                            String text = message.substring(3);
                            text = text.split("/e/")[0];
                            controller.addText(text);
                        }
                    } else if (message.startsWith("/i/")) {
                        String text = "/i/" + client.getID() + "/e/";
                        send(text, false);
                    } else if (message.startsWith("/u/")) {
                        String[] u = message.split("/u/|/n/|/e/");
                        controller.userUpdate(Arrays.copyOfRange(u, 1, u.length - 1));
                    } else if (message.startsWith("/d/")) {
                        disconnect();
                    }
                    //if(setButton && chatTextArea!=null)setEventHandler();
                }
            }
        };
        listen.start();
    }


    public void disconnect(){
        String disconnect = "/d/" + client.getID() + "/e/";
        send(disconnect, false);
        loggedIn = false;
        running = false;
        client.close();
    }

    public void console(String message) {
        System.out.println("console: "+message);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setLoggedIn(Boolean s){
        loggedIn = s;
    }
//    public void setEventHandler(){
//        chatMessageFiled.getParent().getScene().getWindow().setOnHiding(event -> Platform.runLater(() -> {
//            disconnect();
//            System.exit(0);
//        }));
//    }
}
