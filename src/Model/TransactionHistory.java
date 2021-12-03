package Model;

import java.util.LinkedList;

public class TransactionHistory {
    //Instance Variable
    public LinkedList<Transaction> transactionHistoryList;

    //Constructor
    public TransactionHistory(){
        this.transactionHistoryList = new LinkedList<>();
    }

    /***
     * Gets the LinkedList that contains all of the transactions
     * @return transactionHistoryList
     */
    public LinkedList<Transaction> getTransactionHistoryList(){
        return  this.transactionHistoryList;
    }

    /***
     * Adds Transaction at the beginning of the linked list
     * @param t
     */
    public void add(Transaction t){
        transactionHistoryList.add(0,t);
    }

    /***
     * Make a new Iterator with functions defined for next() and hasNext()
     * @return Iterator for the transactions linked list
     */
    public TransactionsIterator getTransactions(){
        return new TransactionsIterator() {
            int i = 0;

            /***
             * Gets the next Transaction in the iterator
             * @return Transaction
             */
            @Override
            public Transaction next() {
                Transaction current = transactionHistoryList.get(i);
                i++;
                return current;
            }

            /***
             * true if there is another transaction in the list, false if there are no more transactions left to traverse
             * @return boolean
             */
            @Override
            public boolean hasNext() {
                return i < transactionHistoryList.size();
            }
        };
    }
}

//Shivam Amin | shivamamin4@gmail.com
