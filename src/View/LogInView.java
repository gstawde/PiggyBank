package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


import Controller.Message;
import Controller.SignInMessage;
import Controller.SignUpMessage;
import Model.Admin;
import Model.User;

public class LogInView extends JFrame{
    // BASIC PAGE STYLING
    final Color background = Color.decode("#272727");
    final Color accentPink = Color.decode("#E6BEAE");
    final Color genericText = Color.decode("#FFFFFF");
    final Color accentBlue = Color.decode("#A9BCD0");
    final Font paragraphText = new Font("Modern No. 20", Font.PLAIN, 16);
    final Font titleText = new Font("Modern No. 20", Font.PLAIN, 30);
    final Font headerText = new Font("Modern No. 20", Font.PLAIN, 20);


    private BlockingQueue<Message> queue;
    public Admin a;
    public JTable table;
    public JTextField username;
    public JTextField password;
    public JLabel title;
    public JButton signin;
    public JButton signup;
    public JLabel fail;
    public JLabel fail2;

    public LogInView(Admin a, BlockingQueue queue){
        this.getContentPane().setBackground(background);
        this.setSize(500, 500);


        this.a = a;
        this.queue =  queue;
        this.setLocation(0,0);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultLookAndFeelDecorated(true);


        title = new JLabel("Login");
        title.setBounds(175, 20, 150, 25);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(accentPink);
        title.setFont(titleText);


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

        this.add(title);
        this.add(username);
        this.add(password);
        this.add(signin);
        this.add(signup);

        fail =new JLabel("Sign In Failed");
        fail.setBounds(215,400,100,25);
        fail.setVisible(false);
        this.add(fail);

        fail2 =new JLabel("Username Already Exists");
        fail2.setBounds(175,400,300,25);
        fail2.setVisible(false);
        this.add(fail2);

        signin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    queue.put(new SignInMessage("Sign In Attempted"));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });


        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    queue.put(new SignUpMessage("Sign Up Attempted"));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });


        this.setLayout(null);
        this.setVisible(true);

    }

    public void logInAttempt(boolean success,User u){

        if(success){
            HomePageView hpv = new HomePageView(u);
            this.dispose();
        }else{
            this.fail.setVisible(true);
            this.fail2.setVisible(false);
        }
    }

    public void signUpAttempt(boolean success, User u){
        if(success){
            HomePageView hpv = new HomePageView(u);
            this.dispose();
        }else{
            this.fail2.setVisible(true);
            this.fail.setVisible(false);
        }
    }

    public static void main(String[] a){
        Admin admin = new Admin();
        new LogInView(admin, new LinkedBlockingQueue());
    }
}