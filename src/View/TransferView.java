package View;

import Controller.Messages.*;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

/**
 * Represents TransferView page
 */
public class TransferView extends JFrame {
    //instance variables
    public JLabel title;
    public JLabel success;
    public JLabel invalidAmount;
    public JLabel invalidUserName;
    public JLabel insufficientFunds;
    public JTextField username;
    public JTextField amount;
    public JButton transfer;
    public JButton settings;
    public JButton homePage;

    // BASIC PAGE STYLING
    final Color background = Color.decode("#272727");
    final Color accentPink = Color.decode("#E6BEAE");
    final Color genericText = Color.decode("#FFFFFF");
    final Color accentBlue = Color.decode("#A9BCD0");
    final Font paragraphText = new Font("Modern No. 20", Font.PLAIN, 16);
    final Font titleText = new Font("Modern No. 20", Font.PLAIN, 30);
    final Font headerText = new Font("Modern No. 20", Font.PLAIN, 20);

    private BlockingQueue<Message> queue;


    public TransferView(BlockingQueue<Message> queue) {
        this.setLocation(0, 0);
        // BASIC PAGE STYLING
        this.getContentPane().setBackground(background);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultLookAndFeelDecorated(true);

        //homePage button settings
        homePage = new JButton("Home");
        homePage.setBounds(99,10,150,40);
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

        //settings button settings
        settings = new JButton("Settings");
        settings.setBounds(251,10,150,40);
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

        //Title label settings
        title = new JLabel("Transfer");
        title.setBounds(200, 65, 250, 25);
        title.setFont(titleText);
        title.setForeground(accentPink);


        //various label settings
        success = new JLabel("Process Success");
        success.setBounds(186, 95, 150, 25);
        success.setVisible(false);
        success.setFont(headerText);
        success.setForeground(genericText);

        username = new JTextField("Username");
        username.setBounds(175, 130, 150, 25);
        username.setBackground(accentBlue);

        amount = new JTextField("Amount");
        amount.setBounds(175, 180, 150, 25);
        amount.setBackground(accentBlue);

        transfer = new JButton("Transfer");
        transfer.setBounds(175, 225, 150, 25);
        transfer.setBackground(accentPink);
        transfer.setFont(headerText);
        //transfer button action
        transfer.addActionListener(e -> {
            try {
                Message msg = new TransferMessage(username.getText(), amount.getText());
                queue.put(msg);
            } catch (InterruptedException exception) {
                // do nothing
            }
        });

        invalidUserName = new JLabel("Invalid UserName");
        invalidUserName.setBounds(186, 95, 150, 25);
        invalidUserName.setVisible(false);
        invalidUserName.setFont(headerText);
        invalidUserName.setForeground(genericText);

        invalidAmount = new JLabel("Invalid Amount");
        invalidAmount.setBounds(186, 95, 150, 25);
        invalidAmount.setVisible(false);
        invalidAmount.setFont(headerText);
        invalidAmount.setForeground(genericText);

        insufficientFunds = new JLabel("Insufficient Funds");
        insufficientFunds.setBounds(186, 95, 150, 25);
        insufficientFunds.setVisible(false);
        insufficientFunds.setFont(headerText);
        insufficientFunds.setForeground(genericText);

        //add elements to Frame
        this.add(success);
        this.add(username);
        this.add(amount);
        this.add(transfer);
        this.add(invalidUserName);
        this.add(invalidAmount);
        this.add(insufficientFunds);
        this.add(settings);
        this.add(homePage);
        this.add(title);

        this.setLayout(null);
        this.setVisible(true);
    }

}
//Chint Patel | patelchint2002@gmail.com