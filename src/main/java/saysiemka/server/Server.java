package saysiemka.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{
    private final int serverPort;
    private ArrayList<ServerWorker> workerList = new ArrayList<>();
    public Server(int port) {
        serverPort=port;
    }

    public List<ServerWorker> getWorkerList() {
        return workerList;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            while(true){
                System.out.println("Waiting for clients");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accept connection "+clientSocket);
                ServerWorker worker = new ServerWorker(this,clientSocket);
                worker.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeWorker(ServerWorker worker) {
        workerList.remove(worker);
    }
}
