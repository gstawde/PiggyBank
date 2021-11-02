import Model.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTester
{
    private BankAccount account1;

    @BeforeEach
    public void setUp()
    {
        account1 = new BankAccount("Jun", "Wu", 1000);
    }

    @Test
    public void getFirstNameTest()
    {
        assertEquals("Jun", account1.getFirstName(), "Should be Jun");
    }

    @Test
    public void getLastNameTest()
    {
        assertEquals("Wu", account1.getLastName(), "Should be Wu");
    }

    @Test
    public void getBalanceTest()
    {
        assertEquals(1000, account1.getBalance(), "Should be 1000");
    }

    @Test
    public void updateBalanceDepositTest()
    {
        account1.updateBalance(500);
        assertEquals(1500, account1.getBalance(), "Should be 1500");
    }

    @Test
    public void updateBalanceWithdrawalTest()
    {
        account1.updateBalance(-500);
        assertEquals(500, account1.getBalance(), "Should be 500");
    }

    @Test
    public void smallerObjFirstCompareToTest()
    {
        BankAccount account2 = new BankAccount("Shivam", "Amin", 1000);
        assertEquals(-1, account1.compareTo(account2), "Should be -1");
    }

    @Test
    public void LargerObjFirstCompareToTest()
    {
        BankAccount account2 = new BankAccount("Gargi", "Tawde", 1000);
        assertEquals(1, account1.compareTo(account2), "Should be 1");
    }

    @Test
    public void equalObjCompareToTest()
    {
        BankAccount account2 = new BankAccount("Jun", "Wu", 1000);
        assertEquals(0, account1.compareTo(account2), "Should be 0");
    }

    @Test
    public void validEqualsTest()
    {
        BankAccount account2 = new BankAccount("Jun", "Wu", 1000);
        assertTrue(account1.equals(account2), "Should be true");
    }

    @Test
    public void invalidEqualsTest()
    {
        BankAccount account2 = new BankAccount("Chint", "Patel", 1500);
        assertFalse(account1.equals(account2), "Should be false");
    }
}
//Jun Wu
