/* STYLE INFO:
    final Color background = Color.decode("#272727");
    final Color accentPink = Color.decode("#E6BEAE");
    final Color genericText = Color.decode("#FFFFFF");
    final Color accentBlue = Color.decode("#A9BCD0");
    final Font paragraphText = new Font("Modern No. 20", Font.PLAIN, 16);
    final Font titleText = new Font("Modern No. 20", Font.PLAIN, 30);
    final Font headerText = new Font("Modern No. 20", Font.PLAIN, 20);
*/


package View;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


import Controller.Messages.Message;
import Controller.Messages.TransferPageMessage;
import Controller.Messages.SettingsPageMessage;
import Model.*;

public class HomePageView extends JFrame{

    User user;
    TransactionsIterator transactionIterator;
    JTable table;
    JLabel transactionsSummary;
    JScrollPane sp;
    JButton settings;
    JButton TransferPage;

    // BASIC PAGE STYLING
    final Color background = Color.decode("#272727");
    final Color accentPink = Color.decode("#E6BEAE");
    final Color genericText = Color.decode("#FFFFFF");
    final Color accentBlue = Color.decode("#A9BCD0");
    final Font paragraphText = new Font("Modern No. 20", Font.PLAIN, 16);
    final Font titleText = new Font("Modern No. 20", Font.PLAIN, 30);
    final Font headerText = new Font("Modern No. 20", Font.PLAIN, 20);


    public HomePageView(TransactionsIterator iterator, BlockingQueue queue){

        transactionIterator = iterator;

        // BASIC PAGE STYLING
        this.getContentPane().setBackground(background);
        this.setSize(500, 600);

        // PAGE HEADER
        // navbar:

        //MenuView mv = new MenuView(this,user,queue,admin);

        TransferPage = new JButton("Transfer");
        //requestOrTransfer.setBounds(0,0,250,40);
        TransferPage.setBackground(accentPink);
        TransferPage.setFont(titleText);

        TransferPage.addActionListener(e -> {
            try {
                Message msg = new TransferPageMessage();
                queue.put(msg);
            } catch (InterruptedException exception) {
                // do nothing
            }
        });

        settings = new JButton("Settings");
       // settings.setBounds(0,0,150,40);
        settings.setBackground(accentPink);
        settings.setFont(titleText);

        settings.addActionListener(e -> {
            try {
                Message msg = new SettingsPageMessage();
                queue.put(msg);
            } catch (InterruptedException exception) {
                // do nothing
            }
        });

        this.add(TransferPage);
        this.add(settings);

        // spacing:
        this.add(Box.createVerticalStrut(50));

        // Logo
        ImageIcon original = new ImageIcon("piggyBankLogo.png"); // load the image to a imageIcon
        Image forTransforming = original.getImage(); // transform it
        Image newImage = forTransforming.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        original = new ImageIcon(newImage);
        JLabel label = new JLabel(original);
        this.add(label);

        // spacing:
        this.add(Box.createVerticalStrut(50));

        // HOME PAGE BASIC CONTENT
        this.transactionsSummary = new JLabel("Transactions Summary",SwingConstants.CENTER);
        transactionsSummary.setFont(titleText);
        transactionsSummary.setForeground(accentPink);
        this.add(transactionsSummary);

        // spacing:
        this.add(Box.createVerticalStrut(50));

        // TRANSACTIONS TABLE
        // table content:
        String[] columnNames = {"Receiver Name",
                "Fulfiller Name",
                 "Amount"};
        Object[][] data = new Object[5][3];
        int i = 0;
        while(this.transactionIterator.hasNext() && i < 5){
            Transaction  t = this.transactionIterator.next();
            data[i][0] = t.getReceiver().getUsername();
            data[i][1] = t.getSender().getUsername();
            data[i][2] = t.getAmount();
            i++;
        }
        this.table = new JTable(data, columnNames);
        // scroll pane:
        this.sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder());
        this.add(sp);
        // table styling:
        this.table.getTableHeader().setBackground(background);
        this.table.getTableHeader().setForeground(accentBlue);
        this.table.getTableHeader().setFont(headerText);
        this.table.setShowGrid(false);
        this.table.setOpaque(true);
        this.table.setFillsViewportHeight(true);
        this.table.setForeground(genericText);
        this.table.setFont(paragraphText);
        this.table.setBackground(background);

        // MORE BASIC PAGE STYLING
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public static void main(String[] a){
        Admin admin = new Admin();

        User u = new User("s", "a", new BankAccount("sm","a",500.00));
        User b = new User("b","a", new BankAccount("b","a",60));
        admin.addUser(u);
        admin.addUser(b);
        u.payUser(50,b);
        b.payUser(20,u);
        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
        HomePageView v = new HomePageView(u.getTransactionIterator(), queue);

    }


}

// © Nov 08, 2021 | Gargi Tawde | gargi.tawde01@gmail.com
