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
        if(this.firstName.compareTo(o.firstName) < 0)
        {
            return -1;
        }
        else if(this.firstName.compareTo(o.firstName) > 0)
        {
            return 1;
        }
        else
        {
            if(this.lastName.compareTo(o.lastName) < 0)
            {
                return -1;
            }
            else if(this.lastName.compareTo(o.lastName) > 0)
            {
                return 1;
            }
            else
                {
                    if(this.balance > o.balance)
                    {
                        return -1;
                    }
                    else if(this.balance < o.balance)
                    {
                        return 1;
                    }
                    else
                    {
                        return 0;
                    }
                }
        }
    }
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof BankAccount) {
            BankAccount u = (BankAccount) o;
            return this.compareTo(u) == 0;
        }
        return false;
    }
}
//Jun Wu
