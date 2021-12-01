package Controller;

import Model.Admin;
import Model.BankAccount;
import Model.User;
import View.LogInView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogInController {
    BlockingQueue<Message> queue;
    Admin admin;
    LogInView lView;

    public LogInController(BlockingQueue<Message> queue, Admin admin, LogInView lView) {
        this.queue = queue;
        this.admin = admin;
        this.lView = lView;
    }

    public void mainLoop() {
        while(lView.isDisplayable()){
            Message message = null;
            try {
                message = queue.take();
            } catch (InterruptedException exception) {
                // do nothing
            }

            if(message.getClass() == SignInMessage.class) {

                String user = lView.username.getText();
                String pass = lView.password.getText();
                User u = lView.a.authenticateUser(user,pass);
                if(u != null){
                    lView.logInAttempt(true,u);
                }else{
                    lView.logInAttempt(false,u);
                }

            }else if(message.getClass() == SignUpMessage.class){
                String user = lView.username.getText();
                String pass = lView.password.getText();

                User u = new User(user,pass,new BankAccount("","",50.00));
                if(admin.addUser(u)){
                    lView.signUpAttempt(true,u);
                }else{
                    lView.signUpAttempt(false,u);
                }
            }
        }
    }


    public static void main(String[] args)
    {
        Admin admin = new Admin();
        User u = new User("s", "a", new BankAccount("sm","a",500.00));
        User b = new User("b","a", new BankAccount("b","a",60));
        u.payUser(50,b);
        b.payUser(20,u);
        admin.addUser(u);
        admin.addUser(b);
        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
        LogInView view = new LogInView(admin,queue);
        LogInController c = new LogInController(queue, admin, view);
        c.mainLoop();
    }

}
