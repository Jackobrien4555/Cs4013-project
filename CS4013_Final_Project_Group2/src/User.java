/**
 * This class is a blueprint for creating a user. It defines a user admin for the reservation system.
 *
 * @author 20238029 Sergiu Mereacre
 * @since 15/11/2021
 */

public class User {

    // Stores the username for the user.
    private final String username;
    // Stores the password for the user.
    private final String password;

    /**
     * Creating an instance of a User.
     * @param username Username.
     * @param password Password.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns a username.
     * @return Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns a password.
     * @return Password.
     */
    public String getPassword() {
        return password;
    }
}
