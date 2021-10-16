public class BankAccount
{
    String firstName;
    String lastName;
    double balance;

    public BankAccount(String first, String last, double balance)
    {
        this.firstName = first;
        this.lastName = last;
        this.balance = balance;
    }

    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public double getBalance()
    {
        return balance;
    }

    public void updateBalance(double amount)
    {
        balance += amount;
    }
}

