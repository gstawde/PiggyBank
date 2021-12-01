package Model;

import java.io.Serializable;

/**
 * The Model.BankAccount represent the bank amount of the user
 * A Model.BankAccount contains a first name, a last name and the balance
 */
public class BankAccount implements Comparable<BankAccount>, Serializable
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

    /**
     * This method gets the first name of the account holder
     * @return a string contains the first name of the account holder
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * This method gets the last name of the account holder
     * @return a string contains the last name of the account holder
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * This method gets the balance of the account
     * @return the balance of this Model.BankAccount
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     * This method add a double value to the balance of this Model.BankAccount
     * @param amount
     */
    public void updateBalance(double amount)
    {
        balance += amount;
    }

    /**
     * This method compare two Model.BankAccount object
     * @param o
     * @return -1 if this Model.BankAccount is smaller
     *          1 if this Model.BankAccount is larger
     *          0 if two Model.BankAccount is the same
     */
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

    /**
     * This method check if two Model.BankAccount is the same or not
     * @param o
     * @return true if two Model.BankAccount is the same
     *         false if two Model.BankAccount is different
     */
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
