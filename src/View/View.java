package View;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import Model.*;

public class View extends JFrame{
    JLabel userNameLabel;
    JButton button;
    public View(User user){
        this.userNameLabel = new JLabel(user.getUsername());

        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Object[][] data = new Object[user.getTransactionHistory().size()][3];

        for(int i = 0; i < user.getTransactionHistory().size() && i < 5; i++){
            Transaction t = user.getTransactionHistory().get(i);
            data[i] = new Object[]{t.getReceiver().getUsername(), t.getSender().getUsername(), t.getAmount()};
        }

        String[] columnNames = { "Receiver", "Sender", "Amount" };
        JTable table = new JTable(data, columnNames);
        table.setBounds(30, 40, 200, 300);
        this.add(table);
//
//        JScrollPane sp = new JScrollPane(table);
//        this.add(sp);

    }

    public static void main(String[] a){
//        User u = new User("s", "a", new BankAccount("sm","a",50.00));
//
//        u.payUser(50, new User("b","a", new BankAccount("b","a",60)));

        User u = new User("s", "a", new BankAccount("sm","a",500.00));
        User b = new User("b","a", new BankAccount("b","a",60));
        u.payUser(50,b);
        b.payUser(20,u);

        View v = new View(u);
    }


}
