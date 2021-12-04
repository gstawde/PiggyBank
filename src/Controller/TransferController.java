package Controller;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import Controller.Messages.*;
import Model.*;
import View.HomePageView;
import View.TransferView;
import View.SettingsView;

/**
 * Controls Transfer view
 */
public class TransferController {

    //instance variables
    private BlockingQueue<Message> queue;
    private Admin admin;
    private User user;
    private TransferView transferView;


    public TransferController(BlockingQueue<Message> queue, Admin admin, User user, TransferView transferView){
        this.queue = queue;
        this.admin = admin;
        this.user = user;
        this.transferView = transferView;
    }


    /**
     * mainLoop constantly executes message checks to run Transferview
     */
    public void mainLoop() {
        while(transferView.isDisplayable()){
            Message message = null;
            try {
                message = queue.take();
            } catch (InterruptedException exception) {
                // do nothing
            }
            transferView.success.setVisible(false);
            transferView.invalidUserName.setVisible(false);
            transferView.invalidAmount.setVisible(false);
            transferView.insufficientFunds.setVisible(false);


            //perform transfer
            if(message.getClass() == TransferMessage.class){
                TransferMessage transfer = (TransferMessage) message;
                HashMap<String, User> users = admin.getUsernameToUser();
                String username = transfer.getRecipient();
                double amount;
                try {
                    amount = Double.parseDouble(transfer.getAmount());
                    if(users.containsKey(username) && !username.equals(user.getUsername())){
                        if(user.payUser(amount,users.get(username)))
                            transferView.success.setVisible(true);
                        else
                            transferView.insufficientFunds.setVisible(true);
                    }
                    else
                        transferView.invalidUserName.setVisible(true);
                } catch (NumberFormatException ex){
                    transferView.invalidAmount.setVisible(true);
                }
            }

            //go to Settings page
            else if(message.getClass() == SettingsPageMessage.class)
            {
                SettingsView view = new SettingsView(queue, user.getUsername(), user.getBankAccount().getBalance());
                SettingsController c = new SettingsController(queue, admin, user, view);
                transferView.dispose();
                c.mainLoop();
            }
            //go to Home page
            else if(message.getClass() == HomePageMessage.class)
            {
                HomePageView view = new HomePageView(user.getTransactionIterator(),queue);
                HomePageController c = new HomePageController(queue, user, admin, view);
                transferView.dispose();
                c.mainLoop();
            }
        }
    }


    public static void main(String[] args) {
        Admin admin = new Admin();
        User u = new User("u", "a", new BankAccount("sm","a",500.00));
        User b = new User("b","a", new BankAccount("b","a",60));
        u.payUser(50,b);
        b.payUser(20,u);
        admin.addUser(u);
        admin.addUser(b);
        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
        TransferView RTview = new TransferView(queue);
        TransferController RTcontroller = new TransferController(queue,admin,u,RTview);
        RTcontroller.mainLoop();
    }

}
// Chint Patel | patelchint2002@gmail.com