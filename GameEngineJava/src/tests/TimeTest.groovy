package tests

import org.gameEngine.engine.core.Time
import org.junit.Test

/**
 * Created by TekMaTek on 02/07/2014.
 */
class TimeTest extends groovy.util.GroovyTestCase {

    Time testTime
    static final long TEST_TIME = 5000000000l
    static final long TEST_TIME2 = 5000000001l
    static final long TEST_TIME_MIN = -2147483648l
    static final long TEST_TIME_MAX = 2147483647l

    @Test
    void testGetTime() {
        assert ( true )
    }

    @Test
    void testGetDeltaTime() {
        GetDeltaTimeOnce( TEST_TIME );
        GetDeltaTimeOnce( TEST_TIME2 );
        GetDeltaTimeOnce( TEST_TIME_MIN );
        GetDeltaTimeOnce( TEST_TIME_MAX );
    }

    void GetDeltaTimeOnce( long time ) {
        testTime = new Time( )
        testTime.SetDeltaTime( time )
        assertEquals testTime.GetDeltaTime( ), time
    }

    @Test
    void testSetDelta() {
        testTime = new Time( )
        testTime.SetDeltaTime TEST_TIME
        assertEquals testTime.GetDeltaTime, TEST_TIME
    }
}