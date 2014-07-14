package unitTesting

import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author TekMaTek on 08/07/2014
 * Modified from http://www.mkyong.com/unittest/junit-4-tutorial-1-basic-usage/
 *
 * Note: GroovyTestCase extends JUnit (using JUnit 3) which prevents annotations from working
 * by using @RunWith( JUnit4.class ) the class is forced to be run using JUnit4 instead of 3.
 * This allows Groovy to be extended and for JUnit 4 annotations to work.
 */

@RunWith( JUnit4.class )
public class JunitTest1 extends groovy.util.GroovyTestCase {

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
        collection = new ArrayList( );
        myString += "@Before - setUp\n"
    }

    @After
    public void tearDown() {
        collection.clear( );
        myString += "@After - tearDown\n"
    }

    @Test
    public void testEmptyCollection() {
        assertTrue( collection.isEmpty( ) );
        myString += "@Test - testEmptyCollection\n"
    }

    @Test
    public void testOneItemCollection() {
        collection.add( "itemA" );
        assertEquals( 1, collection.size( ) );
        myString += "@Test - testOneItemCollection\n"
    }
}