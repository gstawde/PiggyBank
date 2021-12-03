package Controller;

import Controller.Messages.Message;
import Controller.Messages.SignInMessage;
import Controller.Messages.SignUpMessage;
import Model.Admin;
import Model.BankAccount;
import Model.User;
import View.HomePageView;
import View.LogInView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/***
 * This is the Controller class for the log in page, this page will communicate with the page and events happening to
 * tell the view pages what to display and when to switch tabs etc..
 * It gives the View pages some functionality
 */
public class LogInController {
    //Instance Variables
    BlockingQueue<Message> queue;
    Admin admin;
    LogInView lView;

    //Constructor
    public LogInController(BlockingQueue<Message> queue, Admin admin, LogInView lView) {
        this.queue = queue;
        this.admin = admin;
        this.lView = lView;
    }

    //Main loop of the controller
    public void mainLoop() {
        //run this while the login view is displayable
        while(lView.isDisplayable()){
            //Create an object message that takes the next object from the queue and sees what it has to do
            Message message = null;
            try {
                //Use take so that it will wait until there is something in the queue to take, so if no buttons are pressed, the page will stay the same
                message = queue.take();
            } catch (InterruptedException exception) {
                // do nothing
            }


            if(message.getClass() == SignInMessage.class) {    //If the message is a signinMessage message, do the signin protocol

                //Get the text from textFields and enter them into variables for username and password
                String user = lView.username.getText();
                String pass = lView.password.getText();

                //See if there is a valid user with said username and password
                User u = lView.a.authenticateUser(user,pass);


                if(u != null){      //If a user with the username and password exists enter if
                    //Create homepage views and Controls that pass in the information for the user
                    HomePageView hView = new HomePageView(u.getTransactionIterator(), queue);
                    HomePageController control = new HomePageController(queue, u, admin, hView);

                    //Dispose of the current page
                    lView.dispose();

                    //Call the mainLoop of the HomepageController
                    control.mainLoop();

                }else{
                    //Display the log in failed message
                    lView.logInFail();
                }

            }else if(message.getClass() == SignUpMessage.class){ //If the message is a signUpMessage message, do the signup protocol
                //Get the text from textFields and enter them into variables for username and password
                String user = lView.username.getText();
                String pass = lView.password.getText();

                //Create a new user with the given information
                User u = new User(user,pass,new BankAccount("","",50.00));

                //Check if the admin HashMap can addUser (will be false if User already exists)
                if(admin.addUser(u)){
                    //Create the homepage views and controls for the given User that is created
                    HomePageView hView = new HomePageView(u.getTransactionIterator(), queue);
                    HomePageController control = new HomePageController(queue, u, admin, hView);

                    //Dispose the current page
                    lView.dispose();

                    //Call the mainLoop of the HomePageView controller
                    control.mainLoop();
                }else{
                    //User already exists so display fail message
                    lView.signUpFail();
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
//Shivam Amin | shivamamin4@gmail.com

