package Controller;

import Model.Admin;
import Model.BankAccount;
import Model.User;
import View.HomePageView;
import View.LogInView;

import javax.swing.*;
import java.awt.event.*;


public class LogInToHomePage {
    public LogInToHomePage(Admin a){
        LogInView lView = new LogInView();
        lView.signin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String username = lView.username.getText();
               String password = lView.password.getText();

               User u = a.authenticateUser(username,password);
               if(u != null){
                   HomePageView hpv = new HomePageView(u);
                   lView.dispose();
               }else{
                   lView.fail.setVisible(true);
                   lView.fail2.setVisible(false);
               }
            }
        });

        lView.signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = lView.username.getText();
                String password = lView.password.getText();

                User u = new User(username,password,new BankAccount("","",50.00));
                if(a.addUser(u)){
                    HomePageView hpv = new HomePageView(u);
                    lView.dispose();
                }else{
                    lView.fail2.setVisible(true);
                    lView.fail.setVisible(false);
                }

            }
        });
    }
    public static void main(String[] a){
        Admin admin = new Admin();
        User u = new User("s", "a", new BankAccount("sm","a",500.00));
        User b = new User("b","a", new BankAccount("b","a",60));
        u.payUser(50,b);
        b.payUser(20,u);
        admin.addUser(u);
        admin.addUser(b);
        new LogInToHomePage(admin);
    }
}
