package org.gameEngine.engine.core;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by TekMaTek on 17/07/2014.
 */
public class InputObserver extends Observable {

	public static final String KEY_HELD_EVENT = "KeyHeld";
	public static final String KEY_PRESSED_EVENT = "KeyPressed";
	public static final String KEY_RELEASED_EVENT = "KeyReleased";

	protected static int numOfKeyboardEvents;
	protected ArrayList< Integer > keysHeld = new ArrayList< Integer >( );
	protected ArrayList< Integer > keysPressed = new ArrayList< Integer >( );
	protected ArrayList< Integer > keysReleased = new ArrayList< Integer >( );
	protected KeyboardWrapper keyboard;

	public InputObserver( KeyboardWrapper keyboard ) {
		this.keyboard = keyboard;
		numOfKeyboardEvents = keyboard.GetKeyboardSize( );
	}

	public void ObserveKeyboardActions( ) {
		RegisterKeyboardActions( );
		for( int keyNumber = 0; keyNumber < numOfKeyboardEvents; keyNumber++ ) {
			/* TODO: Setup events so that a button press and a button held event
			 * are separate subscribe-able objects. E.G
			 * foreach( EventType event in List< EventType > ) {
			 * 		if( event.ShouldBeTriggered( ) ) {
			 * 			event.Trigger( );
			 * 		}
			 * }
			 */
			if( IsKeyPressed( keyNumber ) ) {
				setChanged( );
				notifyObservers( ObserverArgs.CreateArgs( KEY_PRESSED_EVENT, keyNumber ) );
			}
			else if( IsKeyHeld( keyNumber ) ) {
				setChanged( );
				notifyObservers( ObserverArgs.CreateArgs( KEY_HELD_EVENT, keyNumber ) );
			}
			else if( IsKeyReleased( keyNumber ) ) {
				setChanged( );
				notifyObservers( ObserverArgs.CreateArgs( KEY_RELEASED_EVENT, keyNumber ) );
			}
		}
	}

	protected void RegisterKeyboardActions( ) {
		keysReleased.clear( );
		for( int i = 0; i < numOfKeyboardEvents; i++ ) {
			if( !IsKeyPressed( i ) && keysHeld.contains( i ) ) {
				keysReleased.add( i );
			}
		}

		keysPressed.clear( );
		for( int i = 0; i < numOfKeyboardEvents; i++ ) {
			if( IsKeyPressed( i ) && !keysHeld.contains( i ) ) {
				keysPressed.add( i );
			}
		}

		keysHeld.clear( );
		for( int i = 0; i < numOfKeyboardEvents; i++ ) {
			if( IsKeyPressed( i ) ) {
				keysHeld.add( i );
			}
		}
	}

	protected boolean IsKeyHeld( int keyNumber ) {
		return keysPressed.contains( keyNumber );
	}

	protected boolean IsKeyPressed( int keyNumber ) {
		return keyboard.IsKeyDown( keyNumber );
	}

	protected boolean IsKeyReleased( int keyNumber ) {
		return keysReleased.contains( keyNumber );
	}
}
