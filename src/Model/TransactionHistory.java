package Model;

import java.util.ArrayList;
import java.util.LinkedList;

public class TransactionHistory {


    public LinkedList<Transaction> transactionHistoryList;
    public TransactionHistory(){
        this.transactionHistoryList = new LinkedList<>();
    }
    public LinkedList<Transaction> getTransactionHistoryList(){
        return  this.transactionHistoryList;
    }
    public void add(Transaction t){
        transactionHistoryList.add(0,t);
    }
    public TransactionsIterator getTransactions(){
        return new TransactionsIterator() {
            int i = 0;
            @Override
            public Transaction next() {
                Transaction current = transactionHistoryList.get(i);
                i++;
                return current;
            }

            @Override
            public boolean hasNext() {
                return i < transactionHistoryList.size();
            }
        };
    }


}
