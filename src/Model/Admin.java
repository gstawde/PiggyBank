package Model;

import java.util.HashMap;
import java.util.HashSet;

/***
 *The Model.Admin class handles keeping track of and authenticating Users of the App
 */
public class Admin {
    //Instance Variable and Constructor
    private HashSet<User> userList = new HashSet<>();
    private HashMap<String, User> usernameToUser;
    public Admin(){
        this.usernameToUser = new HashMap<>();
    }

    //Methods

    /***
     * Adds Model.User "u" to HashMap with Model.User "u"'s username or if the username is blank
     * @param u
     * @return True if added, false if not
     */
    public boolean addUser(User u){
        if(this.usernameToUser.containsKey(u.getUsername()) || u.getUsername().length() == 0 || u.getPassword().length() == 0) {
            System.out.println("Model.User with this username already exists");
            return false;
        }

        this.usernameToUser.put(u.getUsername(), u);
        this.userList.add(u);
        return true;
    }

    /***
     * Delete a Model.User with username "username"
     * @param username
     * @return true if deleted, false if not
     */
    public boolean deleteUser(String username){
       User u = this.usernameToUser.get(username);
       this.userList.remove(u);
       return this.usernameToUser.remove(username,u);
    }

    /***
     * Authenticate Model.User given "username" and "password"
     * @param username
     * @param password
     * @return Model.User if username and password are a correct combination, Null if not
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


    /***
     * Get method of HashSet
     * @return this.HashSet
     */
    public HashSet<User> getUserList(){return this.userList;}

}
//Shivam Amin | shivamamin4@gmail.com