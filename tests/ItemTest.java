import org.junit.Test;
import csc4700.Item;
import static org.junit.Assert.*;


public class ItemTest {

    @Test
    public void testObjectEquality() {

        // Setup
        Item i1 = new Item();
        Item i2 = i1;

        // Test
        boolean b = i1.equals(i2);

        // Verfiy
        assertTrue(b);

        // Clean
    }
    @Test
    public void testHashCode()
    {
        //Set Up
        Item a = new Item();
        a.setName("apple");

        //Test
        int code =a.hashCode();

        //Verify
        assertEquals(a.hashCode(),"apple".hashCode());

        //clean
    }

    @Test
    public void testSetName()
    {
        //Setup
        Item b = new Item();
        //test
        b.setName("apple");
        //verify
        assertEquals(b.getName(),"apple");
        //clean


    }
    @Test
    public void testSetCost()
    {
        //Setup
        Item b = new Item();
        //test
        b.setCost(2);
        //verify
        assertEquals(b.getCost(),2);
        //clean

    }
    @Test
    public void testSetDescription()
    {
        //Setup
        Item b = new Item();
        //test
        b.setDescription("tasty");
        //verify
        assertEquals(b.getDescription(),"tasty");
        //clean

    }

    @Test
    public void testObjectNull() {

        // Setup
        Item i1 = new Item();
        i1.setName("car");
        i1.setCost(12);
        i1.setDescription("A nice car.");

        Item i2 = new Item();

        assertFalse(i1.equals(i2));
    }

    @Test
    public void testSameClassEquality() {

        Item i1 = new Item();

        String s = new String();

        assertFalse(i1.equals(s));
    }

    @Test
    public void testObjectNameEqualityTrue() {

        Item i1 = new Item();
        i1.setName("car");

        Item i2 = new Item();
        i2.setName("car");

        assertTrue(i1.equals(i2));
    }

    @Test
    public void testObjectNameEqualityFalse() {

        Item i1 = new Item();
        i1.setName("car");

        Item i2 = new Item();
        i2.setName("van");

        assertFalse(i1.equals(i2));
    }

    @Test
    public void testGetName() {

        Item i1 = new Item();
        i1.setName("car");

        assertEquals(i1.getName(), "car");
    }

    @Test
    public void testGetCost() {

        Item i1 = new Item();
        i1.setCost(12);

        assertEquals(i1.getCost(), 12);
    }

    @Test
    public void testGetDescription() {

        Item i1 = new Item();
        i1.setDescription("This is an item");

        assertEquals(i1.getDescription(), "This is an item");
    }













}
