package saysiemka;

public class userInfo {
    private static final int PORT = 8100;
    private static String login;
    private static String password;
    private static Boolean logedIn;

    public static Boolean isLogedIn() {
        return logedIn;
    }

    public static void setLogedIn(boolean logedIn) {
        userInfo.logedIn = logedIn;
    }

    public static int getPORT() {
        return PORT;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLoginAndPass(String login, String password) {
        userInfo.login = login;
        userInfo.password = password;
        logedIn = null;
    }

    public static String getPassword() {
        return password;
    }

    public static void clean() {
        userInfo.login = null;
        userInfo.password = null;
    }
}
