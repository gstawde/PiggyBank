package Controller;

import Model.Admin;
import Model.BankAccount;
import Model.User;
import View.LogInView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class App {
    public Admin admin;

    public App(){
        admin = new Admin();
        populateAdmin();

        BlockingQueue queue = new LinkedBlockingQueue();
        LogInController start = new LogInController(queue,admin,new LogInView(admin,queue));
        start.mainLoop();
    }


    public void populateAdmin(){
        User s = new User("s","a",new BankAccount("s","a",50));
        User a = new User("a","a",new BankAccount("s","a",50));
        admin.addUser(s);
        admin.addUser(a);
        s.payUser(25,a);
    }

    public static void main(String[] args) {
        App start = new App();
    }
}
