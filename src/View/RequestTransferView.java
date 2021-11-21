package View;

import javax.swing.*;

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

    public RequestTransferView(){
        this.setLocation(0,0);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultLookAndFeelDecorated(true);

        title = new JLabel("Request/Transfer");
        title.setBounds(200, 0, 150, 25);

        success = new JLabel("Process Success");
        success.setBounds(175, 50, 150, 25);
        success.setVisible(false);

        username = new JTextField("Username");
        username.setBounds(175, 100, 150, 25);

        amount = new JTextField("Amount");
        amount.setBounds(175, 150, 150, 25);

        request = new JButton("Request");
        request.setBounds(175,250,150,25);

        transfer = new JButton("Transfer");
        transfer.setBounds(175,300,150,25);

        invalidUserName = new JLabel("Invalid UserName");
        invalidUserName.setBounds(175, 50, 150, 25);
        invalidUserName.setVisible(false);

        invalidAmount = new JLabel("Invalid Amount");
        invalidAmount.setBounds(175, 50, 150, 25);
        invalidAmount.setVisible(false);

        insufficientFunds = new JLabel("Insufficient Funds");
        insufficientFunds.setBounds(175, 50, 150, 25);
        insufficientFunds.setVisible(false);

        this.add(title);
        this.add(success);
        this.add(username);
        this.add(amount);
        this.add(request);
        this.add(transfer);
        this.add(invalidUserName);
        this.add(invalidAmount);
        this.add(insufficientFunds);



        this.setLayout(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new RequestTransferView();
    }
}
