import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private BankAccount bankAccount;
    private ArrayList<Transactions> transactionHistory;
    private ArrayList<Requests> requests;

    public void User(String username, String password, BankAccount bankAccount){
        this.username = username;
        this. password = password;
        this.bankAccount = bankAccount;
    }

    public void payUser(double amount, User recipient) {
        recipient.bankAccount.updateBalance(amount);
        transactionHistory.add(new Transactions(recipient,this,amount));
    }

    public void requestFromUser(double amount, User sender) {
        sender.requests.add(new Requests(amount, this, sender));
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public ArrayList<Transactions> getTransactionHistory() {
        return transactionHistory;
    }

    public ArrayList<Requests> getRequests() {
        return requests;
    }
}