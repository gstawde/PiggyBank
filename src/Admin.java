import java.util.HashMap;

public class Admin {
    private HashMap<String, User> usernameToUser;

    public Admin(){
        this.usernameToUser = new HashMap<>();
    }

    public boolean addUser(User u){
        if(this.usernameToUser.containsKey(u.getUsername())) {
            System.out.println("User with this username already exists");
            return false;
        }

        this.usernameToUser.put(u.getUsername(), u);
        return true;
    }

    public boolean deleteUser(String username){
       User u = this.usernameToUser.get(username);
       return this.usernameToUser.remove(username,u);
    }

    public User authenticateUser(String username, String password){
        return this.usernameToUser.containsKey(username) && this.usernameToUser.get(username).getPassword().equals(password)
                ? this.usernameToUser.get(username)
                : null;
    }

    public HashMap<String, User> getUsernameToUser(){return this.usernameToUser;}
}
