package Model;

/**
 * Model.Transaction class contains the information required at the macro level for a transaction to take place
 * (the users that are the sender and receiver, as well as the amount to be transferred).
 */
public class Transaction implements Comparable<Transaction> {

    // ATTRIBUTES
    private User sender;
    private User receiver;
    private double amount;


    // CONSTRUCTORS
    public Transaction(User receiver, User sender, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }


    // METHODS
    /**
     * Gets the sender for this Model.Transaction and returns it.
     * @return The Model.User designated as sender for this Model.Transaction.
     */
    public User getSender() {
        return this.sender;
    }

    /**
     * Gets the receiver for this Model.Transaction and returns it.
     * @return The Model.User designated as the receiver for this Model.Transaction.
     */
    public User getReceiver() {
        return this.receiver;
    }

    /**
     * Gets the amount to be transferred between parties during the Model.Transaction and returns it.
     * @return The amount to be transferred between sender and receiver.
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Method that will be used to test instances of the Model.Transaction class
     * @param args A String array you can pass into main(); i.e. if you use main() on the command line.
     */
    public static void main(String[] args) {
	// write your code here
    }

    @Override
    public int compareTo(Transaction t) {
        // Check if they are the same transaction:
        if (this.sender == t.getSender() && this.receiver == t.getReceiver() && this.amount == t.getAmount()) {
            return 0;
        } else if (this.sender == t.getSender() && this.receiver == t.getReceiver() && this.amount != t.getAmount()) {
            return -1;
        } else {
            return 1;
        }
    }
}
// © Oct 12, 2021 | Gargi Tawde | gargi.tawde01@gmail.com