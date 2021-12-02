package Controller.Messages;

public class UpdateUsernameMessage implements Message
{
    private String username;

    public UpdateUsernameMessage(String name)
    {
        this.username = name;
    }

    public String getNewUserName()
    {
        return this.username;
    }
}
