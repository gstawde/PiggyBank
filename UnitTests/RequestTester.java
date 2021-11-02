import Model.BankAccount;
import Model.Request;
import Model.User;
import org.junit.*;

import static org.junit.jupiter.api.Assertions.*;

public class RequestTester {
    private Request r = new Request( 20,
            new User("sa01", "123", new BankAccount("Shivam", "Amin", 50.00)),
            new User("cpSJSU", "hello", new BankAccount("Chint", "Patel", 50.00))
    );

    @Test
    public void getAmountTesting(){
        assertEquals(20, r.getAmount(),"Values should be equal");
    }
    @Test
    public void getRequesterTesting(){
        assertEquals(new User("sa01", "123", new BankAccount("Shivam", "Amin", 50.00)),
                r.getRequester(),"Values should be equal");
    }
    @Test
    public void getSenderTesting(){
        assertEquals(new User("cpSJSU", "hello", new BankAccount("Chint", "Patel", 50.00)),
                r.getFulfiller(),"Values should be equal");
    }


    @Test
    public void equalObjectsCompareToTesting(){
        Request r2 = r;
        assertEquals(0, r.compareTo(r2));
    }
    @Test
    public void largerObjectFirstCompareToTesting(){
        Request r2 = new Request(10,
        new User("sa01", "123", new BankAccount("Shivam", "Amin", 50.00)),
        new User("cpSJSU", "hello", new BankAccount("Chint", "Patel", 50.00)));

        assertEquals(1, r.compareTo(r2), "Should return 1");
    }
    @Test
    public void smallerObjectFirstCompareToTesting(){
        Request r2 = new Request(30,
                new User("sa01", "123", new BankAccount("Shivam", "Amin", 50.00)),
                new User("cpSJSU", "hello", new BankAccount("Chint", "Patel", 50.00)));

        assertEquals(-1, r.compareTo(r2), "Should return -1");
    }

    @Test
    public void validEqualsTesting(){
        Request r2 = r;
        assertTrue(r.equals(r2),"Should return true");
    }
    @Test
    public void invalidEqualsTesting(){
        Request r2 = new Request(30,
                new User("sa01", "123", new BankAccount("Shivam", "Amin", 50.00)),
                new User("cpSJSU", "hello", new BankAccount("Chint", "Patel", 50.00)));

        assertFalse(r.equals(r2),"Should return false");
    }
}
//Shivam Amin | shivamamin4@gmail.com