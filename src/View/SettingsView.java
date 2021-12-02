package View;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

import Controller.Messages.*;
import Controller.RequestOrTransferMessage;
import Model.*;

public class SettingsView extends JFrame
{
   private JLabel settingLabel;
   private JLabel bankAccInfoLabel;
   private JLabel userNameLabel;
   private JLabel bankBalanceLabel;
   private JLabel updateNameSuccess;
   private JLabel updateNameFail;
   private JLabel updatePasswordSuccess;
   private JLabel updatePasswordFail;
   private JLabel deleteSuccess;
   private JLabel deleteFail;
   private JButton updateNameButton;
   private JButton updatePasswordButton;
   private JButton deleteAccountButton;
   private JButton logOutButton;
   private JTextField updateNameText;
   private JPasswordField passwordText;
   private JButton requestOrTransfer;

   private BlockingQueue<Message> queue;

   //styling variables
   final Color background = Color.decode("#272727");
   final Color accentPink = Color.decode("#E6BEAE");
   final Font paragraphText = new Font("Modern No. 20", Font.PLAIN, 16);
   final Font titleText = new Font("Modern No. 20", Font.PLAIN, 30);
   final Font headerText = new Font("Modern No. 20", Font.PLAIN, 20);

   public SettingsView(BlockingQueue<Message> queue, String username, double balance, Admin admin)
   {
       this.queue = queue;

       //setting the base of the Jframe
       this.setLocation(0,0);
       this.setSize(600,600);
       this.getContentPane().setBackground(background);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setDefaultLookAndFeelDecorated(true);


       requestOrTransfer = new JButton("Request/Transfer");
       requestOrTransfer.setBounds(0,0,250,40);
       requestOrTransfer.setBackground(accentPink);
       requestOrTransfer.setFont(titleText);

       //ActionListener for requestOrTransfer button
       requestOrTransfer.addActionListener(e -> {
           try {
               Message msg = new RequestOrTransferMessage();
               queue.put(msg);
           } catch (InterruptedException exception) {
               // do nothing
           }
       });

       settingLabel = new JLabel("Settings");
       settingLabel.setBounds(260, 0, 160,40);
       settingLabel.setFont(titleText);
       settingLabel.setForeground(accentPink);

       bankAccInfoLabel = new JLabel("Bank Account Information:");
       bankAccInfoLabel.setBounds(10, 60, 230, 40);
       bankAccInfoLabel.setFont(headerText);
       bankAccInfoLabel.setForeground(accentPink);

       userNameLabel = new JLabel("User Name: " + username);
       userNameLabel.setBounds(170,90,200,35);
       userNameLabel.setFont(headerText);
       userNameLabel.setForeground(accentPink);

       bankBalanceLabel = new JLabel("Balance: $"+ balance);
       bankBalanceLabel.setBounds(170,130,200,35);
       bankBalanceLabel.setFont(headerText);
       bankBalanceLabel.setForeground(accentPink);

       updateNameButton = new JButton("Update User Name");
       updateNameButton.setBounds(0,190,170,25);
       updateNameButton.setFont(paragraphText);
       updateNameButton.setBackground(accentPink);

       updateNameText = new JTextField(20);
       updateNameText.setBounds(180,190,150,25);

       //ActionListener for updateNameButton button
       updateNameButton.addActionListener(e -> {
           String str = updateNameText.getText();
           this.userNameLabel.setText("User Name: " + str);
           try {
               Message msg = new UpdateUsernameMessage(str);
               queue.put(msg);
           } catch (InterruptedException exception) {
               // do nothing
           }
       });

       updateNameSuccess = new JLabel("User Name Updated!");
       updateNameSuccess.setBounds(340, 190, 170, 25);
       updateNameSuccess.setFont(paragraphText);
       updateNameSuccess.setForeground(accentPink);
       updateNameSuccess.setVisible(false);

       updateNameFail = new JLabel("Invalid UserName");
       updateNameFail.setBounds(340, 190, 170, 25);
       updateNameFail.setFont(paragraphText);
       updateNameFail.setForeground(accentPink);
       updateNameFail.setVisible(false);

       updatePasswordButton = new JButton("Update Password");
       updatePasswordButton.setBounds(0,250,170,25);
       updatePasswordButton.setFont(paragraphText);
       updatePasswordButton.setBackground(accentPink);

       passwordText = new JPasswordField(20);
       passwordText.setBounds(180,250, 150, 25);

       //ActionListener for the updatePasswordButton button
       updatePasswordButton.addActionListener(e -> {
           String str = passwordText.getText();
           try {
               Message msg = new UpdatePasswordMessage(str);
               queue.put(msg);
           } catch (InterruptedException exception) {
               // do nothing
           }
       });

       updatePasswordSuccess = new JLabel("Password Updated!");
       updatePasswordSuccess.setBounds(340, 250, 170, 25);
       updatePasswordSuccess.setFont(paragraphText);
       updatePasswordSuccess.setForeground(accentPink);
       updatePasswordSuccess.setVisible(false);

       updatePasswordFail = new JLabel("Invalid Password");
       updatePasswordFail.setBounds(340, 250, 170, 25);
       updatePasswordFail.setFont(paragraphText);
       updatePasswordFail.setForeground(accentPink);
       updatePasswordFail.setVisible(false);

       deleteAccountButton = new JButton("Delete Account");
       deleteAccountButton.setBounds(0,310, 170,25);
       deleteAccountButton.setFont(paragraphText);
       deleteAccountButton.setBackground(accentPink);

       //ActionListener for the deleteAccountButton button
       deleteAccountButton.addActionListener(e -> {
           try {
               Message msg = new DeleteAccountMessage();
               queue.put(msg);
           } catch (InterruptedException exception) {
               // do nothing
           }
       });

       deleteSuccess = new JLabel("Account Deleted");
       deleteSuccess.setBounds(350, 310, 170, 25);
       deleteSuccess.setFont(paragraphText);
       deleteSuccess.setForeground(accentPink);
       deleteSuccess.setVisible(false);

       deleteFail = new JLabel("Delete Fail");
       deleteFail.setBounds(350, 310, 170, 25);
       deleteFail.setFont(paragraphText);
       deleteFail.setForeground(accentPink);
       deleteFail.setVisible(false);

       logOutButton = new JButton("Log Out");
       logOutButton.setBounds(160, 370,150,25);
       logOutButton.setFont(paragraphText);
       logOutButton.setBackground(accentPink);

       //ActionListener for logOutButton
       logOutButton.addActionListener(e -> {
           try {
               Message msg = new LogOutMessage();
               queue.put(msg);
           } catch (InterruptedException exception) {
               // do nothing
           }
       });

       //add all the components to the frame
       this.add(settingLabel);
       this.add(bankAccInfoLabel);
       this.add(bankBalanceLabel);
       this.add(userNameLabel);
       this.add(updateNameButton);
       this.add(updateNameText);
       this.add(updateNameSuccess);
       this.add(updateNameFail);
       this.add(updatePasswordButton);
       this.add(passwordText);
       this.add(updatePasswordSuccess);
       this.add(updatePasswordFail);
       this.add(deleteAccountButton);
       this.add(deleteSuccess);
       this.add(deleteFail);
       this.add(logOutButton);
       this.add(requestOrTransfer);

       this.setLayout(null);
       this.setVisible(true);
   }

   //update settingsView to prompt user if username updated success or not
   public void updateNameSuccessFail(boolean val)
   {
       if(val == true)
       {
           this.updateNameSuccess.setVisible(true);
           this.updateNameFail.setVisible(false);
       }
       else
       {
           this.updateNameFail.setVisible(true);
           this.updateNameSuccess.setVisible(false);
       }
   }

    //update settingsView to prompt user if password updated success or not
    public void updatePasswordSuccessFail(boolean val)
    {
        if(val == true)
        {
            this.updatePasswordSuccess.setVisible(true);
            this.updatePasswordFail.setVisible(false);
        }
        else
        {
            this.updatePasswordFail.setVisible(true);
            this.updatePasswordSuccess.setVisible(false);
        }
    }

    //update settingsView to prompt user if the account successfully deleted or not
    public void deleteSuccessFail(boolean val)
    {
        if(val == true)
        {
            this.deleteSuccess.setVisible(true);
            this.deleteFail.setVisible(false);
        }
        else
        {
            this.deleteFail.setVisible(true);
            this.deleteSuccess.setVisible(false);
        }
    }
}
