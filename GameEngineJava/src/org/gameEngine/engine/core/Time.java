package org.gameEngine.engine.core;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Time {

	public static final long SECOND = 1000000000L;
	public static long perLoopDelta;
	protected static long delta;
	protected static double timeElapsed = 0.0d;
	static long timeLastLoop;
	static long timeThisLoop;
	static long timeLastFrame = Time.getTime( );
	static long timeThisFrame;

	public static long getTime( ) {
		return System.nanoTime( );
	}

	public static long GetDeltaTime( ) {
		return delta;
	}

	public static void SetDeltaTime( long delta ) {
		Time.delta = delta;
	}

	protected static boolean IsReadyForFrame( ) {
		timeThisLoop = getTime( );
		perLoopDelta = timeThisLoop - timeLastLoop;
		timeLastLoop = timeThisLoop;
		long frameThrottle = ( Core.FRAME_CAP == 0 ) ? 0 : SECOND / Core.FRAME_CAP;
		boolean isReady = ( timeElapsed += perLoopDelta ) > frameThrottle;
		if( isReady ) {
			timeElapsed = 0;
		}
		return isReady;
	}

	public static void UpdateDeltaTime( ) {
		timeThisFrame = Time.getTime( );
		Time.SetDeltaTime( timeThisFrame - timeLastFrame );
		timeLastFrame = timeThisFrame;

	}

	public static void init( ) {
		Time.timeLastLoop = Time.getTime( );
	}
}
