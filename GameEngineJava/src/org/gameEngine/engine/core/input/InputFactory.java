package org.gameEngine.engine.core.input;

/**
 * Created by TekMaTek on 25/07/2014.
 */
public class InputFactory {

	public static KeyboardObserver BuildKeyboard( ) {
		KeyboardWrapper keyboard = new KeyboardWrapper( );
		KeyboardObserver.KeyboardEvents events = new KeyboardObserver.KeyboardEvents( keyboard );
		KeyboardObserver newKeyboard = new KeyboardObserver( events );
		return newKeyboard;
	}

	public static MouseObserver BuildMouse( ) {
		MouseWrapper mouse = new MouseWrapper( );
		MouseObserver.MouseEvents events = new MouseObserver.MouseEvents( mouse );
		MouseObserver newMouse = new MouseObserver( events );
		return newMouse;
	}

}
