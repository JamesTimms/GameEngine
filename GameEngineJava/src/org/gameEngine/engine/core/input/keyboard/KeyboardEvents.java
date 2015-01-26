package org.gameEngine.engine.core.input.keyboard;

import java.util.ArrayList;

/**
 * Created by TekMaTek on 25/07/2014.
 */
public class KeyboardEvents {

	public int numberOfEvents;

	protected ArrayList< Integer > keysHeld = new ArrayList< Integer >( );
	protected ArrayList< Integer > keysPressed = new ArrayList< Integer >( );
	protected ArrayList< Integer > keysReleased = new ArrayList< Integer >( );
	protected KeyboardWrapper keyboard;

	public KeyboardEvents( KeyboardWrapper keyboard ) {
		this.keyboard = keyboard;
		numberOfEvents = keyboard.GetKeyboardSize( );
	}

	/**
	 * This method should be run in an update loop to work properly.
	 */
	public void LookForKeyboardActions( ) {
		keysReleased.clear( );
		for( int i = 0; i < numberOfEvents; i++ ) {
			if( !IsKeyPressed( i ) && keysHeld.contains( i ) ) {
				keysReleased.add( i );
			}
		}

		keysPressed.clear( );
		for( int i = 0; i < numberOfEvents; i++ ) {
			if( keyboard.IsKeyDown( i ) && !keysHeld.contains( i ) ) {
				keysPressed.add( i );
			}
		}

		keysHeld.clear( );
		for( int i = 0; i < numberOfEvents; i++ ) {
			if( IsKeyPressed( i ) ) {
				keysHeld.add( i );
			}
		}
	}

	protected boolean IsKeyHeld( int keyNumber ) {
		return keysHeld.contains( keyNumber );
	}

	protected boolean IsKeyPressed( int keyNumber ) {
		return keysPressed.contains( keyNumber );
	}

	protected boolean IsKeyReleased( int keyNumber ) {
		return keysReleased.contains( keyNumber );
	}

}
