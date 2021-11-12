package View;
import javax.swing.*;
import java.awt.*;

import Model.*;

public class SettingsView extends JFrame
{
   //private JFrame frame;
   private JPanel panel;
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

   public SettingsView(String userName, double balance)
   {
       panel = new JPanel();
       this.setSize(600,600);
      // frame.setLayout(new FlowLayout());
      // frame.setVisible(true);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.add(panel);

       panel.setLayout(null);

       settingLabel = new JLabel("Settings");
       settingLabel.setBounds(200, 0, 80,25);
       panel.add(settingLabel);

       bankAccInfoLabel = new JLabel("Bank Account Information:");
       bankAccInfoLabel.setBounds(10, 50, 150, 25);
       panel.add(bankAccInfoLabel);

       userNameLabel = new JLabel("User Name: " + userName);
       userNameLabel.setBounds(200,80,200,25);
       panel.add(userNameLabel);

       bankBalanceLabel = new JLabel("Balance: $"+ balance);
       bankBalanceLabel.setBounds(200,110,200,25);
       panel.add(bankBalanceLabel);

       updateNameButton = new JButton("Update User Name");
       updateNameButton.setBounds(60,160,150,25);
       panel.add(updateNameButton);

       updateNameText = new JTextField(20);
       updateNameText.setBounds(240,160,150,25);
       panel.add(updateNameText);

       updateNameSuccess = new JLabel("User Name Updated!");
       updateNameSuccess.setBounds(420, 160, 150, 25);
       updateNameSuccess.setVisible(false);
       panel.add(updateNameSuccess);

       updateNameFail = new JLabel("Invalid UserName");
       updateNameFail.setBounds(420, 160, 150, 25);
       updateNameFail.setVisible(false);
       panel.add(updateNameFail);

       updatePasswordButton = new JButton("Update Password");
       updatePasswordButton.setBounds(60,220,150,25);
       panel.add(updatePasswordButton);

       passwordText = new JPasswordField(20);
       passwordText.setBounds(240,220, 150, 25);
       panel.add(passwordText);

       updatePasswordSuccess = new JLabel("Password Updated!");
       updatePasswordSuccess.setBounds(420, 220, 150, 25);
       updatePasswordSuccess.setVisible(false);
       panel.add(updatePasswordSuccess);

       updatePasswordFail = new JLabel("Invalid Password");
       updatePasswordFail.setBounds(420, 220, 150, 25);
       updatePasswordFail.setVisible(false);
       panel.add(updatePasswordFail);

       deleteAccountButton = new JButton("Delete Account");
       deleteAccountButton.setBounds(60,280, 150,25);
       panel.add(deleteAccountButton);

       deleteSuccess = new JLabel("Account Deleted");
       deleteSuccess.setBounds(420, 280, 150, 25);
       deleteSuccess.setVisible(false);
       panel.add(deleteSuccess);

       deleteFail = new JLabel("Delete Fail");
       deleteFail.setBounds(420, 280, 150, 25);
       deleteFail.setVisible(false);
       panel.add(deleteFail);

       logOutButton = new JButton("Log Out");
       logOutButton.setBounds(200, 450,150,25);
       panel.add(logOutButton);

       this.setVisible(true);
   }

   public static void main(String[] args)
   {
       User user = new User("Jun Wu", "abc", new BankAccount("Jun", "Wu", 500));
       new SettingsView(user.getUsername(), user.getBankAccount().getBalance());
   }
}
