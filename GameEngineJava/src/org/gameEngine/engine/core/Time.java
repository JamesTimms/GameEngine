package org.gameEngine.engine.core;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Time {

	public static final long SECOND = 1000000000L;
	public static long perLoopDelta;
	protected static long delta;

	public static long getTime( ) {
		return System.nanoTime( );
	}

	public static long GetDeltaTime( ) {
		return delta;
	}

	public static void SetDeltaTime( long delta ) {
		Time.delta = delta;
	}
}
