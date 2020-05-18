import csc4700.Item;
import csc4700.exceptions.InvalidCountException;
import org.junit.Test;
import csc4700.CartItem;
import static org.junit.Assert.*;

public class CartItemTest {


    @Test
    public void testIncrementCountByOne(){
        //set up
        Item i = new Item();

        CartItem c = new CartItem(i);
        c.setCount(12);
        c.incrementCountByOne();

        assertEquals(c.getCount(), 13);
    }
    @Test
    public void testDecrementCountByOne() {

        Item i = new Item();

        CartItem c = new CartItem(i);
        c.setCount(12);
        c.decrementCountByOne();

        assertEquals(c.getCount(), 11);
    }

    @Test
    public void testObjectEquality() {
        Item i = new Item();
        CartItem c1 = new CartItem(i);
        CartItem c2 = c1;

        assertTrue(c1.equals(c2));
    }

    @Test
    public void testObjectNull() {
        Item i1 = new Item();
        i1.setName("car");
        CartItem c1 = new CartItem(i1);

        Item i2 = new Item();
        CartItem c2 = new CartItem(i2);

        assertFalse(c1.equals(c2));
    }

    @Test
    public void testHashCodeNotNull() {
        Item i = new Item();
        i.setName("app");
        CartItem c = new CartItem(i);
        //test
        int code = c.hashCode();
        int code1 = i.hashCode();
        assertEquals(code, code1);
    }

    @Test
    public void testSameClassEquality() {

        Item i1 = new Item();
        i1.setName("car");
        CartItem c1 = new CartItem(i1);

        String s = new String();

        assertFalse(c1.equals(s));
    }

    @Test
    public void testObjectItemEqualityTrue() {

        Item i1 = new Item();
        i1.setName("car");
        CartItem c1 = new CartItem(i1);

        CartItem c2 = new CartItem(i1);

        assertTrue(c1.equals(c2));
    }

    @Test
    public void testObjectItemEqualityFalse() {

        Item i1 = new Item();
        i1.setName("car");
        CartItem c1 = new CartItem(i1);

        Item i2 = new Item();
        i1.setName("van");
        CartItem c2 = new CartItem(i2);

        assertFalse(c1.equals(c2));
    }

    @Test(expected = InvalidCountException.class)
    public void testInvalidCountException() {
        Item i = new Item();
        i.setName("car");

        CartItem c = new CartItem(i);
        c.setCount(0);
    }

    @Test
    public void testSetCount() {
        Item i = new Item();
        i.setName("car");

        CartItem c = new CartItem(i);
        c.setCount(1);

        assertEquals(c.getCount(), 1);
    }

    @Test
    public void testSetItem() {
        Item i1 = new Item();
        i1.setName("car");

        CartItem c = new CartItem(i1);

        Item i2 = new Item();
        i2.setName("van");

        c.setItem(i2);

        assertEquals(c.getItem(), i2);
    }

    @Test
    public void testGetItem()
    {
        Item i = new Item();
        CartItem c = new CartItem(i);
        i.setName("car");

        assertEquals(c.getItem(),i);
    }
    @Test
    public void testGetCount(){
        Item i = new Item();
        CartItem c = new CartItem(i);
        i.setName("car");
        c.setCount(2);

        assertEquals(c.getCount(),2);
    }
}
