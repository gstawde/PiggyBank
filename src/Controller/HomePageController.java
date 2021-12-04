package Controller;

import Controller.Messages.Message;
import Controller.Messages.TransferPageMessage;
import Controller.Messages.SettingsPageMessage;
import Model.Admin;
import Model.User;
import View.HomePageView;
import View.TransferView;
import View.SettingsView;

import java.util.concurrent.BlockingQueue;

public class HomePageController
{
    BlockingQueue<Message> queue;
    User user;
    Admin admin;
    HomePageView homeView;

    public HomePageController(BlockingQueue<Message> queue, User user, Admin admin, HomePageView view) {
        this.queue = queue;
        this.admin = admin;
        this.homeView = view;
        this.user = user;
    }

    public void mainLoop()
    {
        while(homeView.isDisplayable()){
            Message message = null;
            try {
                message = queue.take();
            } catch (InterruptedException exception) {
                // do nothing
            }

            if(message.getClass() == TransferPageMessage.class)
            {
                TransferView transferView = new TransferView(queue);
                TransferController transferController = new TransferController(queue,admin,user,transferView);
                homeView.dispose();
                transferController.mainLoop();
            }

            else if(message.getClass() == SettingsPageMessage.class)
            {
                SettingsView view = new SettingsView(queue, user.getUsername(), user.getBankAccount().getBalance());
                SettingsController c = new SettingsController(queue, admin, user, view);
                homeView.dispose();
                c.mainLoop();
            }


        }
    }
}
