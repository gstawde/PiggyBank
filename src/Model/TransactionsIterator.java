package Model;

/***
 * Iterator interface so that we can make our own iterator for the Linked List we are using to store the transaction history
 */
public interface TransactionsIterator {
    Transaction next();
    boolean hasNext();
}

//Shivam Amin | shivamamin4@gmail.com
