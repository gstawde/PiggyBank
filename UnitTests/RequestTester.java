import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;

public class RequestTester {
    public Request createRequestForTesting(){
        return new Request( 20,
                            new User("sa01", "123", new BankAccount("Shivam", "Amin", 50.00)),
                            new User("cpSJSU", "hello", new BankAccount("Chint", "Patel", 50.00))
        );
    }

    @Test
    public void getAmountTesting(){
        Request r = createRequestForTesting();
        assertEquals(20, r.getAmount(),"Values should be equal");
    }
    @Test
    public void getRequesterTesting(){
        Request r = createRequestForTesting();
        assertEquals(new User("sa01", "123", new BankAccount("Shivam", "Amin", 50.00)),
                r.getRequester(),"Values should be equal");
    }
    @Test
    public void getSenderTesting(){
        Request r = createRequestForTesting();
        assertEquals(new User("cpSJSU", "hello", new BankAccount("Chint", "Patel", 50.00)),
                r.getFulfiller(),"Values should be equal");
    }


    @Test
    public void equalObjectsCompareToTesting(){
        Request r = createRequestForTesting();
        Request r2 = createRequestForTesting();
        assertEquals(0, r.compareTo(r2));
    }
    @Test
    public void largerObjectFirstCompareToTesting(){
        Request r = createRequestForTesting();
        Request r2 = new Request(10,
        new User("sa01", "123", new BankAccount("Shivam", "Amin", 50.00)),
        new User("cpSJSU", "hello", new BankAccount("Chint", "Patel", 50.00)));

        assertEquals(1, r.compareTo(r2), "Should return 1");
    }
    @Test
    public void smallerObjectFirstCompareToTesting(){
        Request r = createRequestForTesting();
        Request r2 = new Request(30,
                new User("sa01", "123", new BankAccount("Shivam", "Amin", 50.00)),
                new User("cpSJSU", "hello", new BankAccount("Chint", "Patel", 50.00)));

        assertEquals(-1, r.compareTo(r2), "Should return -1");
    }

    @Test
    public void validEqualsTesting(){
        Request r = createRequestForTesting();
        Request r2 = createRequestForTesting();
        assertTrue(r.equals(r2),"Should return true");
    }
    @Test
    public void invalidEqualsTesting(){
        Request r = createRequestForTesting();
        Request r2 = new Request(30,
                new User("sa01", "123", new BankAccount("Shivam", "Amin", 50.00)),
                new User("cpSJSU", "hello", new BankAccount("Chint", "Patel", 50.00)));

        assertFalse(r.equals(r2),"Should return false");
    }
}
