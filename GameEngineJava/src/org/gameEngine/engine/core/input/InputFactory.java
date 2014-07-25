package org.gameEngine.engine.core.input;

/**
 * Created by TekMaTek on 25/07/2014.
 */
public class InputFactory {

	public static KeyboardObserver BuildKeyboard( ) {
		KeyboardWrapper keyboard = new KeyboardWrapper( );
		KeyboardEvents events = new KeyboardEvents( keyboard );
		KeyboardObserver newKeyboard = new KeyboardObserver( events );
		return newKeyboard;
	}

}
