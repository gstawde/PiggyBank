import Model.BankAccount;
import Model.Transaction;
import Model.User;
import org.junit.*;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTester {

    private User gargi = new User("gargi.tawde", "gpassword", new BankAccount("Gargi", "Tawde", 13.13));
    private User chint = new User("chint.patel", "cpassword", new BankAccount("Chint", "Patel", 12.12));
    Transaction t = new Transaction(gargi, chint, 11.11);

    @Test
    public void getSenderTest() {
        assertEquals("chint.patel", t.getSender().getUsername(), "Should be chint.patel");
    }

    @Test
    public void getReceiverTest() {
        assertEquals("gargi.tawde", t.getReceiver().getUsername(), "Should be gargi.tawde");
    }

    @Test
    public void getAmountTest() {
        assertEquals(11.11, t.getAmount(), "Shouild be 11.11");
    }

    @Test
    public void equalTransactionsCompareToTest() {
        assertEquals(0, t.compareTo(t), "Should be true");
    }

    @Test
    public void sameUsersDiffAmountsCompareToTest() {
        Transaction t2 = new Transaction(gargi, chint, 14.14);
        assertEquals(-1, t.compareTo(t2), "Should be -1");
    }

    @Test
    public void differentUsersCompareToTest() {
        User jun = new User("jun.wu", "jpassword", new BankAccount("Jun", "Wu", 16.16));
        Transaction t3 = new Transaction(gargi, jun, 15.15);
        assertEquals(1, t.compareTo(t3), "Should be 1");
    }
}

// © Oct 18, 2021 | Gargi Tawde | gargi.tawde01@gmail.com