import org.gameEngine.engine.core.Window
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by TekMaTek on 29/07/2014.
 */

@RunWith( JUnit4.class )
class ObserverTest extends groovy.util.GroovyTestCase {

    private static Window window;

    @BeforeClass
    public static void SetupKeyboard() {
        window = new Window( );
    }

    @AfterClass
    public static void TearDownKeyboard() {
        window.Dispose( );
    }

    @Test
    void testEventObjectSent() {
        boolean dataReceived = false;
        Observable testObservable = new Observable()

        Observer testObserver = new Observer( ) {
            @Override
            void update( Observable o, Object arg ) {
                dataReceived = true;
            }
        }
        testObservable.addObserver( testObserver )
        testObservable.setChanged( )
        testObservable.notifyObservers( )

        assertTrue( dataReceived )
    }

    @Test
    void testEventObjectSent2() {

        Observable testObservable = new Observable();

        Observer testObserver = new Observer( ) {
            @Override
            void update( Observable o, Object arg ) {
                assertEquals( o, testObservable );
            }
        }
        testObservable.addObserver( testObserver )
        testObservable.setChanged( )
        testObservable.notifyObservers( )

    }

    @Test
    void testEventTriggers() {

    }

}
