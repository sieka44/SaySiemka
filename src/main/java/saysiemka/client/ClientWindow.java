package saysiemka.client;

import saysiemka.db.MockDb;

import java.util.Arrays;

public class ClientWindow implements Runnable {
	private static final long serialVersionUID = 1L;

	private Thread run, listen;
	private Client client;
	private MockDb db;

	private boolean running = false;

	public ClientWindow(String name, String address, int port) {
		client = new Client(name, address, port);
		boolean connect = client.openConnection(address);
		if (!connect) {
			System.err.println("Connection failed!");
			console("Connection failed!");
		}
		console("Attempting a connection to " + address + ":" + port + ", user: " + name);
		String connection = "/c/" + name + "/e/";
		client.send(connection.getBytes());
		running = true;
		run = new Thread(this, "Running");
		run.start();
	}

	public void run() {
		listen();
	}

	private void send(String message, boolean isMessage) {//True for message
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
					} else if (message.startsWith("/m/")) {
						String text = message.substring(3);
						text = text.split("/e/")[0];
						console(text);
					} else if (message.startsWith("/i/")) {
						String text = "/i/" + client.getID() + "/e/";
						send(text, false);
					} else if (message.startsWith("/u/")) {
						String[] u = message.split("/u/|/n/|/e/");
						userUpdate(Arrays.copyOfRange(u, 1, u.length - 1));
					} else if (message.startsWith("")){

                    } else if (message.startsWith("/d/")) {
                        String disconnect = "/d/" + client.getID() + "/e/";
                        send(disconnect, false);
                        running = false;
                        client.close();
                    }
				}
			}
		};
		listen.start();
	}

	private void userUpdate(String[] users){
	    for(String s : users) System.out.println(s);
    }

	public void console(String message) {
        System.out.println("console: "+message);
	}
}
