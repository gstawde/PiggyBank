package Controller.Messages;

public class UpdatePasswordMessage implements Message
{
    private String password;

    public UpdatePasswordMessage(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return this.password;
    }
}
