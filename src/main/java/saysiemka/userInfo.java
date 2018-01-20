package saysiemka;

public class userInfo {
    private static final int PORT = 8100;
    private static String login;
    private static String password;
    private static Boolean loggedIn;
    private static Boolean signUp;


    public static Boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        userInfo.loggedIn = loggedIn;
    }

    public static int getPORT() {
        return PORT;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLoginAndPass(String login, String password,Boolean signUp) {
        userInfo.signUp = signUp;
        userInfo.login = login;
        userInfo.password = password;
        loggedIn = null;
    }

    public static String getPassword() {
        return password;
    }

    public static void clean() {
        userInfo.login = null;
        userInfo.password = null;
    }
}
