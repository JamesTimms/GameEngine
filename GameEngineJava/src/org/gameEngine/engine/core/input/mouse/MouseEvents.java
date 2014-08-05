package org.gameEngine.engine.core.input.mouse;

import java.util.ArrayList;

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
