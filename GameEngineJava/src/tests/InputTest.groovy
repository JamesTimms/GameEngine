package tests

import org.junit.*

import org.gameEngine.engine.core.Input
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.Display

/**
 * Created by TekMaTek on 04/07/2014.
 */
public class InputTest {

    @BeforeClass
    public static void SetupKeyboard() {
        Display.create( )
        Keyboard.create( )
    }

    @AfterClass
    public static void TearDownKeyboard() {
//        Display.create( )
//        Keyboard.create( )
    }

    @Test
    void testGetKeyAndSucceed() {
//        Input.InjectKeyPress( 56 )
//        assertTrue( Input.GetKey( 56 ) )
    }

    @Test
    void testGetKeyAndFail() {
//        Input.InjectKeyPress( 56 )
//        assertFalse( Input.GetKey( 57 ) )
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
