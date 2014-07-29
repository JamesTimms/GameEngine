package org.gameEngine.engine.core.input;

import org.gameEngine.engine.core.ObserverArgs;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by TekMaTek on 17/07/2014.
 */
public class KeyboardObserver extends Observable implements GameInput {

	public static final String KEY_HELD_EVENT = "KeyHeld";
	public static final String KEY_PRESSED_EVENT = "KeyPressed";
	public static final String KEY_RELEASED_EVENT = "KeyReleased";

	protected KeyboardEvents keyboardEvents;

	public KeyboardObserver( KeyboardEvents keyboardEvents ) {
		this.keyboardEvents = keyboardEvents;
	}

	public void CheckForEvents( ) {
		keyboardEvents.LookForKeyboardActions( );
		for( int keyNumber = 0; keyNumber < keyboardEvents.numberOfEvents; keyNumber++ ) {
			/* TODO: Setup events so that a button press and a button held event
			 * are separate subscribe-able objects. E.G
			 * foreach( EventType event in List< EventType > ) {
			 * 		if( event.ShouldBeTriggered( ) ) {
			 * 			event.Trigger( );
			 * 		}
			 * }
			 */
			//event.ShouldBeTriggered( )
			if( keyboardEvents.IsKeyPressed( keyNumber ) ) {
				setChanged( );
				//keyPressedEvent.Trigger( );
				notifyObservers( ObserverArgs.CreateArgs( KEY_PRESSED_EVENT, keyNumber ) );
			}
			else if( keyboardEvents.IsKeyHeld( keyNumber ) ) {
				setChanged( );
				notifyObservers( ObserverArgs.CreateArgs( KEY_HELD_EVENT, keyNumber ) );
			}
			else if( keyboardEvents.IsKeyReleased( keyNumber ) ) {
				setChanged( );
				notifyObservers( ObserverArgs.CreateArgs( KEY_RELEASED_EVENT, keyNumber ) );
			}
		}
	}

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
}
