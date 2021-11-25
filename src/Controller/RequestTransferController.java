package Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import Model.*;
import View.RequestTransferView;

public class RequestTransferController {
    public RequestTransferController(Admin admin, User user){
        RequestTransferView RTview = new RequestTransferView();
        RTview.request.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String, User> users = admin.getUsernameToUser();
                String username = RTview.username.getText();
                double amount;
                try{
                    amount = Double.parseDouble(RTview.amount.getText());
                    if(users.containsKey(username)) {
                        user.requestFromUser(amount, users.get(username));
                        RTview.success.setVisible(true);
                        RTview.invalidUserName.setVisible(false);
                        RTview.invalidAmount.setVisible(false);
                        RTview.insufficientFunds.setVisible(false);
                    }
                    else{
                        RTview.success.setVisible(false);
                        RTview.invalidUserName.setVisible(true);
                        RTview.invalidAmount.setVisible(false);
                        RTview.insufficientFunds.setVisible(false);
                    }
                } catch (NumberFormatException ex){
                    RTview.success.setVisible(false);
                    RTview.invalidUserName.setVisible(false);
                    RTview.invalidAmount.setVisible(true);
                    RTview.insufficientFunds.setVisible(false);

                }

            }
        });

        RTview.transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String, User> users = admin.getUsernameToUser();
                String username = RTview.username.getText();
                double amount;
                try {
                    amount = Double.parseDouble(RTview.amount.getText());
                    if(users.containsKey(username)){
                        if(user.payUser(amount,users.get(username))){
                            RTview.success.setVisible(true);
                            RTview.invalidUserName.setVisible(false);
                            RTview.invalidAmount.setVisible(false);
                            RTview.insufficientFunds.setVisible(false);
                        }
                        else{
                            RTview.success.setVisible(false);
                            RTview.invalidUserName.setVisible(false);
                            RTview.invalidAmount.setVisible(false);
                            RTview.insufficientFunds.setVisible(true);
                        }
                    }
                    else {
                        RTview.success.setVisible(false);
                        RTview.invalidUserName.setVisible(true);
                        RTview.invalidAmount.setVisible(false);
                        RTview.insufficientFunds.setVisible(false);
                    }
                } catch (NumberFormatException ex){
                    RTview.success.setVisible(false);
                    RTview.invalidUserName.setVisible(false);
                    RTview.invalidAmount.setVisible(true);
                    RTview.insufficientFunds.setVisible(false);
                }
            }
        });
    }

    public static void main(String[] args) {
        Admin admin = new Admin();
        User u = new User("s", "a", new BankAccount("sm","a",500.00));
        User b = new User("b","a", new BankAccount("b","a",60));
        u.payUser(50,b);
        b.payUser(20,u);
        admin.addUser(u);
        admin.addUser(b);
        new RequestTransferController(admin, u);
    }

}
