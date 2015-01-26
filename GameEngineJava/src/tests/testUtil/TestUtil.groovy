package testUtil

import org.gameEngine.engine.core.maths.Vector3f

/**
 * Created by TekMaTek on 23/07/2014.
 */
class TestUtil {

    static Random rand = new Random( );
    public static final float ROOT_MAX_FLOAT = 1.844674352395373E19;
    public static final float ROOT_MIN_FLOAT = 3.743392130574644E-23;


    public static float GetRandomFloat( float lowerBound, float upperBound ) {
        return rand.nextFloat( ) * ( upperBound - lowerBound ) + lowerBound;
    }

    public static float GetRandomFloat() {
        return rand.nextFloat( ) * ( Float.MAX_VALUE - Float.MIN_VALUE ) + Float.MIN_VALUE;
    }

    //Short floats can only be root of Float.MAX_VALUE due to calculations in Vector3f limiting their indicesLength.
    //IE doing floatA * floatB must give a value lower than Float.MAX_VALUE
    public static float GetRandomShortFloat() {
        return GetRandomFloat( ROOT_MAX_FLOAT, -ROOT_MIN_FLOAT );
    }

    public static boolean ApproaxEquals( float a, float b, int significantFigures ) {
        return ( Math.abs( a - b ) < Math.pow( 0.1, significantFigures )
                * Math.max( Math.abs( a ), Math.abs( b ) ) )
    }

    public static boolean ApproaxEquals( Vector3f vectorA, Vector3f vectorB, int significantFigures ) {
        return ( ApproaxEquals( vectorA.x, vectorB.x, significantFigures ) &&
                ApproaxEquals( vectorA.y, vectorB.y, significantFigures ) &&
                ApproaxEquals( vectorA.z, vectorB.z, significantFigures ) );
    }
}
