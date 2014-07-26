package tests

import org.gameEngine.engine.core.Window
import org.gameEngine.engine.core.input.KeyboardEvents
import org.gameEngine.engine.core.input.KeyboardObserver
import org.gameEngine.engine.core.input.KeyboardWrapper
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by TekMaTek on 04/07/2014.
 */


@RunWith( JUnit4.class )
public class InputTest extends groovy.util.GroovyTestCase {

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
    void testEventTriggers() {
        boolean dataReceived = false;

        KeyboardWrapper keyboard = new KeyboardWrapper( ) {
            @Override
            public boolean IsKeyDown( int keyNumber ) {
                return true;
            }
        }
        KeyboardEvents events = new KeyboardEvents( keyboard )
        KeyboardObserver testInput = new KeyboardObserver( events )

        Observer testObserver = new Observer( ) {
            @Override
            void update( Observable o, Object arg ) {
                dataReceived = true;
            }
        }
        testInput.addObserver( testObserver )

        testInput.CheckForEvents( )
        assertTrue( dataReceived )
    }

    @Test
    void testGetKeyAndFail() {

        KeyboardWrapper keyboard = new KeyboardWrapper( ) {
            @Override
            public boolean IsKeyDown( int keyNumber ) {
                return true;
            }
        }
        KeyboardEvents events = new KeyboardEvents( keyboard )
        KeyboardObserver testInput = new KeyboardObserver( events )

        Observer testObserver = new Observer( ) {
            @Override
            void update( Observable o, Object arg ) {
                assertEquals( o, testInput );
            }
        }
        testInput.addObserver( testObserver )
        testInput.CheckForEvents( )

    }

    @Test
    void testGetKeyDown() {
    }

    @Test
    void testGetKeyUp() {

    }

    @Test
    void testGetMouse() {

    }

    @Test
    void testGetMouseDown() {

    }

    @Test
    void testGetMouseUp() {

    }
}
