package saysiemka.Client;

public interface MessageListener {
    void onMessage(String fromLogin, String msgBody);
}
