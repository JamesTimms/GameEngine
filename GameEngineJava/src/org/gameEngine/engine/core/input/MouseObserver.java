package org.gameEngine.engine.core.input;

import org.gameEngine.engine.core.ObserverArgs;

import java.util.ArrayList;
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

	/**
	 * Created by TekMaTek on 28/07/2014.
	 */
	public class MouseEvents {
		public int numberOfEvents;
	
		protected ArrayList< Integer > mouseHeld = new ArrayList< Integer >( );
		protected ArrayList< Integer > mousePressed = new ArrayList< Integer >( );
		protected ArrayList< Integer > mouseReleased = new ArrayList< Integer >( );
		protected MouseWrapper mouse;

		public MouseEvents( MouseWrapper mouse ) {
			this.mouse = mouse;
			numberOfEvents = mouse.GetMouseSize( );
		}

		/**
		 * This method should be run in an update loop to work properly.
		 */
		public void LookForKeyboardActions( ) {
			mouseReleased.clear( );
			for( int i = 0; i < numberOfEvents; i++ ) {
				if( !IsMousePressed( i ) && mouseHeld.contains( i ) ) {
					mouseReleased.add( i );
				}
			}

			mousePressed.clear( );
			for( int i = 0; i < numberOfEvents; i++ ) {
				if( mouse.IsMouseDown( i ) && !mouseHeld.contains( i ) ) {
					mousePressed.add( i );
				}
			}

			mouseHeld.clear( );
			for( int i = 0; i < numberOfEvents; i++ ) {
				if( IsMousePressed( i ) ) {
					mouseHeld.add( i );
				}
			}
		}

		protected boolean IsMouseHeld( int keyNumber ) {
			return mouseHeld.contains( keyNumber );
		}

		protected boolean IsMousePressed( int keyNumber ) {
			return mousePressed.contains( keyNumber );
		}

		protected boolean IsMouseReleased( int keyNumber ) {
			return mouseReleased.contains( keyNumber );
		}

	}
}
