package tests

import org.gameEngine.engine.core.InputObserver
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.Display

/**
 * Created by TekMaTek on 04/07/2014.
 */

@RunWith( JUnit4.class )
public class InputTest extends groovy.util.GroovyTestCase {

    private InputObserver input = new InputObserver( );

    @BeforeClass
    public static void SetupKeyboard() {
        Display.create( )
        Keyboard.create( )
        input.Update( )
    }

    @AfterClass
    public static void TearDownKeyboard() {
//        Display.create( )
//        Keyboard.create( )
    }

    @Test
    void testGetKeyAndSucceed() {
        Input.InjectKeyPress( 56 )
        assertTrue( Input.GetKey( 56 ) )
    }

    @Test
    void testGetKeyAndFail() {
        Input.InjectKeyPress( 56 )
        assertFalse( Input.GetKey( 57 ) )
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
