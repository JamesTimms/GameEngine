package org.gameEngine.engine.core.maths

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import static testUtil.TestUtil.GetRandomFloat;

/**
 * Created by TekMaTek on 05/08/2014.
 */

@RunWith( JUnit4 )
class Vector3fTest extends GroovyTestCase {

    @Test
    void testLength() {
        int TESTS_TO_RUN = 1000;
        for( int testsRun = 0; testsRun < TESTS_TO_RUN; testsRun++ ) {
            LengthTestsWithFiveSigFig( ShortenFloatForVectorLength( GetRandomFloat( ) ),
                    ShortenFloatForVectorLength( GetRandomFloat( ) ),
                    ShortenFloatForVectorLength( GetRandomFloat( ) ) );
        }
    }

    //Can't use really large floats as of calculation restrictions.
    float ShortenFloatForVectorLength( float pureFloat ) {
        return ( float ) ( ( Math.sqrt( pureFloat ) ) / 3.0f )
    }

    void LengthTestsWithFiveSigFig( float x, float y, float z ) {
        int SIGNIFICANT_FIGURES = 5
        LengthTests( x, y, z, SIGNIFICANT_FIGURES )
    }

    void LengthTestsWithSixSigFig( float x, float y, float z ) {
        int SIGNIFICANT_FIGURES = 6
        LengthTests( x, y, z, SIGNIFICANT_FIGURES )
    }

    void LengthTests( float x, float y, float z, int sifnificantFigures ) {
        Vector3f vector = new Vector3f( x, y, z )
        float expectedLength = ( float ) Math.sqrt( x * x + y * y + z * z )
        float actualLength = vector.length( )
        boolean testPassed = ApproaxEquals( expectedLength, actualLength, sifnificantFigures )
        assertTrue( testPassed )}

    boolean ApproaxEquals( float a, float b, int significantFigures ) {
        return ( Math.abs( a - b ) < Math.pow( 0.1, significantFigures )
                * Math.max( Math.abs( a ), Math.abs( b ) ) )
    }

    @Test
    void testMax() {
        Vector3f vector = new Vector3f( 2.0f, 3.0f, 4.0f );
        assertEquals( vector.max( ), 4.0f );
    }

    @Test
    void testDot() {
        Vector3f vectorToDot = new Vector3f( 2.0f, 3.0f, 4.0f );
        Vector3f vectorOther = new Vector3f( 2.0f, 2.0f, 3.0f );
        float dottedValue = vectorToDot.dot( vectorOther );
        float expectedValue = ( 2.0f * 2.0f ) + ( 3.0f * 2.0f ) + ( 4.0f * 3.0f );
        assertEquals( dottedValue, expectedValue );
    }

    @Test
    void testCross() {
    }

    @Test
    void testNormalized() {

    }

    @Test
    void testRotate() {

    }

    @Test
    void testRotate1() {

    }

    @Test
    void testLerp() {

    }

    @Test
    void testAdd() {

    }

    @Test
    void testAdd1() {

    }

    @Test
    void testSub() {

    }

    @Test
    void testSub1() {

    }

    @Test
    void testMul() {

    }

    @Test
    void testMul1() {

    }

    @Test
    void testDiv() {

    }

    @Test
    void testDiv1() {

    }

    @Test
    void testAbs() {

    }

    @Test
    void testToString() {

    }

    @Test
    void testGetXY() {

    }

    @Test
    void testGetYZ() {

    }

    @Test
    void testGetZX() {

    }

    @Test
    void testGetYX() {

    }

    @Test
    void testGetZY() {

    }

    @Test
    void testGetXZ() {

    }

    @Test
    void testSet() {

    }

    @Test
    void testSet1() {

    }

    @Test
    void testGetX() {

    }

    @Test
    void testSetX() {

    }

    @Test
    void testGetY() {

    }

    @Test
    void testSetY() {

    }

    @Test
    void testGetZ() {

    }

    @Test
    void testSetZ() {

    }

    @Test
    void testEquals() {

    }
}
