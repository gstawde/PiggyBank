package Controller;

import Controller.Messages.*;
import Model.*;
import View.*;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

/**
 * Controller class for the setting page, communicate with SettingView using message and queue
 */
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
        //While the settingView is displaying
        while (settingsView.isDisplayable()) {
            Message message = null;

            //take the message in the front of the queue
            try {
                message = queue.take();
            } catch (InterruptedException exception) {
                // do nothing
            }

            // if update username was clicked
            if(message.getClass() == UpdateUsernameMessage.class) {
                UpdateUsernameMessage newName = (UpdateUsernameMessage) message;
                //check if the username is empty and if the name already exists in the admin
                if(newName.getNewUserName().length() == 0 || admin.getUsernameToUser().containsKey(newName.getNewUserName()))
                {
                    settingsView.updateNameSuccessFail(false,newName.getNewUserName());
                }
                //else add the same user with the new name to the admin
                else
                {
                    admin.deleteUser(user.getUsername());
                    user.setUsername(newName.getNewUserName());
                    admin.addUser(user);
                    settingsView.updateNameSuccessFail(true,newName.getNewUserName());
                }
            }

            // update password button was clicked
            else if(message.getClass() == UpdatePasswordMessage.class)
            {
                UpdatePasswordMessage newPassword = (UpdatePasswordMessage) message;
                //check if the password is empty and if the user is in the admin
                if(newPassword.getPassword().length() == 0 || !admin.getUsernameToUser().containsKey(user.getUsername()))
                {
                    settingsView.updatePasswordSuccessFail(false);
                }
                //else set user's password to the new password
                else
                {
                    user.setPassword(newPassword.getPassword());
                    settingsView.updatePasswordSuccessFail(true);
                }
            }

            // delete account button was clicked
            else if(message.getClass() == DeleteAccountMessage.class)
            {
                //if the deletion is success, go back to the log in page by calling LogInController
                if(admin.deleteUser(user.getUsername()))
                {
                    settingsView.deleteSuccessFail(true);
                    LogInView lTview = new LogInView(admin, queue);
                    LogInController logIn = new LogInController(queue,admin,lTview);
                    settingsView.dispose();
                    logIn.mainLoop();
                }
                //else display the fail label in SettingView
                else
                {
                    settingsView.deleteSuccessFail(false);
                }
            }

            // log out button was clicked, go to log in page by calling LogInController
            else if(message.getClass() == LogOutMessage.class)
            {
                LogInView lTview = new LogInView(admin, queue);
                LogInController logIn = new LogInController(queue,admin,lTview);
                settingsView.dispose();
                logIn.mainLoop();
            }

            //Transfer button was clicked, go to Transfer page by calling RequestTransferController
            else if(message.getClass() == TransferPageMessage.class)
            {
                TransferView RTview = new TransferView(queue);
                TransferController RTcontroller = new TransferController(queue,admin,user,RTview);
                settingsView.dispose();
                RTcontroller.mainLoop();
            }

            //HomePage button was clicked, go to Home page by calling HomePageController
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
