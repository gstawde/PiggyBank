package Controller;

import Model.Admin;

public class App {
    public Admin admin;

    public App(){
        admin = new Admin();
        LogInToHomePage start = new LogInToHomePage(this.admin);
    }

    public static void main(String[] args) {
        App start = new App();
    }
}
