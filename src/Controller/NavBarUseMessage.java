package Controller;

public class NavBarUseMessage implements Message {

    String menuItemSelected;

    public NavBarUseMessage(String menuItemSelected) {
        this.menuItemSelected = menuItemSelected;
    }

    public String getMenuItemSelected() {
        return menuItemSelected;
    }

}
