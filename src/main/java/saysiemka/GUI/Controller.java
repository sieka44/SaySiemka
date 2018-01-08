package saysiemka.GUI;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

public class Controller {
    @FXML
    private TextField chatMessageFiled;

    @FXML
    private TextArea chatTextArea;

    private final DatagramSocket socket;

    private String myName;

    public Controller() throws SocketException {
        super();
        this.socket = new DatagramSocket(8080);
    }

    public String getTextFieldText() {
        return this.chatMessageFiled.getText();
    }

    @FXML
    protected void sendMessage() {
        try {
            this.broadcastDatagram(this.getTextFieldText());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addMessageToTextArea("Me", this.getTextFieldText());

        this.chatMessageFiled.setText("");
    }

    public void addMessageToTextArea(String computerName, String message) {
        this.chatTextArea.appendText(computerName + "> " + message + "\n");
    }

    // --- Socket Code ------------------------------------------------------

    public void broadcastDatagram(String text)
            throws IOException {
        byte[] outbuf = new byte[1024];

        String msg = getMyName() + "|" + text;

        for (int i = 1; i < 254; i++) {
            sendPacket(this.socket, msg.getBytes(), msg.length(), i);
        }
    }

    private void sendPacket(final DatagramSocket socket, byte[] message,
                            int len, int i) throws UnknownHostException, SocketException {
        final DatagramPacket request = new DatagramPacket(message, len,
                InetAddress.getByName("192.168.0." + i), 8080);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket.send(request);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void runit() throws Exception {
        // prompt the user to enter their name

        System.out.println("Communicator 1.0");

        setMyName(InetAddress.getLocalHost().getHostName());

        startListen(socket);
    }

    private void startListen(final DatagramSocket socket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    receivePacket(socket);
                }
            }
        }).start();
    }

    private void receivePacket(DatagramSocket socket) {
        byte[] inbuf = new byte[1024]; // default size

        try {
            DatagramPacket packet = new DatagramPacket(inbuf, inbuf.length);
            socket.receive(packet);

            if (!packet.getAddress().getHostAddress()
                    .equals(InetAddress.getLocalHost().getHostAddress())) {
                String msg = new String(packet.getData(), 0, packet.getLength());
                int pos = msg.indexOf("|");
                String name = pos >= 0 ? msg.substring(0, pos) : packet
                        .getAddress().getHostName();
                String text = pos >= 0 ? msg.substring(pos + 1) : msg;
                this.addMessageToTextArea(name, text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }
}