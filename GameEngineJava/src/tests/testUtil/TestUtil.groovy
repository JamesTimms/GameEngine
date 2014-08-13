package testUtil

import org.gameEngine.engine.core.MainComponent
import org.gameEngine.engine.core.Window

/**
 * Created by TekMaTek on 23/07/2014.
 */
class TestUtil {

    static Random rand = new Random();

    public static float GetRandomFloat( float lowerBound, float upperBound ) {
        return rand.nextFloat( ) * ( upperBound - lowerBound ) + lowerBound;
    }

    public static float GetRandomFloat(  ) {
        return rand.nextFloat( ) * ( Float.MAX_VALUE - Float.MIN_VALUE ) + Float.MIN_VALUE;
    }

}
