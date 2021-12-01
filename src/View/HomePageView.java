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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


import Controller.Message;
import Controller.NavBarUseMessage;
import Model.BankAccount;
import Model.User;

public class HomePageView extends JFrame implements ActionListener {

    User user;

    JTable table;
    JLabel transactionsSummary;
    JScrollPane sp;

    JMenuBar mb;
    JMenu menu;
    JMenuItem homePage, requestOrTransfer, settings;

    // BASIC PAGE STYLING
    final Color background = Color.decode("#272727");
    final Color accentPink = Color.decode("#E6BEAE");
    final Color genericText = Color.decode("#FFFFFF");
    final Color accentBlue = Color.decode("#A9BCD0");
    final Font paragraphText = new Font("Modern No. 20", Font.PLAIN, 16);
    final Font titleText = new Font("Modern No. 20", Font.PLAIN, 30);
    final Font headerText = new Font("Modern No. 20", Font.PLAIN, 20);

    private BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    public HomePageView(User user){

        this.user = user;

        // BASIC PAGE STYLING
        this.getContentPane().setBackground(background);
        this.setSize(500, 500);

        // PAGE HEADER
        // navbar:
        mb = new JMenuBar();
        menu = new JMenu("Menu");
        homePage = new JMenuItem("Home");
        requestOrTransfer = new JMenuItem("Request or Transfer");
        settings = new JMenuItem("Settings");
        menu.add(homePage);
        menu.add(requestOrTransfer);
        menu.add(settings);
        mb.add(menu);
        this.setJMenuBar(mb);
        this.setSize(500,500);
        homePage.addActionListener(this);
        requestOrTransfer.addActionListener(this);
        settings.addActionListener(this);
        // navbar styling:
        mb.setForeground(background);
        mb.setBackground(background);
        mb.setOpaque(true);
        menu.setFont(headerText);
        homePage.setBackground(accentBlue);
        homePage.setFont(paragraphText);
        homePage.setForeground(background);
        requestOrTransfer.setBackground(accentBlue);
        requestOrTransfer.setFont(paragraphText);
        requestOrTransfer.setForeground(genericText);
        settings.setBackground(accentBlue);
        settings.setFont(paragraphText);
        settings.setForeground(genericText);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        Message m;
        JFrame newFrame;
        if (e.getSource() == homePage) {
            m = new NavBarUseMessage("\'Home\' menu item clicked");
            newFrame = new HomePageView(user);
            newFrame.setVisible(true);
            queue.add(m);
        } else if (e.getSource() == requestOrTransfer) {
            m = new NavBarUseMessage("\'equest or Transfer\' menu item clicked");
            newFrame = new RequestTransferView(queue);
            newFrame.setVisible(true);
            queue.add(m);
        } else if (e.getSource() == settings) {
            m = new NavBarUseMessage("\'Settings\' menu item clicked");
            newFrame = new SettingsView(queue, user.getUsername(), user.getBankAccount().getBalance());
            newFrame.setVisible(true);
            queue.add(m);
        }
    }

    public static void main(String[] a){
        User u = new User("s", "a", new BankAccount("sm","a",500.00));
        User b = new User("b","a", new BankAccount("b","a",60));
        u.payUser(50,b);
        b.payUser(20,u);
        HomePageView v = new HomePageView(u);

    }


}

// © Nov 08, 2021 | Gargi Tawde | gargi.tawde01@gmail.com
