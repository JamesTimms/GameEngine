package org.gameEngine.engine.core;

import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by TekMaTek on 17/07/2014.
 */
public class InputObserver extends Observable {

	public static final String KEY_HELD_EVENT = "KeyHeld";
	public static final String KEY_PRESSED_EVENT = "KeyPressed";
	public static final String KEY_RELEASED_EVENT = "KeyReleased";

	protected static ArrayList< Integer > keysHeld = new ArrayList< Integer >( );
	protected static ArrayList< Integer > keysPressed = new ArrayList< Integer >( );
	protected static ArrayList< Integer > keysReleased = new ArrayList< Integer >( );
	protected static ArrayList< Integer > injectedKeys = new ArrayList< Integer >( );

	protected static int numOfKeyboardEvents;

	public InputObserver( ) {
		numOfKeyboardEvents = Keyboard.KEYBOARD_SIZE;
	}

	public void ObserveKeyboardActions( ) {
		RegisterKeyboardActions( );
		for( int keyNumber = 0; keyNumber < numOfKeyboardEvents; keyNumber ++) {
			if( IsKeyPressed( keyNumber ) ) {
				setChanged();
				notifyObservers( ObserverArgs.CreateArgs( KEY_PRESSED_EVENT, keyNumber ) );
				continue;
			}
			if( IsKeyHeld( keyNumber ) ) {
				setChanged();
				notifyObservers( ObserverArgs.CreateArgs( KEY_HELD_EVENT, keyNumber ) );
				continue;
			}
			if( IsKeyReleased( keyNumber ) ) {
				setChanged();
				notifyObservers( ObserverArgs.CreateArgs( KEY_RELEASED_EVENT, keyNumber ));
			}
		}
	}

	protected static void RegisterKeyboardActions( ) {
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
		injectedKeys.clear( );
	}

	protected static boolean IsKeyHeld( int keyNumber ) {
		return keysPressed.contains( keyNumber );
	}

	protected static boolean IsKeyPressed( int keyNumber ) {
		return Keyboard.isKeyDown( keyNumber ) || injectedKeys.contains( keyNumber );
	}

	protected static boolean IsKeyReleased( int keyNumber ) {
		return keysReleased.contains( keyNumber );
	}

	public static void InjectKeyPress( int keyCode ) {
		injectedKeys.add( keyCode );
	}
}
