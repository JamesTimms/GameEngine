package org.gameEngine.engine.core.input.keyboard;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

/**
 * Created by TekMaTek on 25/07/2014.
 * <p/>
 * LWJGL implements many of the methods in Keyboard as static.
 * To try and make the engine more testable this class
 * wraps the Static methods with non statics methods. This should
 * allow for more dependency injection for input classes and tests.
 */
public class KeyboardWrapper {
	//TODO: FINISH IMPLEMENTING WRAPPER.

	public KeyboardWrapper( ) {
		try {
			if( Keyboard.isCreated( ) ) {
				Keyboard.destroy( );
			}
			Keyboard.create( );
		} catch( LWJGLException ex ) {
			ex.printStackTrace( );
		} catch( IllegalStateException ex ) {
			ex.printStackTrace( );
			System.out.println( "Need to set up lwjgl window first." );
		}
	}

	public int GetKeyboardSize( ) {
		return Keyboard.KEYBOARD_SIZE;
	}

	public boolean IsKeyDown( int keyNumber ) {
		//Could add an inject key press function here?
		return Keyboard.isKeyDown( keyNumber );
	}

}
