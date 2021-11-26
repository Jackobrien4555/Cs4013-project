public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Returns a username.
    public String getUsername() {
        return username;
    }

    // Returns a password.
    public String getPassword() {
        return password;
    }
}
