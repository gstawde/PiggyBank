package Controller;

import Controller.Messages.*;
import Model.*;
import View.*;

import java.util.concurrent.BlockingQueue;

public class SettingsController {

    BlockingQueue<Message> queue;
    Admin admin;
    User user;
    SettingsView settingsView;

    public SettingsController(BlockingQueue<Message> queue, Admin admin, User user, SettingsView settingsView)
    {
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
                //take a message in the front of the queue
                message = queue.take();
            } catch (InterruptedException exception) {
                // do nothing
            }

            if(message.getClass() == UpdateUsernameMessage.class) {
                // if update username was clicked
                UpdateUsernameMessage newName = (UpdateUsernameMessage) message;

                //check if the test field has no character and if the user account exists
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
                //check if the test field has no character and if the user account exists
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
                // check if the account deleted or not
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
                LogInView lTview = new LogInView(admin, queue);  //go to log in view
                LogInController logIn = new LogInController(queue,admin,lTview);
                settingsView.dispose();
                logIn.mainLoop();   //start the LogInController
            }

            else if(message.getClass() == RequestOrTransferMessage.class)
            {
                RequestTransferView RTview = new RequestTransferView(queue);  //go to the RequestTransferView
                RequestTransferController RTcontroller = new RequestTransferController(queue,admin,user,RTview);
                settingsView.dispose();
                RTcontroller.mainLoop();    //start the RequestTransferController
            }

        }
    }
}
