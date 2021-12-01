package Controller;

import Model.Admin;
import View.LogInView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class App {
    public Admin admin;

    public App(){
        admin = new Admin();
        //admin.addUser(new User("s","a",new BankAccount("s","a",50)));
        BlockingQueue queue = new LinkedBlockingQueue();
        LogInController start = new LogInController(queue,admin,new LogInView(admin,queue));
    }

    public static void main(String[] args) {
        App start = new App();
    }
}
