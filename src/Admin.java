import java.util.HashMap;

public class Admin {
    private HashMap<String, User> usernameToUser;

    public Admin(){
        this.usernameToUser = new HashMap<>();
    }

    public void addUser(User u){
        this.usernameToUser.put(u.getUsername(), u);
    }

    public void deleteUser(User u){
        this.usernameToUser.remove(u.getUsername(),u);
    }

    public User authenticateUser(String username, String password){
        return this.usernameToUser.containsKey(username) && this.usernameToUser.get(username).getPassword().equals(password)
                ? this.usernameToUser.get(username)
                : null;
    }
}
