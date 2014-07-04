package tests

import org.junit.Test

/**
 * Created by TekMaTek on 04/07/2014.
 */
class InputTest extends GroovyTestCase {

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
