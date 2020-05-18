import csc4700.Item;
import csc4700.ShoppingCart;
import csc4700.exceptions.SerializedFormatException;
import org.junit.After;
import org.junit.Test;
import csc4700.CartItem;
import csc4700.Backup;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class BackupTest {

    public static final String FIELD_SEPARATOR = ",";
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private File tempFile;

    @Test(expected = NullPointerException.class)
    public void testSerializeShoppingCartNull()
    {
        Backup bp = new Backup();
        ShoppingCart cart = null;

        bp.serializeShoppingCart(cart);
    }

    @Test
    public void testSerializeShoppingCartNotNull()
    {
        Backup bp = new Backup();
        ShoppingCart sc = new ShoppingCart();

        Item i1 = new Item();
        i1.setName("car");
        i1.setCost(10);
        i1.setDescription("good");
        Item i2 = new Item();
        i2.setName("apple");
        i2.setCost(12);
        i2.setDescription("great");
        //Item i3 = new Item();
        //i3.setName("apple");

        sc.addItem(i1);
        sc.addItem(i2);
        //sc.addItem(i3);
        String actual = bp.serializeShoppingCart(sc);
        //String expected = "car,10,good,1" + Backup.LINE_SEPARATOR;
        String expected = "car,10,good,1" + Backup.LINE_SEPARATOR + "apple,12,great,1"+Backup.LINE_SEPARATOR;

        assertEquals(actual,expected);


    }

    @Test(expected = NullPointerException.class)
    public void testDeserializeShoppingCartNull() throws SerializedFormatException
    {
        Backup bp = new Backup();
        String s = null;

        bp.deserializeShoppingCart(s);
    }

    @Test(expected = SerializedFormatException.class)
    public void testDeserializeShoppingStringFormat() throws  SerializedFormatException {
        Backup bp = new Backup();

        StringBuffer itemLine = new StringBuffer();
        itemLine.append("car");
        itemLine.append(FIELD_SEPARATOR);
        itemLine.append(10);
        itemLine.append(FIELD_SEPARATOR);
        itemLine.append("A nice car");

        bp.deserializeShoppingCart(itemLine.toString());
    }

    @Test
    public void testDeserializeShoppingRightString() throws  SerializedFormatException {
        Backup bp = new Backup();

        StringBuffer itemLine = new StringBuffer();
        itemLine.append("car");
        itemLine.append(FIELD_SEPARATOR);
        itemLine.append(10);
        itemLine.append(FIELD_SEPARATOR);
        itemLine.append("A nice car");
        itemLine.append(FIELD_SEPARATOR);
        itemLine.append(4);

        ShoppingCart cart = bp.deserializeShoppingCart(itemLine.toString());

        List<CartItem> cartItems = cart.getCartItems();

        CartItem ci = cartItems.get(0);

        assertEquals(ci.getCount(), 4);

        Item i = ci.getItem();

        assertEquals(i.getName(), "car");
        assertEquals(i.getCost(), 10);
        assertEquals(i.getDescription(), "A nice car");
    }

    @Test
    public void testSaveNewShoppingCart()
            throws IOException {

        tempFile = File.createTempFile("FileReadersDemoTests", "");
        String filename = tempFile.getAbsolutePath();
        File f = new File(filename);
        Backup bc = new Backup();

        ShoppingCart sc = new ShoppingCart();
        Item i1 = new Item();
        i1.setName("car");
        i1.setCost(10);
        i1.setDescription("good");
        Item i2 = new Item();
        i2.setName("apple");
        i2.setCost(12);
        i2.setDescription("great");
        sc.addItem(i1);
        sc.addItem(i2);

        bc.saveShoppingCart(sc, f);

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        StringBuffer allLines = new StringBuffer();
        while ((line = br.readLine()) != null) {
            allLines.append(line);
            allLines.append(LINE_SEPARATOR);
        }
        br.close();
        String actual = allLines.toString();
        String expected = "car,10,good,1" + Backup.LINE_SEPARATOR + "apple,12,great,1"+Backup.LINE_SEPARATOR;

        assertEquals( expected, actual);

    }

    @Test(expected = FileNotFoundException.class)
    public void testLoadShoppingCartNotFound()
            throws IOException, SerializedFormatException {

        File f = new File("/rahul/thapa");
        Backup bc = new Backup();

        bc.loadShoppingCart(f);
    }

    @Test
    public void testLoadShoppingCartWorkingFile()
            throws IOException, SerializedFormatException {

        tempFile = File.createTempFile("FileReadersDemoTests", "");
        String filename = tempFile.getAbsolutePath();
        File f = new File(filename);
        Backup bc = new Backup();

        ShoppingCart sc = new ShoppingCart();
        Item i = new Item();
        i.setName("car");
        i.setCost(10);
        i.setDescription("A nice car");

        sc.addItem(i);
        sc.addItem(i);
        sc.addItem(i);
        sc.addItem(i);

        bc.saveShoppingCart(sc, f);

        ShoppingCart sc1 = bc.loadShoppingCart(f);

        List<CartItem> cartItems = sc1.getCartItems();

        CartItem ci = cartItems.get(0);

        assertEquals(ci.getCount(), 4);

        Item i1 = ci.getItem();

        assertEquals(i1.getName(), "car");
        assertEquals(i1.getCost(), 10);
        assertEquals(i1.getDescription(), "A nice car");
    }


    @Test
    public void testSaveShoppingCartAlreadyExisted()
            throws IOException, SerializedFormatException {
        tempFile = File.createTempFile("FileReadersDemoTests", "");
        String filename = tempFile.getAbsolutePath();
        File f = new File(filename);
        Backup bc = new Backup();

        ShoppingCart sc = new ShoppingCart();
        Item i = new Item();
        i.setName("Car");
        sc.addItem(i);

        bc.saveShoppingCart(sc, f);

        ShoppingCart sc1 = new ShoppingCart();
        Item i1 = new Item();
        i1.setName("Van");
        sc1.addItem(i1);

        bc.saveShoppingCart(sc1, f);

        ShoppingCart sc2 = bc.loadShoppingCart(f);

        List<CartItem> l = sc2.getCartItems();

        String s = l.get(0).getItem().getName();

        assertFalse(s.equals("Car"));

    }
    
    @After
    public void cleanUpCommonTestStuff() {
        if (tempFile != null) {
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }
    }
}
