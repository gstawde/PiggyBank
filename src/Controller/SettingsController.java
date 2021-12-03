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
            Message message = null;

            try {
                message = queue.take();
            } catch (InterruptedException exception) {
                // do nothing
            }

            if(message.getClass() == UpdateUsernameMessage.class) {
                // if update username was clicked
                UpdateUsernameMessage newName = (UpdateUsernameMessage) message;
                if(newName.getNewUserName().length() == 0 || admin.getUsernameToUser().containsKey(newName.getNewUserName()))
                {
                    settingsView.updateNameSuccessFail(false,newName.getNewUserName());
                }
                else
                {
                    admin.deleteUser(user.getUsername());
                    user.setUsername(newName.getNewUserName());
                    admin.addUser(user);
                    settingsView.updateNameSuccessFail(true,newName.getNewUserName());
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
                    LogInView lTview = new LogInView(admin, queue);
                    LogInController logIn = new LogInController(queue,admin,lTview);
                    settingsView.dispose();
                    logIn.mainLoop();
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
}
