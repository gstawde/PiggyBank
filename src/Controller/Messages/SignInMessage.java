package Controller.Messages;

/***
 * Class that implements Message class, tells the BlockingQueue that sign in was attempted
 */
public class SignInMessage implements Message{
    //Instance Variable
    String s;

    //Constructor
    public SignInMessage(String s) {this.s = s;}


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
