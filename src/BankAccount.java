public class BankAccount implements Comparable<BankAccount>
{
    private String firstName;
    private String lastName;
    private double balance;

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

    @Override
    public int compareTo(BankAccount o) {
        if(!this.firstName.equals(o.firstName)){
            return this.firstName.compareTo(o.firstName);
        }else if(!this.lastName.equals(o.lastName)){
            return this.lastName.compareTo(o.lastName);
        }else{
            return (int)Math.signum(o.balance - this.balance);
        }
    }
    @Override
    public boolean equals(Object o) {
        BankAccount u = (BankAccount)o;
        return this.compareTo(u) == 0;
    }
}

