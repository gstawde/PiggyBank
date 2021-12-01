package View;

import Controller.Message;
import Controller.NavBarUseMessage;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

public class MenuView implements ActionListener {
    // BASIC PAGE STYLING
    final Color background = Color.decode("#272727");
    final Color accentPink = Color.decode("#E6BEAE");
    final Color genericText = Color.decode("#FFFFFF");
    final Color accentBlue = Color.decode("#A9BCD0");
    final Font paragraphText = new Font("Modern No. 20", Font.PLAIN, 16);
    final Font titleText = new Font("Modern No. 20", Font.PLAIN, 30);
    final Font headerText = new Font("Modern No. 20", Font.PLAIN, 20);

    public JMenuBar mb;
    public JMenu menu;
    public JMenuItem homePage, requestOrTransfer, settings;

    public JFrame j;
    User user;
    BlockingQueue<Message> queue;


    public MenuView(JFrame j, User user,BlockingQueue<Message> queue){

        this.j = j;
        this.user = user;
        this.queue = queue;

        // PAGE HEADER
        // navbar:
        mb = new JMenuBar();
        menu = new JMenu("Menu");
        homePage = new JMenuItem("Home");
        requestOrTransfer = new JMenuItem("Request or Transfer");
        settings = new JMenuItem("Settings");
        menu.add(homePage);
        menu.add(requestOrTransfer);
        menu.add(settings);
        mb.add(menu);
        j.setJMenuBar(mb);
        j.setSize(500, 500);
        homePage.addActionListener(this);
        requestOrTransfer.addActionListener(this);
        settings.addActionListener(this);
        // navbar styling:
        mb.setForeground(background);
        mb.setBackground(background);
        mb.setOpaque(true);
        menu.setFont(headerText);
        menu.setForeground(accentPink);
        homePage.setBackground(accentBlue);
        homePage.setFont(paragraphText);
        homePage.setForeground(background);
        requestOrTransfer.setBackground(accentBlue);
        requestOrTransfer.setFont(paragraphText);
        requestOrTransfer.setForeground(genericText);
        settings.setBackground(accentBlue);
        settings.setFont(paragraphText);
        settings.setForeground(genericText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Message m;
        JFrame newFrame;
        if (e.getSource() == homePage) {
            m = new NavBarUseMessage("\'Home\' menu item clicked");
            newFrame = new HomePageView(user);
            newFrame.setVisible(true);
            queue.add(m);
        } else if (e.getSource() == requestOrTransfer) {
            m = new NavBarUseMessage("\'Request or Transfer\' menu item clicked");
            newFrame = new RequestTransferView(queue,user);
            newFrame.setVisible(true);
            queue.add(m);
        } else if (e.getSource() == settings) {
            m = new NavBarUseMessage("\'Settings\' menu item clicked");
            newFrame = new SettingsView(queue, user, user.getBankAccount().getBalance());
            newFrame.setVisible(true);
            queue.add(m);
        }
    }


}
