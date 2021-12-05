package View;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

import Controller.Messages.*;
import Controller.Messages.TransferPageMessage;

/**
 * Represent the view of setting page
 */
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
   private JButton TransferPage;
   private JButton homePage;

   private BlockingQueue<Message> queue;

   //Page styling
   final Color background = Color.decode("#272727");
   final Color accentPink = Color.decode("#E6BEAE");
   final Font paragraphText = new Font("Modern No. 20", Font.PLAIN, 16);
   final Font titleText = new Font("Modern No. 20", Font.PLAIN, 30);
   final Font headerText = new Font("Modern No. 20", Font.PLAIN, 20);
   final Color accentBlue = Color.decode("#A9BCD0");
   final Color genericText = Color.decode("#FFFFFF");


    public SettingsView(BlockingQueue<Message> queue, String username, double balance)
   {
       this.queue = queue;

       //Basic page settings
       this.setLocation(0,0);
       this.setSize(500,500);
       this.getContentPane().setBackground(background);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setDefaultLookAndFeelDecorated(true);

       //Setting for "Home" button
       homePage = new JButton("Home");
       homePage.setBounds(85,10,150,40);
       homePage.setBackground(accentPink);
       homePage.setFont(titleText);

       //AddctionListener for Home button. Create a HomePageMessage and add to the queue
       homePage.addActionListener(e -> {
           try{
               Message msg = new HomePageMessage();
               queue.put(msg);
           } catch (InterruptedException exception){
               //do nothing
           }
       });

       //Setting for "Transfer" button
       TransferPage = new JButton("Transfer");
       TransferPage.setBounds(240,10,150,40);
       TransferPage.setBackground(accentPink);
       TransferPage.setFont(titleText);

       //AddctionListener for Transfer button. Create a TransferMessage and add to the queue
       TransferPage.addActionListener(e -> {
           try {
               Message msg = new TransferPageMessage();
               queue.put(msg);
           } catch (InterruptedException exception) {
               // do nothing
           }
       });

       //Setting for "Settings" Label
       settingLabel = new JLabel("Settings");
       settingLabel.setBounds(200, 65, 160,40);
       settingLabel.setFont(titleText);
       settingLabel.setForeground(accentPink);

       //Setting for "Bank Account Information:" label
       bankAccInfoLabel = new JLabel("Bank Account Information:");
       bankAccInfoLabel.setBounds(10, 120, 230, 40);
       bankAccInfoLabel.setFont(headerText);
       bankAccInfoLabel.setForeground(genericText);

       //Setting for "User Name" label and print the current username
       userNameLabel = new JLabel("User Name: " + username);
       userNameLabel.setBounds(170,160,200,35);
       userNameLabel.setFont(headerText);
       userNameLabel.setForeground(accentBlue);

       //Setting for "Balance" label and print the current balance
       bankBalanceLabel = new JLabel("Balance: $"+ balance);
       bankBalanceLabel.setBounds(170,190,200,35);
       bankBalanceLabel.setFont(headerText);
       bankBalanceLabel.setForeground(accentBlue);

       //Setting for "Update User Name" button
       updateNameButton = new JButton("Update User Name");
       updateNameButton.setBounds(0,240,170,25);
       updateNameButton.setFont(paragraphText);
       updateNameButton.setBackground(accentPink);

       //Setting for a test field for user to put the new name
       updateNameText = new JTextField(20);
       updateNameText.setBounds(180,240,150,25);

       //AddctionListener for updateName button. Create a UpdateUserNameMessage and add to the queue
       updateNameButton.addActionListener(e -> {
           String str = updateNameText.getText();
           try {
               Message msg = new UpdateUsernameMessage(str);
               queue.put(msg);
           } catch (InterruptedException exception) {
               // do nothing
           }
       });

       //Setting for a label to prompt user that name updated
       updateNameSuccess = new JLabel("User Name Updated!");
       updateNameSuccess.setBounds(340, 240, 170, 25);
       updateNameSuccess.setFont(paragraphText);
       updateNameSuccess.setForeground(accentPink);
       updateNameSuccess.setVisible(false);

       //Setting for a label to prompt user that update name fail
       updateNameFail = new JLabel("Invalid UserName");
       updateNameFail.setBounds(340, 240, 170, 25);
       updateNameFail.setFont(paragraphText);
       updateNameFail.setForeground(accentPink);
       updateNameFail.setVisible(false);

       //Setting for "Update Password" button
       updatePasswordButton = new JButton("Update Password");
       updatePasswordButton.setBounds(0,280,170,25);
       updatePasswordButton.setFont(paragraphText);
       updatePasswordButton.setBackground(accentPink);

       //Setting for a test field for user to put the new password
       passwordText = new JPasswordField(20);
       passwordText.setBounds(180,280, 150, 25);

       //AddctionListener for updatePassword button. Create a UpdatePasswordMessage and add to the queue
       updatePasswordButton.addActionListener(e -> {
           String str = passwordText.getText();
           try {
               Message msg = new UpdatePasswordMessage(str);
               queue.put(msg);
           } catch (InterruptedException exception) {
               // do nothing
           }
       });

       //Setting for a label to prompt user that password updated
       updatePasswordSuccess = new JLabel("Password Updated!");
       updatePasswordSuccess.setBounds(340, 280, 170, 25);
       updatePasswordSuccess.setFont(paragraphText);
       updatePasswordSuccess.setForeground(accentPink);
       updatePasswordSuccess.setVisible(false);

       //Setting for a label to prompt user that update password fail
       updatePasswordFail = new JLabel("Invalid Password");
       updatePasswordFail.setBounds(340, 280, 170, 25);
       updatePasswordFail.setFont(paragraphText);
       updatePasswordFail.setForeground(accentPink);
       updatePasswordFail.setVisible(false);

       //Setting for "Delete Account" button
       deleteAccountButton = new JButton("Delete Account");
       deleteAccountButton.setBounds(0,320, 170,25);
       deleteAccountButton.setFont(paragraphText);
       deleteAccountButton.setBackground(accentPink);

       //AddctionListener for deleteAccount button. Create a DeleteAccountMessage and add to the queue
       deleteAccountButton.addActionListener(e -> {
           try {
               Message msg = new DeleteAccountMessage();
               queue.put(msg);
           } catch (InterruptedException exception) {
               // do nothing
           }
       });

       //Setting for a label to prompt user that account deleted
       deleteSuccess = new JLabel("Account Deleted");
       deleteSuccess.setBounds(350, 320, 170, 25);
       deleteSuccess.setFont(paragraphText);
       deleteSuccess.setForeground(accentPink);
       deleteSuccess.setVisible(false);

       //Setting for a label to prompt user that account delete fail
       deleteFail = new JLabel("Delete Fail");
       deleteFail.setBounds(350, 320, 170, 25);
       deleteFail.setFont(paragraphText);
       deleteFail.setForeground(accentPink);
       deleteFail.setVisible(false);

       //Setting for "Log Out" button
       logOutButton = new JButton("Log Out");
       logOutButton.setBounds(170, 380,150,25);
       logOutButton.setFont(paragraphText);
       logOutButton.setBackground(accentPink);

       //AddctionListener for logOut button. Create a LogOutMessage and add to the queue
       logOutButton.addActionListener(e -> {
           try {
               Message msg = new LogOutMessage();
               queue.put(msg);
           } catch (InterruptedException exception) {
               // do nothing
           }
       });

       //Added all the buttons, labels and test fields to the frame
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
       this.add(TransferPage);
       this.add(homePage);

       this.setLayout(null);
       this.setVisible(true);
   }

    /**
     * Display updateNameSuccess label if val is true, else display updateNameFail label
     * @param val
     * @param str
     */
   public void updateNameSuccessFail(boolean val,String str)
   {
       if(val)
       {
           this.userNameLabel.setText("Username: "+str);
           this.updateNameSuccess.setVisible(true);
           this.updateNameFail.setVisible(false);
       }
       else
       {
           this.updateNameFail.setVisible(true);
           this.updateNameSuccess.setVisible(false);
       }
   }

    /**
     * Display updatePasswordSuccess label if val is true, else display updatePasswordFail label
     * @param val
     */
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

    /**
     * Display deleteSuccess label if val is true, else display deleteFail label
     * @param val
     */
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
