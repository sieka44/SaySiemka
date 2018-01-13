package saysiemka.server;

public class ServerMain {

	private int port;
	private Server server;

	public ServerMain(int port) {
		this.port = port;
		server = new Server(port);
	}

	public static void main(String[] args) {
		int port = 8101;
		new ServerMain(port);
	}

}
