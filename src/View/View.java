package View;

import javax.swing.*;
import java.awt.*;

import Model.BankAccount;
import Model.User;

public class View extends JFrame{
    JTable table;

    public View(String s, User user){

        String[] columnNames = {"Receiver Name",
                "Fulfiller Name",
                 "Amount"};
        Object[][] data = new Object[user.getTransactionHistory().size()][3];
        for(int i = 0; i < user.getTransactionHistory().size(); i++){
            data[i][0] = user.getTransactionHistory().get(i).getReceiver().getUsername();
            data[i][1] = user.getTransactionHistory().get(i).getSender().getUsername();
            data[i][2] = user.getTransactionHistory().get(i).getAmount();
        }
        this.table = new JTable(data, columnNames);

        JScrollPane sp = new JScrollPane(table);
        this.add(sp);

        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] a){
        User u = new User("s", "a", new BankAccount("sm","a",500.00));
        User b = new User("b","a", new BankAccount("b","a",60));
        u.payUser(50,b);
        b.payUser(20,u);
        View v = new View("s", u);
    }
}
