package Controller;

import Model.*;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsController {
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

    public static void main(String[] args)
    {
        Admin admin = new Admin();
        User u = new User("s", "a", new BankAccount("sm","a",500.00));
        User b = new User("b","a", new BankAccount("b","a",60));
        u.payUser(50,b);
        b.payUser(20,u);
        admin.addUser(u);
        admin.addUser(b);
        new SettingsController(admin, u);
    }
}
