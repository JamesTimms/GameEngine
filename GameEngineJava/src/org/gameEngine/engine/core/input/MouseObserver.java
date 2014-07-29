package org.gameEngine.engine.core.input;

import org.gameEngine.engine.core.ObserverArgs;

import java.util.Observable;

/**
 * Created by TekMaTek on 28/07/2014.
 */
public class MouseObserver extends Observable implements GameInput {

	public static final String MOUSE_CLICKED = "MouseClick";
	public static final String MOUSE_HELD = "MouseHeld";
	public static final String MOUSE_RELEASED = "MouseRelease";

	protected MouseEvents mouseEvents;

	public MouseObserver( MouseEvents mouseEvents ) {
		this.mouseEvents = mouseEvents;
	}

	public void CheckForEvents( ) {
		mouseEvents.LookForKeyboardActions( );
		for( int mouseNumber = 0; mouseNumber < mouseEvents.numberOfEvents; mouseNumber++ ) {
			if( mouseEvents.IsMousePressed( mouseNumber ) ) {
				setChanged( );
				notifyObservers( ObserverArgs.CreateArgs( MOUSE_CLICKED, mouseNumber ) );
			}
			else if( mouseEvents.IsMouseHeld( mouseNumber ) ) {
				setChanged( );
				notifyObservers( ObserverArgs.CreateArgs( MOUSE_HELD, mouseNumber ) );
			}
			else if( mouseEvents.IsMouseReleased( mouseNumber ) ) {
				setChanged( );
				notifyObservers( ObserverArgs.CreateArgs( MOUSE_RELEASED, mouseNumber ) );
			}
		}
	}

}
