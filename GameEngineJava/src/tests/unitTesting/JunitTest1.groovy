package unitTesting

import org.junit.*

import static org.junit.Assert.*;
import java.util.*;

/**
 * @author mkyong
 *
 */

public class JunitTest1 {

    private Collection collection;
    public static String myString = "";

    @BeforeClass
    public static void oneTimeSetUp() {
        // one-time initialization code
        myString += "@BeforeClass - oneTimeSetUp\n"
    }

    @AfterClass
    public static void oneTimeTearDown() {
        // one-time cleanup code
        myString += "@AfterClass - oneTimeTearDown\n"
        System.out.println( myString )
    }

    @Before
    public void setUp() {
        collection = new ArrayList();
        myString += "@Before - setUp\n"
    }

    @After
    public void tearDown() {
        collection.clear();
        myString += "@After - tearDown\n"
    }

    @Test
    public void testEmptyCollection() {
        assertTrue(collection.isEmpty());
        myString += "@Test - testEmptyCollection\n"
    }

    @Test
    public void testOneItemCollection() {
        collection.add("itemA");
        assertEquals(1, collection.size());
        myString += "@Test - testOneItemCollection\n"
    }
}