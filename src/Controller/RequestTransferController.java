package Controller;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import Controller.Messages.*;
import Model.*;
import View.HomePageView;
import View.RequestTransferView;
import View.SettingsView;

public class RequestTransferController {

     BlockingQueue<Message> queue;
     Admin admin;
     User user;
     RequestTransferView RTview;


    public RequestTransferController(BlockingQueue<Message> queue, Admin admin, User user, RequestTransferView RTview){
        this.queue = queue;
        this.admin = admin;
        this.user = user;
        this.RTview = RTview;

//        RequestTransferView RTview = new RequestTransferView(queue);
//        RTview.request.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                RTview.success.setVisible(false);
//                RTview.invalidUserName.setVisible(false);
//                RTview.invalidAmount.setVisible(false);
//                RTview.insufficientFunds.setVisible(false);
//
//                HashMap<String, User> users = admin.getUsernameToUser();
//                String username = RTview.username.getText();
//                double amount;
//                try{
//                    amount = Double.parseDouble(RTview.amount.getText());
//                    if(users.containsKey(username)) {
//                        user.requestFromUser(amount, users.get(username));
//                        RTview.success.setVisible(true);
//                    }
//                    else
//                        RTview.invalidUserName.setVisible(true);
//                } catch (NumberFormatException ex) {
//                    RTview.invalidAmount.setVisible(true);
//                }
//
//            }
//        });
//
//        RTview.transfer.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                RTview.success.setVisible(false);
//                RTview.invalidUserName.setVisible(false);
//                RTview.invalidAmount.setVisible(false);
//                RTview.insufficientFunds.setVisible(false);
//
//                HashMap<String, User> users = admin.getUsernameToUser();
//                String username = RTview.username.getText();
//                double amount;
//                try {
//                    amount = Double.parseDouble(RTview.amount.getText());
//                    if(users.containsKey(username)){
//                        if(user.payUser(amount,users.get(username)))
//                            RTview.success.setVisible(true);
//                        else
//                            RTview.insufficientFunds.setVisible(true);
//                    }
//                    else
//                        RTview.invalidUserName.setVisible(true);
//                } catch (NumberFormatException ex){
//                    RTview.invalidAmount.setVisible(true);
//                }
//            }
//        });
    }

    public void mainLoop() {
        while(RTview.isDisplayable()){
            Message message = null;
            try {
                message = queue.take();
            } catch (InterruptedException exception) {
                // do nothing
            }
            RTview.success.setVisible(false);
            RTview.invalidUserName.setVisible(false);
            RTview.invalidAmount.setVisible(false);
            RTview.insufficientFunds.setVisible(false);

            //perfom request
            if(message.getClass() == RequestMessage.class) {
                RequestMessage request = (RequestMessage) message;
                HashMap<String, User> users = admin.getUsernameToUser();
                String username = request.getFulfiller();
                double amount;
                try{
                    amount = Double.parseDouble(request.getAmount());
                    if(users.containsKey(username) && !username.equals(user.getUsername())) {
                        user.requestFromUser(amount, users.get(username));
                        RTview.success.setVisible(true);
                    }
                    else
                        RTview.invalidUserName.setVisible(true);
                } catch (NumberFormatException ex) {
                    RTview.invalidAmount.setVisible(true);
                }
            }
            //perform transfer
            else if(message.getClass() == TransferMessage.class){
                TransferMessage transfer = (TransferMessage) message;
                HashMap<String, User> users = admin.getUsernameToUser();
                String username = transfer.getRecipient();
                double amount;
                try {
                    amount = Double.parseDouble(transfer.getAmount());
                    if(users.containsKey(username) && !username.equals(user.getUsername())){
                        if(user.payUser(amount,users.get(username)))
                            RTview.success.setVisible(true);
                        else
                            RTview.insufficientFunds.setVisible(true);
                    }
                    else
                        RTview.invalidUserName.setVisible(true);
                } catch (NumberFormatException ex){
                    RTview.invalidAmount.setVisible(true);
                }
            }

            else if(message.getClass() == SettingsPageMessage.class)
            {
                SettingsView view = new SettingsView(queue, user.getUsername(), user.getBankAccount().getBalance());
                SettingsController c = new SettingsController(queue, admin, user, view);
                RTview.dispose();
                c.mainLoop();
            }
            else if(message.getClass() == HomePageMessage.class)
            {
                HomePageView view = new HomePageView(user.getTransactionIterator(),queue);
                HomePageController c = new HomePageController(queue, user, admin, view);
                RTview.dispose();
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
        RequestTransferView RTview = new RequestTransferView(queue);
        RequestTransferController RTcontroller = new RequestTransferController(queue,admin,u,RTview);
        RTcontroller.mainLoop();
    }

}
// Chint Patel | patelchint2002@gmail.com