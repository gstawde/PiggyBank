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


    public Admin a;
    public JTextField username;
    public JTextField password;
    public JLabel title;
    public JButton signin;
    public JButton signup;
    public JLabel fail;
    public JLabel fail2;

    public LogInView(Admin a, BlockingQueue<Message> queue){
        this.getContentPane().setBackground(background);
        this.setSize(500, 500);


        this.a = a;
        //this.setLocation(0,0);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);


        title = new JLabel("Login");
        title.setBounds((this.getWidth()-75)/2, 20, 150, 25);
        title.setSize(150,25);
        title.setForeground(accentPink);
        title.setFont(titleText);
        this.add(title);
        //setLocationRelativeTo(null);



        username = new JTextField("Username");
        username.setBounds(175, 100, 150, 25);
        username.setBackground(accentPink);

        password = new JTextField("Password");
        password.setBounds(175, 200, 150, 25);
        password.setBackground(accentPink);

        signin = new JButton("Sign In");
        signin.setBounds(200,300,100,25);
        signin.setBackground(accentPink);

        signup = new JButton("Sign Up");
        signup.setBounds(200,350,100,25);
        signup.setBackground(accentPink);


        this.add(username);
        this.add(password);
        this.add(signin);
        this.add(signup);

        fail =new JLabel("Sign In Failed");
        fail.setBounds(215,400,100,25);
        fail.setForeground(accentPink);
        fail.setVisible(false);
        this.add(fail);

        fail2 =new JLabel("Username Already Exists");
        fail2.setBounds(175,400,300,25);
        fail2.setForeground(accentPink);
        fail2.setVisible(false);
        this.add(fail2);

        signin.addActionListener(e -> {
            try {
                queue.put(new SignInMessage("Sign In Attempted"));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });


        signup.addActionListener(e -> {
            try {
                queue.put(new SignUpMessage("Sign Up Attempted"));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });


        this.setLayout(null);
        this.setVisible(true);

    }

    public void logInAttempt(boolean success){

        if(!success){
            this.fail.setVisible(true);
            this.fail2.setVisible(false);
        }
    }

    public void signUpAttempt(boolean success){
        if(!success){
            this.fail2.setVisible(true);
            this.fail.setVisible(false);
        }
    }

   /* public static void main(String[] a){
        Admin admin = new Admin();
        new LogInView(admin, new LinkedBlockingQueue());
    }*/
}