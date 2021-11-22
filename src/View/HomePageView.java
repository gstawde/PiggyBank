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


import Model.BankAccount;
import Model.User;

public class HomePageView extends JFrame{

    JTable table;
    JLabel transactionsSummary;
    JScrollPane sp;
    JButton homePage;
    JButton requestOrTransfer;
    JButton settings;

    // BASIC PAGE STYLING
    final Color background = Color.decode("#272727");
    final Color accentPink = Color.decode("#E6BEAE");
    final Color genericText = Color.decode("#FFFFFF");
    final Color accentBlue = Color.decode("#A9BCD0");
    final Font paragraphText = new Font("Modern No. 20", Font.PLAIN, 16);
    final Font titleText = new Font("Modern No. 20", Font.PLAIN, 30);
    final Font headerText = new Font("Modern No. 20", Font.PLAIN, 20);

    public HomePageView(User user){

        // BASIC PAGE STYLING
        this.getContentPane().setBackground(background);
        this.setSize(500, 500);

        // PAGE HEADER
        // navbar:
        homePage = new JButton("Home Page");
        requestOrTransfer = new JButton("Request/Transfer");
        settings = new JButton("Settings");
        this.add(homePage);
        this.add(requestOrTransfer);
        this.add(settings);
        // navbar styling:
        homePage.setMargin(new Insets(10,175, 0,5));
        homePage.setForeground(genericText);
        homePage.setOpaque(false);
        homePage.setBorderPainted(false);
        homePage.setFont(headerText);
        requestOrTransfer.setMargin(new Insets(10,0, 0,5));
        requestOrTransfer.setForeground(accentBlue);
        requestOrTransfer.setOpaque(false);
        requestOrTransfer.setBorderPainted(false);
        requestOrTransfer.setFont(headerText);
        settings.setMargin(new Insets(10,0, 0,5));
        settings.setForeground(accentBlue);
        settings.setOpaque(false);
        settings.setBorderPainted(false);
        settings.setFont(headerText);
        // spacing:
        this.add(Box.createVerticalStrut(50));

        // HOME PAGE BASIC CONTENT
        this.transactionsSummary = new JLabel("Transactions Summary");
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
        Object[][] data = new Object[user.getTransactionHistory().size()][3];
        for(int i = 0; i < user.getTransactionHistory().size(); i++){
            data[i][0] = user.getTransactionHistory().get(i).getReceiver().getUsername();
            data[i][1] = user.getTransactionHistory().get(i).getSender().getUsername();
            data[i][2] = user.getTransactionHistory().get(i).getAmount();
        }
        this.table = new JTable(data, columnNames);
        // scroll pane:
        this.sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder());
        this.add(sp);
        // table styling:
        this.table.getTableHeader().setBackground(background);
        this.table.getTableHeader().setForeground(accentPink);
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
        User u = new User("s", "a", new BankAccount("sm","a",500.00));
        User b = new User("b","a", new BankAccount("b","a",60));
        u.payUser(50,b);
        b.payUser(20,u);
        HomePageView v = new HomePageView(u);
    }
}
