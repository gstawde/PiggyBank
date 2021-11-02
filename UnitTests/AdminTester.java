import Model.Admin;
import Model.BankAccount;
import Model.User;
import org.junit.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;


public class AdminTester {

    private Admin a = new Admin();

    @Before
    public void setUp()
    {
        a.addUser(new User("sa01", "123", new BankAccount("Shivam", "Amin", 50.00)));
        a.addUser(new User("cpSJSU", "hello", new BankAccount("Chint", "Patel", 50.00)));
        a.addUser(new User("gtCS", "sacadw", new BankAccount("Gargi", "Tawde", 50.00)));
        a.addUser(new User("Jun_Wu", "12asca3", new BankAccount("Jun", "Wu", 50.00)));
    }



    @Test
    public void validAddMethodTesting(){
        User u = new User("npc", "123", new BankAccount("Jane", "Doe", 50.00));
        assertTrue(a.addUser(u), "Should return true");
    }
    @Test
    public void invalidAddMethodTesting(){
        User u = new User("sa01", "123", new BankAccount("Jane", "Doe", 50.00));
        assertFalse(a.addUser(u), "Should return false");
    }


    @Test
    public void validRemoveMethodTesting(){
        assertTrue(a.deleteUser("sa01"), "Should return true");
    }
    @Test
    public void invalidRemoveMethodTesting(){
        assertFalse(a.deleteUser("random"), "Should return false");
    }


    @Test
    public void validAuthenticateMethodTesting(){
        assertNotNull(a.authenticateUser("sa01","123"), "Should return a non null Model.User");
    }
    @Test
    public void invalidUsernameAuthenticateMethodTesting(){
        assertNull(a.authenticateUser("a01","123"), "Should return null");
    }
    @Test
    public void invalidPasswordAuthenticateMethodTesting(){
        assertNull(a.authenticateUser("sa01","1243"), "Should return null");
    }


    @Test
    public void getHashMapTesting(){

        HashMap<String, User> map = new HashMap<>();
        map.put("sa01",(new User("sa01", "123", new BankAccount("Shivam", "Amin", 50.00))));
        map.put("cpSJSU",(new User("cpSJSU", "hello", new BankAccount("Chint", "Patel", 50.00))));
        map.put("gtCS",(new User("gtCS", "sacadw", new BankAccount("Gargi", "Tawde", 50.00))));
        map.put("Jun_Wu",(new User("Jun_Wu", "12asca3", new BankAccount("Jun", "Wu", 50.00))));

        assertEquals(a.getUsernameToUser(),map,"Both hashmaps should be equal");
    }
}
//Shivam Amin | shivamamin4@gmail.com

