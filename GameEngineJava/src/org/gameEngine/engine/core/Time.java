package org.gameEngine.engine.core;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Time {

    public static final long SECOND = 100000000L;

    private static double delta;

    public static long getTime( ) {
        return System.nanoTime( );
    }

    public static double getDeltaTime( ) {
        return delta;
    }

    public static void setDelta( double delta ) {
        Time.delta = delta;
    }
}
