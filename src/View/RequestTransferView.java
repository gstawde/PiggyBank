package View;

import Controller.Messages.*;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class RequestTransferView extends JFrame {
    public JLabel title;
    public JLabel success;
    public JLabel invalidAmount;
    public JLabel invalidUserName;
    public JLabel insufficientFunds;
    public JTextField username;
    public JTextField amount;
    public JButton request;
    public JButton transfer;
    JButton settings;
    JButton homePage;

    // BASIC PAGE STYLING
    final Color background = Color.decode("#272727");
    final Color accentPink = Color.decode("#E6BEAE");
    final Color genericText = Color.decode("#FFFFFF");
    final Color accentBlue = Color.decode("#A9BCD0");
    final Font paragraphText = new Font("Modern No. 20", Font.PLAIN, 16);
    final Font titleText = new Font("Modern No. 20", Font.PLAIN, 30);
    final Font headerText = new Font("Modern No. 20", Font.PLAIN, 20);

    private BlockingQueue<Message> queue;

    public RequestTransferView(BlockingQueue<Message> queue) {
        this.setLocation(0, 0);
        // BASIC PAGE STYLING
        this.getContentPane().setBackground(background);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultLookAndFeelDecorated(true);

        title = new JLabel("Request/Transfer");
        title.setBounds(200, 10, 250, 25);
        title.setFont(titleText);
        title.setForeground(accentPink);

        homePage = new JButton("Home");
        homePage.setBounds(0,0,150,40);
        homePage.setBackground(accentPink);
        homePage.setFont(titleText);

        homePage.addActionListener(e -> {
            try{
                Message msg = new HomePageMessage();
                queue.put(msg);
            } catch (InterruptedException exception){
                //do nothing
            }
        });

        settings = new JButton("Setting");
        settings.setBounds(0,80,150,40);
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



        success = new JLabel("Process Success");
        success.setBounds(175, 50, 150, 25);
        success.setVisible(false);
        success.setFont(headerText);
        success.setForeground(accentPink);

        username = new JTextField("Username");
        username.setBounds(175, 100, 150, 25);

        amount = new JTextField("Amount");
        amount.setBounds(175, 150, 150, 25);

        request = new JButton("Request");
        request.setBounds(175, 250, 150, 25);
        request.setBackground(accentPink);
        request.setFont(headerText);

        transfer = new JButton("Transfer");
        transfer.setBounds(175, 300, 150, 25);
        transfer.setBackground(accentPink);
        transfer.setFont(headerText);

        invalidUserName = new JLabel("Invalid UserName");
        invalidUserName.setBounds(175, 50, 150, 25);
        invalidUserName.setVisible(false);
        invalidUserName.setFont(headerText);
        invalidUserName.setForeground(accentPink);

        invalidAmount = new JLabel("Invalid Amount");
        invalidAmount.setBounds(175, 50, 150, 25);
        invalidAmount.setVisible(false);
        invalidAmount.setFont(headerText);
        invalidAmount.setForeground(accentPink);

        insufficientFunds = new JLabel("Insufficient Funds");
        insufficientFunds.setBounds(175, 50, 250, 25);
        insufficientFunds.setVisible(false);
        insufficientFunds.setFont(headerText);
        insufficientFunds.setForeground(accentPink);

        this.add(title);
        this.add(success);
        this.add(username);
        this.add(amount);
        this.add(request);
        this.add(transfer);
        this.add(invalidUserName);
        this.add(invalidAmount);
        this.add(insufficientFunds);
        this.add(settings);
        this.add(homePage);
      //  MenuView mv = new MenuView(this,u,queue,a);

        this.setLayout(null);
        this.setVisible(true);

        //request button action
        request.addActionListener(e -> {
            try {
                Message msg = new RequestMessage(username.getText(), amount.getText());
                queue.put(msg);
            } catch (InterruptedException exception) {
                // do nothing
            }
        });
        //transfer button action
        transfer.addActionListener(e -> {
            try {
                Message msg = new TransferMessage(username.getText(), amount.getText());
                queue.put(msg);
            } catch (InterruptedException exception) {
                // do nothing
            }
        });
    }
    /*
    public static void main(String[] args) {
        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
        new RequestTransferView(queue,);
    }*/
}
//Chint Patel | patelchint2002@gmail.com