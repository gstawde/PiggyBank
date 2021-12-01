package Controller;

public class SignUpMessage implements Message{
    String s;
    public SignUpMessage(String s) {
        this.s = s;
    }

    public String toString(){
        return this.s;
    }
}
