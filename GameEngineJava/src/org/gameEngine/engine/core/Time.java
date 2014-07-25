package org.gameEngine.engine.core;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Time {

	public static final long SECOND = 1000000000L;

	protected long delta;

	public long getTime( ) {
		return System.nanoTime( );
	}

	public long GetDeltaTime() {
		return delta;
	}

	public void SetDeltaTime( long delta ) {
		this.delta = delta;
	}
}
