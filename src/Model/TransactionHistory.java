package Model;

import java.util.ArrayList;

public class TransactionHistory {
    public ArrayList<Transaction> transactionHistoryList;
    public TransactionHistory(){
        this.transactionHistoryList = new ArrayList<>();
    }
    public ArrayList<Transaction> getTransactionHistoryList(){
        return  this.transactionHistoryList;
    }
    public boolean add(Transaction t){
        return this.transactionHistoryList.add(t);
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
