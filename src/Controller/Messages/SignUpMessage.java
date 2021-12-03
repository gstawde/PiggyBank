package Controller.Messages;

/***
 * Class that implements Message class, tells the BlockingQueue that sign up was attempted
 */
public class SignUpMessage implements Message{
    //Instance Variable
    String s;

    //Constructor
    public SignUpMessage(String s) {this.s = s;}

    /***
     * Overridden toString method basically a getter
     * @return s
     */
    @Override
    public String toString(){
        return this.s;
    }
}
//Shivam Amin | shivamamin4@gmail.com