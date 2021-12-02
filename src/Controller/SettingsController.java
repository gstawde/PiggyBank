package Controller;

import Controller.Messages.*;
import Model.*;
import View.*;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SettingsController {

    BlockingQueue<Message> queue;
    Admin admin;
    User user;
    SettingsView settingsView;
    Iterator<Transaction> iterator; // NEW CODE


    public SettingsController(BlockingQueue<Message> queue, Admin admin, User user, SettingsView settingsView)
    {
        this.iterator = iterator;
        this.queue = queue;
        this.admin = admin;
        this.user = user;
        this.settingsView = settingsView;
    }

    public void mainLoop()
    {
        while (settingsView.isDisplayable()) {
            System.out.println("TEST SETTING");
            Message message = null;

            try {
                System.out.println("TEST SETTINGS 1");
                message = queue.take();
                System.out.println("TEST SETTINGS 2");
            } catch (InterruptedException exception) {
                // do nothing
            }

            if(message.getClass() == UpdateUsernameMessage.class) {
                // if update username was clicked
                UpdateUsernameMessage newName = (UpdateUsernameMessage) message;
                if(newName.getNewUserName().length() == 0 || !admin.getUsernameToUser().containsKey(user.getUsername()))
                {
                    settingsView.updateNameSuccessFail(false);
                }
                else
                {
                    admin.deleteUser(user.getUsername());
                    user.setUsername(newName.getNewUserName());
                    admin.addUser(user);
                    settingsView.updateNameSuccessFail(true);
                }
            }

            else if(message.getClass() == UpdatePasswordMessage.class)
            {
                // update password button was clicked
                UpdatePasswordMessage newPassword = (UpdatePasswordMessage) message;
                if(newPassword.getPassword().length() == 0 || !admin.getUsernameToUser().containsKey(user.getUsername()))
                {
                    settingsView.updatePasswordSuccessFail(false);
                }
                else
                {
                    user.setPassword(newPassword.getPassword());
                    settingsView.updatePasswordSuccessFail(true);
                }
            }

            else if(message.getClass() == DeleteAccountMessage.class)
            {
                // delete account button was clicked
                if(admin.deleteUser(user.getUsername()))
                {
                    settingsView.deleteSuccessFail(true);
                }
                else
                {
                    settingsView.deleteSuccessFail(false);
                }
            }

            else if(message.getClass() == LogOutMessage.class)
            {
                // log out button was clicked
                LogInView lTview = new LogInView(admin, queue);
                LogInController logIn = new LogInController(queue,admin,lTview);
                settingsView.dispose();
                logIn.mainLoop();
            }

            else if(message.getClass() == RequestOrTransferMessage.class)
            {
                RequestTransferView RTview = new RequestTransferView(queue);
                RequestTransferController RTcontroller = new RequestTransferController(queue,admin,user,RTview);
                settingsView.dispose();
                RTcontroller.mainLoop();
            }
            else if(message.getClass() == HomePageMessage.class)
            {
                HomePageView view = new HomePageView(user.getTransactionIterator(),queue);
                HomePageController c = new HomePageController(queue, user, admin, view);
                settingsView.dispose();
                c.mainLoop();
            }


        }
    }
    /*
    public SettingsController(Admin admin, User user)
    {
        SettingsView view = new SettingsView(user.getUsername(), user.getBankAccount().getBalance());
        view.updateNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = view.updateNameText.getText();
                if(newName.length() == 0)
                {
                    view.updateNameFail.setVisible(true);
                    view.updateNameSuccess.setVisible(false);
                }
                else
                {
                    admin.deleteUser(user.getUsername());
                    user.setUsername(newName);
                    admin.addUser(user);
                    view.updateNameSuccess.setVisible(true);
                    view.updateNameFail.setVisible(false);
                }
            }
        });

        view.updatePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPassword = view.passwordText.getText();
                if(newPassword.length() == 0)
                {
                    view.updatePasswordFail.setVisible(true);
                    view.updatePasswordSuccess.setVisible(false);
                }
                else
                {
                    user.setPassword(newPassword);
                    view.updatePasswordSuccess.setVisible(true);
                    view.updatePasswordFail.setVisible(false);
                }
            }
        });

        view.deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(admin.deleteUser(user.getUsername()))
                {
                    view.deleteSuccess.setVisible(true);
                    view.deleteFail.setVisible(false);
                }
                else
                {
                    view.deleteFail.setVisible(true);
                    view.deleteSuccess.setVisible(false);
                }

            }
        });

        view.logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInToHomePage logIn = new LogInToHomePage(admin);
                view.dispose();
            }
        });
    }
    */
    public static void main(String[] args)
    {
        Admin admin = new Admin();
        User u = new User("s", "a", new BankAccount("sm","a",500.00));
        User b = new User("b","a", new BankAccount("b","a",60));
        u.payUser(50,b);
        b.payUser(20,u);
        admin.addUser(u);
        admin.addUser(b);
        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
        SettingsView view = new SettingsView(queue, u.getUsername(), u.getBankAccount().getBalance(),admin);
        SettingsController c = new SettingsController(queue, admin, u, view);
        c.mainLoop();
    }


}
