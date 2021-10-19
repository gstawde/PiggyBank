import java.util.HashMap;

/***
 *The Admin class handles keeping track of and authenticating Users of the App
 */
public class Admin {
    //Instance Variable and Constructor
    private HashMap<String, User> usernameToUser;
    public Admin(){
        this.usernameToUser = new HashMap<>();
    }

    //Methods

    /***
     * Adds User "u" to HashMap with User "u"'s username
     * @param u
     * @return True if added, false if not
     */
    public boolean addUser(User u){
        if(this.usernameToUser.containsKey(u.getUsername())) {
            System.out.println("User with this username already exists");
            return false;
        }

        this.usernameToUser.put(u.getUsername(), u);
        return true;
    }

    /***
     * Delete a User with username "username"
     * @param username
     * @return true if deleted, false if not
     */
    public boolean deleteUser(String username){
       User u = this.usernameToUser.get(username);
       return this.usernameToUser.remove(username,u);
    }

    /***
     * Authenticate User given "username" and "password"
     * @param username
     * @param password
     * @return User if username and password are a correct combination, Null if not
     */
    public User authenticateUser(String username, String password){
        return this.usernameToUser.containsKey(username) && this.usernameToUser.get(username).getPassword().equals(password)
                ? this.usernameToUser.get(username)
                : null;
    }

    /***
     * Get method of HashMap
     * @return this.HashMap
     */
    public HashMap<String, User> getUsernameToUser(){return this.usernameToUser;}
}
//Shivam Amin | shivamamin4@gmail.com