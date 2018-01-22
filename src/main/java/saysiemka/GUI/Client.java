package saysiemka.GUI;

import java.io.IOException;
import java.net.*;

public class Client {
    private DatagramSocket socket;

    private String name, address;
    private int points;
    private int port;
    private InetAddress ip;
    private Thread send;
    private int ID = -1;

    public boolean isGreater() {
        return points > 0;
    }

    public int getAndClearPoints() {
        Integer outcome = points;
        points = 0;
        return outcome;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public Client(String name, String address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
        points = 0;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public boolean openConnection(String address) {
        try {
            socket = new DatagramSocket();
            ip = InetAddress.getByName(address);
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String receive() {
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message = new String(packet.getData());
        return message;
    }

    public void send(final byte[] data) {
        send = new Thread("Send") {
            public void run() {
                DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        send.start();
    }

    public void close() {
        new Thread() {
            public void run() {
                synchronized (socket) {
                    socket.close();
                }
            }
        }.start();
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

}