package View;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

import Controller.Messages.*;
import Model.*;

public class SettingsView extends JFrame
{
   private JLabel settingLabel;
   private JLabel bankAccInfoLabel;
   private JLabel userNameLabel;
   private JLabel bankBalanceLabel;
   public JLabel updateNameSuccess;
   public JLabel updateNameFail;
   public JLabel updatePasswordSuccess;
   public JLabel updatePasswordFail;
   public JLabel deleteSuccess;
   public JLabel deleteFail;
   public JButton updateNameButton;
   public JButton updatePasswordButton;
   public JButton deleteAccountButton;
   public JButton logOutButton;
   public JTextField updateNameText;
   public JPasswordField passwordText;

   private BlockingQueue<Message> queue;

   final Color background = Color.decode("#272727");
   final Color accentPink = Color.decode("#E6BEAE");
   final Color genericText = Color.decode("#FFFFFF");
   final Color accentBlue = Color.decode("#A9BCD0");
   final Font paragraphText = new Font("Modern No. 20", Font.PLAIN, 16);
   final Font titleText = new Font("Modern No. 20", Font.PLAIN, 30);
   final Font headerText = new Font("Modern No. 20", Font.PLAIN, 20);

   public SettingsView(BlockingQueue<Message> queue, User user, double balance, Admin admin)
   {
       this.queue = queue;

       this.setLocation(0,0);
       this.setSize(600,600);
       this.getContentPane().setBackground(background);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setDefaultLookAndFeelDecorated(true);

       settingLabel = new JLabel("Settings");
       settingLabel.setBounds(170, 0, 160,40);
       settingLabel.setFont(titleText);
       settingLabel.setForeground(accentPink);

       bankAccInfoLabel = new JLabel("Bank Account Information:");
       bankAccInfoLabel.setBounds(10, 60, 230, 40);
       bankAccInfoLabel.setFont(headerText);
       bankAccInfoLabel.setForeground(accentPink);

       userNameLabel = new JLabel("User Name: " + user.getUsername());
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

       updateNameButton.addActionListener(e -> {
           String str = updateNameText.getText();
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

       logOutButton.addActionListener(e -> {
           try {
               Message msg = new LogOutMessage();
               queue.put(msg);
           } catch (InterruptedException exception) {
               // do nothing
           }
       });

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

       System.out.println("1");
       MenuView mv = new MenuView(this,user,queue,admin);

       this.setLayout(null);
       this.setVisible(true);
       System.out.println("2");
   }

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

   /*
   public static void main(String[] args)
   {
       User user = new User("Jun Wu", "abc", new BankAccount("Jun", "Wu", 500));
       new SettingsView(user.getUsername(), user.getBankAccount().getBalance());
   }

    */
}
