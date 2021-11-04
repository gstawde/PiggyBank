package View;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class View extends JFrame{
    JLabel userNameLabel;
    JButton button;
    public View(String s){
        this.userNameLabel = new JLabel(s);
        this.button = new JButton("Test");
        this.add(userNameLabel);
        this.add(button, BorderLayout.WEST);
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] a) {
        View v = new View("s");
    }


}
