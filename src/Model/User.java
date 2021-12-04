package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/***
 *The Model.User class defines an individual user of the app and stores Model.User data
 */

public class User implements Comparable<User>, Serializable {
    //Instance Variables
    private String username;
    private String password;
    private BankAccount bankAccount;
    private TransactionHistory transactionHistory;

    //constructor
    public User(String username, String password, BankAccount bankAccount){
        this.username = username;
        this. password = password;
        this.bankAccount = bankAccount;
        transactionHistory = new TransactionHistory();
    }

    //Methods

    /***
     * Pays Model.User recipient the amount of money
     * @param amount
     * @param recipient
     */
    public boolean payUser(double amount, User recipient) {
        if(this.bankAccount.getBalance() < amount || amount <= 0){
            return false;
        }
        recipient.bankAccount.updateBalance(amount);
        this.bankAccount.updateBalance((-1)*amount);
        transactionHistory.add(new Transaction(recipient,this,amount));
        recipient.transactionHistory.add(new Transaction(recipient,this,amount));
        return true;
    }

    /***
     * getter for Username
     * @return username
     */
    public String getUsername(){
        return username;
    }

    /**
     * setter for Username
     * @param name
     */
    public void setUsername(String name)
    {
        this.username = name;
    }

    /***
     * getter for Password
     * @return password
     */
    public String getPassword(){
        return password;
    }

    /**
     * setter for password
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /***
     * getter for bankAccount
     * @return bankAccount
     */
    public BankAccount getBankAccount(){ return bankAccount;}

    /***
     * getter for Model.Transaction History
     * @return transactionHistory
     */
    public LinkedList<Transaction> getTransactionHistory() {
        return transactionHistory.getTransactionHistoryList();
    }

    public TransactionsIterator getTransactionIterator() {
        return transactionHistory.getTransactions();
    }

    /***
     * compare user to another user to store in list
     * @param o
     * @return -1 if this comes first, 0 if equal, or 1 if this comes after
     */
    @Override
    public int compareTo(User o) {
        if(!this.username.equals(o.username)){
            return this.username.compareTo(o.username);
        }else if(!this.password.equals(o.password)){
            return this.password.compareTo(o.password);
        }else{
            return this.bankAccount.compareTo(o.bankAccount);
        }
    }

    /***
     * returns true if 2 users are the same
     * @param o
     * @return true if equal else false
     */
    @Override
    public boolean equals(Object o) {
        User u = (User)o;
        return this.compareTo(u) == 0;
    }
}

//Chint Patel | patelchint2002@gmail.com