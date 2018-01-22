package saysiemka.server;

public class ServerMain {

    private Server server;

    public ServerMain(int port) {
        server = new Server(port);
    }

    public static void main(String[] args) {
        int port = 8100;
        new ServerMain(port);
    }

}
