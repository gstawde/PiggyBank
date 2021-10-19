
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTester{
    @Test
    public void payUserTest(){
        User ab = new User("ab", "123", new BankAccount("a","b",100));
        User cd = new User("cd", "123", new BankAccount("c","d",100));

        ab.payUser(50,cd);
        assertEquals(150,cd.getBankAccount().getBalance(), 0.001);
        assertEquals(50, ab.getBankAccount().getBalance(), 0.001);
    }

    @Test
    public void requestUserTest() {
        User ab = new User("ab", "123", new BankAccount("a","b",100));
        User cd = new User("cd", "123", new BankAccount("c","d",100));

        ab.requestFromUser(50,cd);
        Request req = new Request(50,ab,cd);
        assertTrue(req.equals(cd.getRequests().get(0)), "should return true" );

    }

    @Test
    public void equalsUserTest() {
        User ab = new User("ab", "123", new BankAccount("a","b",100));
        User cd = new User("cd", "123", new BankAccount("c","d",100));
        assertFalse(ab.equals(cd), "should return false");
        assertTrue(ab.equals(new User("ab", "123", new BankAccount("a","b",100))), "should return true");
    }

    @Test
    public void getUsernameTest() {
        User ab = new User("ab", "123", new BankAccount("a","b",100));
        assertTrue(ab.getUsername().equals("ab"), "should return true");
    }

    @Test
    public void getPasswordTest() {
        User ab = new User("ab", "123", new BankAccount("a","b",100));
        assertTrue(ab.getPassword().equals("123"), "should return true");
    }

    @Test
    public void getBankAccountTest() {
        User ab = new User("ab", "123", new BankAccount("a","b",100));
        assertTrue(ab.getBankAccount().equals(new BankAccount("a","b",100)), "should return true");
    }

    @Test
    public void getTransactionsTest() {
        User ab = new User("ab", "123", new BankAccount("a","b",100));
        User cd = new User("cd", "123", new BankAccount("c","d",100));
        ab.payUser(50,cd);
        boolean result = ab.getTransactionHistory().get(0).compareTo(new Transaction(cd,ab,50)) == 0;
        assertTrue(result, "should return true");
    }

    @Test
    public void getRequestsTest() {
        User ab = new User("ab", "123", new BankAccount("a","b",100));
        User cd = new User("cd", "123", new BankAccount("c","d",100));
        ab.requestFromUser(50,cd);
        assertTrue(cd.getRequests().get(0).equals(new Request(50,ab,cd)), "should return true");
    }


}
// Chint Patel | patelchint2002@gmail.com