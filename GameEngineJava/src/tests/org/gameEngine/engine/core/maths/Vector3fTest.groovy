package org.gameEngine.engine.core.maths

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import testUtil.TestUtil

import static testUtil.TestUtil.ApproaxEquals
import static testUtil.TestUtil.ApproaxEquals
import static testUtil.TestUtil.GetRandomShortFloat;

/**
 * Created by TekMaTek on 05/08/2014.
 */

@RunWith( JUnit4 )
class Vector3fTest extends GroovyTestCase {

    @Test
    void testLength() {
        Object tests = new Object( ) {
            public void TestLengths( int significantFigures ) {
                int TESTS_TO_RUN = 1000;
                for( int testsRun = 0; testsRun < TESTS_TO_RUN; testsRun++ ) {
                    LengthTests(
                            GetRandomShortFloat( ),
                            GetRandomShortFloat( ),
                            GetRandomShortFloat( ),
                            significantFigures );
                }
            }
            void LengthTests( float x, float y, float z, int significantFigures ) {
                Vector3f vector = new Vector3f( x, y, z )
                float expectedLength = ( float ) Math.sqrt( x * x + y * y + z * z )//No need to explicitly cast x,
                // y and z here to doubles to do this calculation for large numbers.
                float actualLength = vector.length( )
                boolean testPassed = ApproaxEquals( expectedLength, actualLength, significantFigures )
                assertTrue( testPassed )
            }
        }
        int SIGNIFICANT_FIGURES = 5
        int SIGNIFICANT_FIGURES2 = 6
        tests.TestLengths( SIGNIFICANT_FIGURES );
        tests.TestLengths( SIGNIFICANT_FIGURES2 );
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
        Object tests = new Object( ) {
            private void TestCrossProduct( Vector3f vector, Vector3f vectorToCross, int significantFigures ) {
                Vector3f actual = vector.cross( vectorToCross );
                Vector3f expected = TestCrossProduct( vector, vectorToCross );
                boolean testPassed = ApproaxEquals( expected, actual, significantFigures )
                if( !testPassed ) {
                    System.out.println( "input1: " + vector + " \n input2: " + vectorToCross );
                    System.out.println( "expected: " + expected + " \n actual: " + actual );
                }
                assertTrue( testPassed )
            }
            private Vector3f TestCrossProduct( Vector3f vector1, Vector3f vector2 ) {
                float x_ = vector1.getY( ) * vector2.getZ( ) - vector1.getZ( ) * vector2.getY( );
                float y_ = vector1.getZ( ) * vector2.getX( ) - vector1.getX( ) * vector2.getZ( );
                float z_ = vector1.getX( ) * vector2.getY( ) - vector1.getY( ) * vector2.getX( );
                return new Vector3f( x_, y_, z_ );
            }
            public void RandomTestForCrossProduct( int numberOfRuns, int significantFigures ) {
                for( int i = 0; i < numberOfRuns; i++ ) {
                    Vector3f vector1 = new Vector3f(
                            GetRandomShortFloat( ),
                            GetRandomShortFloat( ),
                            GetRandomShortFloat( ) );
                    Vector3f vector2 = new Vector3f(
                            GetRandomShortFloat( ),
                            GetRandomShortFloat( ),
                            GetRandomShortFloat( ) );
                    TestCrossProduct( vector1, vector2,significantFigures );
                }
            }
            public void ConcreteTest1( ) {
                Vector3f vector = new Vector3f( 1.0f, 2.0f, 3.0f );
                Vector3f vectorToCross = new Vector3f( 3.0f, 2.0f, 1.0f );
                Vector3f actual = vector.cross( vectorToCross );
                Vector3f expected = new Vector3f( -4.0f, 8.0f, -4.0f );
                assertEquals( expected, actual );
            }
            public void ConcreteTest2( ) {
                Vector3f vector = new Vector3f( -2.0f, 3.0f, 4.0f );
                Vector3f vectorToCross = new Vector3f( 2.0f, -2.0f, 3.0f );
                Vector3f actual = vector.cross( vectorToCross );
            }
            public void ConcreteTest3( ) {
                Vector3f vector = new Vector3f( 1.0f, 0.0f, 0.0f );
                Vector3f vectorToCross = new Vector3f( 0.0f, 1.0f, 0.0f );
                Vector3f actual = vector.cross( vectorToCross );
                Vector3f expected = new Vector3f( 0.0f, 0.0f, 1.0f );
                assertEquals( expected, actual );
            }
            public void MaxFloatSize( ) {
                Vector3f vector =
                        new Vector3f( TestUtil.ROOT_MAX_FLOAT, TestUtil.ROOT_MAX_FLOAT, TestUtil.ROOT_MAX_FLOAT );
                Vector3f vectorToCross = new Vector3f( 4.0f, 0.0f, 2.0f );
                Vector3f actual = vector.cross( vectorToCross );
                Vector3f expected = new Vector3f( 3.7e19f, 3.7e19f, -7.4e19f );
                boolean testPassed = ApproaxEquals( expected, actual, 1 );//Can't have sigfig anymore than 1 as large
                // floats are infamously inaccurate.
                assertTrue( testPassed )
            }
            public void MinFloatSize( ) {
                Vector3f vector =
                        new Vector3f( TestUtil.ROOT_MIN_FLOAT, TestUtil.ROOT_MIN_FLOAT, TestUtil.ROOT_MIN_FLOAT );
                Vector3f vectorToCross = new Vector3f( 4.0f, 0.0f, 2.0f );
                Vector3f actual = vector.cross( vectorToCross );
                Vector3f expected = new Vector3f( 7.5e-23f, 7.5e-23f, -1.5e-22f );
                boolean testPassed = ApproaxEquals( expected, actual, 1 );//Can't have sigfig anymore than 1 as large
                // floats are infamously inaccuracy.
                assertTrue( testPassed )
            }
        }
        tests.ConcreteTest1( )
        tests.ConcreteTest2( );
        tests.ConcreteTest3( );
        final int NUMBER_OF_TESTS = 1000;
        final int SIGNIFICANT_FIGURES = 2;
        final int SIGNIFICANT_FIGURES2 = 3;//Large floats have lower accuracy.
        tests.RandomTestForCrossProduct( NUMBER_OF_TESTS, SIGNIFICANT_FIGURES );
        tests.RandomTestForCrossProduct( NUMBER_OF_TESTS, SIGNIFICANT_FIGURES2 );
        tests.MaxFloatSize( );
        tests.MinFloatSize( );
    }

    @Test
    void testNormalized() {
        final int SIGNIFICANT_FIGURES = 5;
        Vector3f actual = ( new Vector3f( 1.0f, 2.0f, 3.0f ) ).normalized( );
        Vector3f expected = new Vector3f( 0.26726f, 0.53452f, 0.80178f );
        boolean testPassed = ApproaxEquals( expected, actual, SIGNIFICANT_FIGURES );
        assertTrue( testPassed );
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

    @Test
    void testZero( ) {
        Vector3f zeroVector = Vector3f.Zero( );
        Vector3f expected = new Vector3f( 0.0f, 0.0f ,0.0f );
        assertEquals( expected, zeroVector );
    }

    @Test
    void testOne( ) {
        Vector3f oneVector = Vector3f.One( );
        Vector3f expected = new Vector3f( 1.0f, 1.0f ,1.0f );
        assertEquals( expected, oneVector );
    }
}
