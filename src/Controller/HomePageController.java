package Controller;

import Controller.Messages.Message;
import Controller.Messages.SignInMessage;
import Controller.Messages.SignUpMessage;
import Model.Admin;
import Model.BankAccount;
import Model.User;
import View.HomePageView;
import View.RequestTransferView;
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

            if(message.getClass() == RequestOrTransferMessage.class)
            {
                RequestTransferView RTview = new RequestTransferView(queue);
                RequestTransferController RTcontroller = new RequestTransferController(queue,admin,user,RTview);
                homeView.dispose();
                RTcontroller.mainLoop();
            }

            else if(message.getClass() == SettingsPageMessage.class)
            {
                SettingsView view = new SettingsView(queue, user.getUsername(), user.getBankAccount().getBalance(),admin);
                SettingsController c = new SettingsController(queue, admin, user, view);
                homeView.dispose();
                c.mainLoop();
            }


        }
    }
}
