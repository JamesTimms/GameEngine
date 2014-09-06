package org.gameEngine.engine.core.input.keyboard;

import org.gameEngine.engine.core.ObserverArgs;
import org.gameEngine.engine.core.input.GameInput;

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
		for( Integer keyNumber = 0; keyNumber < keyboardEvents.numberOfEvents; keyNumber++ ) {
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
				//keyNumber.NotifyObservers( );
				notifyObservers( ObserverArgs.CreateArgs( KEY_PRESSED_EVENT, keyNumber.toString( ) ) );
			}
			else if( keyboardEvents.IsKeyHeld( keyNumber ) ) {
				setChanged( );
				notifyObservers( ObserverArgs.CreateArgs( KEY_HELD_EVENT, keyNumber.toString( ) ) );
			}
			else if( keyboardEvents.IsKeyReleased( keyNumber ) ) {
				setChanged( );
				notifyObservers( ObserverArgs.CreateArgs( KEY_RELEASED_EVENT, keyNumber.toString( ) ) );
			}
		}
	}

}
