package org.gameEngine.engine.core;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Time {

    public static final long SECOND = 1000000000L;

    private static long delta;

    public static long getTime( ) {
        return System.nanoTime( );
    }

    public static long getDeltaTime( ) {
        return delta;
    }

    public static void setDelta( long delta ) {
        Time.delta = delta;
    }
}
