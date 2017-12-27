package saysiemka.server;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;

public class ServerWorker extends Thread {
    private final Socket clientSocket;
    private final Server server;
    private String login = null;
    private OutputStream outputStream;
    private HashSet<String> topicSet;


    public ServerWorker(Server server, Socket clientSocket){
        this.server = server;
        this.clientSocket = clientSocket;
        topicSet = new HashSet<>();
    }

    @Override
    public void run(){
        try {
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClientSocket() throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        this.outputStream = clientSocket.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null){
            String[] tokens = line.split(" ",3);
            if(tokens != null && tokens.length>0) {
                String cmd = tokens[0];
                if ("quit".equalsIgnoreCase(line) || "logoff".equalsIgnoreCase(line)) {
                    break;
                } else if ("login".equalsIgnoreCase(cmd)){
                    hadleLogin(outputStream,tokens);
                }else if("msg".equalsIgnoreCase(cmd)){
                    handleMessage(tokens);
                }
                else if("join".equalsIgnoreCase(cmd)){
                    handleJoin(tokens);
                }
                else if("leave".equalsIgnoreCase(cmd)){
                    handleLeave(tokens);
                }
                else if("exit".equalsIgnoreCase(cmd)){
                    handleLogOff();
                }
                else {
                    String msg = "unknown" + cmd + "\n";
                    outputStream.write(msg.getBytes());
                }
            }
        }

        clientSocket.close();
    }

    public boolean isMemberOfTopicSet(String topic){
        return topicSet.contains(topic);
    }

    private void handleLeave(String[] tokens){
        if(tokens.length > 1){
            String topic = tokens[1];
            topicSet.remove(topic);
        }
    }

    private void handleJoin(String[] tokens) {
        if(tokens.length > 1){
            String topic = tokens[1];
            topicSet.add(topic);
        }
    }

    //TODO: msg format: msg <user> "message...
    //TODO: msg format: msg <#topic> "message...
    private void handleMessage(String[] tokens) throws IOException {
        String sendTo = tokens[1];
        String body = tokens[2];

        List <ServerWorker> workerList = server.getWorkerList();

        boolean isTopic = sendTo.charAt(0) == '#';

        for (ServerWorker worker:workerList) {
            if(isTopic){
                if(worker.isMemberOfTopicSet(sendTo)){
                    String outMsg = "msg " + sendTo + ":" + login + " " + body + "\n";
                    worker.send(outMsg);
                }
            }else {
                if (sendTo.equals(worker.getLogin())) {
                    String outMsg = "msg " + login + " " + body + "\n";
                    worker.send(outMsg);
                }
            }
        }
    }

    public String getLogin() {
        return login;
    }

    private void hadleLogin(OutputStream outputStream, String[] tokens) throws IOException{
        if (tokens.length == 3){
            String login = tokens[1];
            String password = tokens[2];
            //TODO: not hardcoded users(guest,guest1)
            if ((login.equals("guest") && password.equals("guest"))||(login.equals("guest1") && password.equals("guest1"))){
                outputStream.write("ok login".getBytes());
                this.login = login;
                System.out.println("Log in successful: " + login);

                List<ServerWorker> workerList = server.getWorkerList();

                //display other users to curr user
                for (ServerWorker worker:workerList) {
                    if (this != worker && worker.getLogin()!=null) {
                        String onLineMsg1 = "Already online: " + worker.getLogin();
                        send(onLineMsg1);
                    }
                }
                //display curr user to other users
                for (ServerWorker worker:workerList) {
                    String onLineMsg2 ="New online: " + login + "\n";
                    worker.send(onLineMsg2);
                }
            }else {
                String msg = "error login\n";
                outputStream.write(msg.getBytes());
                System.out.println("Login failed for " + login);
            }
        }
    }

    private void handleLogOff() throws IOException {
        server.removeWorker(this);
        List<ServerWorker> workerList = server.getWorkerList();
        for (ServerWorker worker:workerList) {
            if(this!=worker){
                String onLineMsg2 ="Get offline: " + login + "\n";
                worker.send(onLineMsg2);
            }
        }
    }

    private void send(String msg) throws IOException {
        if (login != null)
            outputStream.write(msg.getBytes());
    }

}
