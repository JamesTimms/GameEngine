package org.gameEngine;

import org.gameEngine.engine.core.components.Camera;
import org.gameEngine.engine.core.GameObject;
import org.junit.Test;

public class GameTest {

	GameObject newTestObject;

	@Test
	public void testInit( ) throws Exception {//TODO: add these kinds of tests to a log file?
		newTestObject = new GameObject( );
		newTestObject.addComponent( new Camera( ) );
		System.out.println( runX( 1 ) );//Java optimises for loops so that running the code only once has a much
		// higher cost than the runs after 1. 12000 for first run and 800 for ever run after for example.
		System.out.println( runX( 1000 ) );
	}

	public long runX( int cycles ) {
		long totalTotal = 0l;
		long startTime;
		long timeThisLoop;
		for( int i = 0; i < cycles; i++ ) {
			startTime = System.nanoTime( );
			newTestObject.getComponent( Camera.class );
			timeThisLoop = ( System.nanoTime( ) - startTime );
			totalTotal = totalTotal + timeThisLoop;
		}
		return totalTotal / cycles;
	}
}