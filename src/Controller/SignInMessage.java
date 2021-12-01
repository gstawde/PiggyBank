package Controller;

public class SignInMessage implements Message{
    String s;
    public SignInMessage(String s) {
        this.s = s;
    }

    public String toString(){
        return this.s;
    }
}
