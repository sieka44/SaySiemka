package saysiemka.client;

//TODO:for future login-screen
public class Login {
	private static final long serialVersionUID = 1L;

    private void login(String name,String password, String address, int port) {
        new ClientWindow(name, password, address, port);
    }

    public static void main(String[] args) {
        new Login().login("b", null ,"localhost",8101);
    }}
