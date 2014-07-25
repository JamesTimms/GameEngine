package tests

import org.gameEngine.engine.core.InputObserver
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import testUtil.SetupUtil

/**
 * Created by TekMaTek on 04/07/2014.
 */


@RunWith( JUnit4.class )
public class InputTest extends groovy.util.GroovyTestCase {

    static SetupUtil setup;

    @BeforeClass
    public static void SetupKeyboard() {
        setup = SetupUtil( )
    }

    @AfterClass
    public static void TearDownKeyboard() {
        setup.StopGame( )
    }

    @Test
    void testGetKeyAndSucceed() {
        InputObserver testInput = new InputObserver() {
            @Override
            protected boolean IsKeyPressed( int keyNumber ) {
                return true;
            }
        }

        testInput.addObserver( new Observer( ) {
            @Override
            void update( Observable o, Object arg ) {
                assertEquals( o, testInput );
            }
        } )

        testInput
        //Fake key press
        //Wait for inner class to receive response

    }

    @Test
    void testGetKeyAndFail() {
        InputObserver.InjectKeyPress( 56 )
        assertFalse( InputObserver.GetKey( 57 ) )
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
