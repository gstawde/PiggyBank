import java.util.ArrayList;

public class User implements Comparable<User>{
    private String username;
    private String password;
    private BankAccount bankAccount;
    private ArrayList<Transaction> transactionHistory;
    private ArrayList<Request> requests;

    public User(String username, String password, BankAccount bankAccount){
        this.username = username;
        this. password = password;
        this.bankAccount = bankAccount;
    }

    public void payUser(double amount, User recipient) {
        recipient.bankAccount.updateBalance(amount);
        transactionHistory.add(new Transaction(recipient,this,amount));
    }

    public void requestFromUser(double amount, User sender) {
        sender.requests.add(new Request(amount, this, sender));
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

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
    @Override
    public boolean equals(Object o) {
        User u = (User)o;
        return this.compareTo(u) == 0;
    }
}