package Controller;

import Model.Admin;
import Model.BankAccount;
import Model.User;
import View.LogInView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/***
 * This is the main class that starts the whole app
 */
public class App {
    //Instance Variables
    public Admin admin;
    BlockingQueue queue;

    //Run the App inside of the constructor
    public App(){
        admin = new Admin();
        queue = new LinkedBlockingQueue();
        //Add users to the admin Class
        populateAdmin();

        //Always call the LogInController to start the app and call its main loop
        LogInController start = new LogInController(queue,admin,new LogInView(admin,queue));
        start.mainLoop();
    }

    /***
     * Populate the admin with some users to demonstrate the app
     */
    public void populateAdmin(){
        User s = new User("s","a",new BankAccount("s","a",50));
        User a = new User("a","a",new BankAccount("s","a",50));
        admin.addUser(s);
        admin.addUser(a);
        //Create a transaction between them for demonstration purposes
        s.payUser(25,a);
    }

    public static void main(String[] args) {
        App start = new App();
    }
}
//Shivam Amin | shivamamin4@gmail.com
