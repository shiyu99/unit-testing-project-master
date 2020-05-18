import csc4700.CartItem;
import csc4700.Item;
import csc4700.ShoppingCart;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShoppingCartTest {

    @Test(expected = NullPointerException.class)
    public void testAddItem() {

        ShoppingCart sc = new ShoppingCart();
        Item i = null;

        sc.addItem(i);
    }

    @Test
    public void testNullCartItem() {
        ShoppingCart sc = new ShoppingCart();
        Item i1 = new Item();

        sc.addItem(i1);

        CartItem c = sc.findCartItem(i1);

        assertEquals(c.getItem(), i1);
    }

    @Test
    public void testNotNullCartItem() {

        ShoppingCart sc = new ShoppingCart();
        Item i1 = new Item();
        i1.setName("car");
        Item i2 = new Item();
        i2.setName("car");

        sc.addItem(i1);
        sc.addItem(i2);

        CartItem c = sc.findCartItem(i1);

        assertEquals(c.getCount(), 2);
    }

    @Test
    public void testFindCartItem() {
        ShoppingCart sc = new ShoppingCart();
        Item i1 = new Item();
        i1.setName("car");
        Item i2 = new Item();
        i2.setName("car");

        sc.addItem(i1);
        sc.addItem(i2);

        CartItem c1 = sc.findCartItem(i1);
        CartItem c2 = sc.findCartItem(i2);

        assertEquals(c1.getItem(), i1);
        assertEquals(c2.getItem(), i2);
    }

    @Test
    public void testFindCartItemNotEqual() {
        ShoppingCart sc = new ShoppingCart();
        Item i1 = new Item();
        i1.setName("car");
        Item i2 = new Item();
        i2.setName("van");

        sc.addItem(i1);

        CartItem c1 = sc.findCartItem(i2);

        assertEquals(c1, null);
    }

    @Test
    public void testGetCartItem() {
        ShoppingCart sc = new ShoppingCart();
        Item i1 = new Item();
        i1.setName("car");
        CartItem c = new CartItem(i1);


        sc.addItem(i1);
        List<CartItem> cartItems = sc.getCartItems();
        assertTrue(cartItems.contains(c));
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteItemNull() {
        ShoppingCart sc = new ShoppingCart();
        Item i1 = null;
        sc.deleteItem(i1);

    }

    @Test
    public void testDeleteCartItemOne() {
        ShoppingCart sc = new ShoppingCart();
        Item i1 = new Item();
        i1.setName("car");
        sc.addItem(i1);
        //sc.deleteItem(i1);
        List<CartItem> cartItems = new ArrayList<CartItem>();
        //CartItem c = sc.findCartItem(i1);
        sc.deleteItem(i1);

        assertEquals(sc.getCartItems(),cartItems);

    }

    @Test
    public void testDeleteCartItemNull(){
        ShoppingCart sc = new ShoppingCart();
        Item i1 = new Item();
        i1.setName("car");
        Item i2 = new Item();
        i2.setName("apple");
        //sc.addItem(i1);
        List<CartItem> cartItems = new ArrayList<CartItem>();
        sc.addItem(i2);
        CartItem c = sc.findCartItem(i2);

        sc.deleteItem(i1);
        assertEquals(c.getCount(),1);

    }
    @Test
    public void testDeleteCartItem()
    {
        ShoppingCart sc = new ShoppingCart();
        Item i1 = new Item();
        i1.setName("car");
        Item i2 = new Item();
        i2.setName("car");

        sc.addItem(i1);
        sc.addItem(i2);
        sc.deleteItem(i1);
        CartItem c = sc.findCartItem(i2);
        assertEquals(c.getCount(),1);
    }
}
