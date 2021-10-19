/***
 * This the request object for when people request money from other users
 */
public class Request implements Comparable<Request>{

    //Instance Variables and Constructor
    private Double  amount;
    private User requester;
    private User fulfiller;

    public Request(double amount, User requester, User fulfiller){
        this.amount    =    amount;
        this.requester = requester;
        this.fulfiller = fulfiller;
    }

    //Methods

    /***
     * getter for Amount
     * @return this.amount
     */
    public double getAmount() {
        return this.amount;
    }

    /***
     * getter for Requester
     * @return this.requester
     */
    public User getRequester() {
        return this.requester;
    }

    /***
     * getter for Fulfiller
     * @return this.fulfiller
     */
    public User getFulfiller() {
        return this.fulfiller;
    }

    /***
     * Compares 2 requests
     * @param r
     * @return 1 if the 2nd request is larger, -1 if the 2nd request is smaller, and 0 if they are the same
     */
    public int compareTo(Request r) {
        if(!this.requester.equals(r.requester)) {
            return this.requester.compareTo(r.requester);
        }else if(!this.fulfiller.equals(r.fulfiller)) {
            return this.fulfiller.compareTo(r.fulfiller);
        }else{
            return (int)Math.signum(this.amount.compareTo(r.amount));
        }
    }

    /***
     * Takes in object o and checks if this and o are deep equal
     * @param o
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object o){
        return compareTo((Request)o) == 0;
    }
}
//Shivam Amin | shivamamin4@gmail.com
