package View;

import javax.swing.*;
import java.awt.*;


import Model.Admin;
import Model.BankAccount;
import Model.User;

public class LogInView extends JFrame{
    public JTable table;
    public JTextField username;
    public JTextField password;
    public JLabel title;
    public JButton signin;
    public JButton signup;
    public JLabel fail;
    public JLabel fail2;

    public LogInView(){
        this.setLocation(0,0);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultLookAndFeelDecorated(true);


        title = new JLabel("Login");
        title.setBounds(245, 0, 150, 25);

        username = new JTextField("Username");
        username.setBounds(175, 100, 150, 25);

        password = new JTextField("Password");
        password.setBounds(175, 200, 150, 25);

        signin = new JButton("Sign In");
        signin.setBounds(200,300,100,25);

        signup = new JButton("Sign Up");
        signup.setBounds(200,350,100,25);
        this.add(title);
        this.add(username);
        this.add(password);
        this.add(signin);
        this.add(signup);

        fail =new JLabel("Sign In Failed");
        fail.setBounds(215,400,100,25);
        fail.setVisible(false);
        this.add(fail);

        fail2 =new JLabel("Sign Up Failed");
        fail2.setBounds(215,400,100,25);
        fail2.setVisible(false);
        this.add(fail2);

        this.setLayout(null);
        this.setVisible(true);
    }

    public static void main(String[] a){
        Admin admin = new Admin();
        new LogInView();
    }
}