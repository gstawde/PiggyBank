package View;

import Controller.Messages.Message;
import Controller.Messages.SignInMessage;
import Controller.Messages.SignUpMessage;
import Model.Admin;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class LogInView extends JFrame{
    // BASIC PAGE STYLING
    final Color background = Color.decode("#272727");
    final Color accentPink = Color.decode("#E6BEAE");
    final Font titleText = new Font("Modern No. 20", Font.PLAIN, 30);
    final Font paragraphText = new Font("Modern No. 20", Font.PLAIN, 16);
    final Color accentBlue = Color.decode("#A9BCD0");

    //Instance Variables
    public Admin a;
    public JTextField username;
    public JTextField password;
    public JLabel title;
    public JButton signin;
    public JButton signup;
    public JLabel failSignIn;
    public JLabel failSignUp;

    //Constructor
    public LogInView(Admin a, BlockingQueue<Message> queue){
        this.a = a;

        //Set Background
        this.getContentPane().setBackground(background);

        //Set Size of page
        this.setSize(500, 500);

        //Set Close Operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Initialize all of the Labels, textFields, and buttons
        title = new JLabel("Login");
        username = new JTextField("Username");
        password = new JTextField("Password");
        signin = new JButton("Sign In");
        signup = new JButton("Sign Up");
        failSignIn =new JLabel("Sign In Failed");
        failSignUp =new JLabel("User info Already Exists/Invalid");

        //Set Bounds of all elememts
        title.setBounds((this.getWidth()-75)/2, 20, 150, 25);
        username.setBounds(175, 100, 150, 25);
        password.setBounds(175, 200, 150, 25);
        signin.setBounds(200,300,100,25);
        signup.setBounds(200,350,100,25);
        failSignIn.setBounds(215,400,100,25);
        failSignUp.setBounds(150,400,300,25);


        //Set Color of all Elements
        title.setForeground(accentPink);
        username.setBackground(accentBlue);
        password.setBackground(accentBlue);
        signin.setBackground(accentPink);
        signup.setBackground(accentPink);
        failSignIn.setForeground(accentPink);
        failSignUp.setForeground(accentPink);

        //Set fonts for Jlabels
        title.setFont(titleText);
        failSignIn.setFont(paragraphText);
        failSignUp.setFont(paragraphText);

        //Set both of the fail Labels to false for now
        failSignIn.setVisible(false);
        failSignUp.setVisible(false);

        //Add all of the JButtons, textFields and Jlabels to the JFrame
        this.add(title);
        this.add(username);
        this.add(password);
        this.add(signin);
        this.add(signup);
        this.add(failSignIn);
        this.add(failSignUp);

        /***
         * Action listener for the signin button
         * Whenever it is pressed it sends the queue a message saying that sign in was attempted
         */
        signin.addActionListener(e -> {
            try {
                queue.put(new SignInMessage("Sign In Attempted"));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        /***
         * Action listener for the signUp button
         * Whenever it is pressed it sends the queue a message saying that sign up was attempted
         */
        signup.addActionListener(e -> {
            try {
                queue.put(new SignUpMessage("Sign Up Attempted"));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        //Make the layout null and set the jframe visible
        this.setLayout(null);
        this.setVisible(true);

    }

    /***
     * Method is called from the login controller, if there is an invalid log in attempted
     */
    public void logInFail(){
        //Make the signin Jlabel visible and hide the signup jlabel if it was already open
        this.failSignIn.setVisible(true);
        this.failSignUp.setVisible(false);
    }

    /***
     * Method is called from the login controller, if there is an invalid sign up attempted
     */
    public void signUpFail(){
        //Make the signup Jlabel visible and hide the signin jlabel if it was already open
        this.failSignUp.setVisible(true);
        this.failSignIn.setVisible(false);
    }
}
//Shivam Amin | shivamamin4@gmail.com