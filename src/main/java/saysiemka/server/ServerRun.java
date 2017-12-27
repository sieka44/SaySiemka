package saysiemka.server;

public class ServerRun {
    public static void main(String[] args) {
        int port = 8818;
        Server server = new Server(port);
        server.start();
    }

}
