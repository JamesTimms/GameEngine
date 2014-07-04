package org.gameEngine.engine.core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

/**
 * Created by TekMaTek on 04/07/2014.
 */
public class Input {

	public static final int NUM_KEYCODES = 256;
	public static final int NUM_MOUSE_BUTTONS = 5;

	private static ArrayList< Integer > currentKeys = new ArrayList< Integer >( );
	private static ArrayList< Integer > downKeys = new ArrayList< Integer >( );
	private static ArrayList< Integer > upKeys = new ArrayList< Integer >( );
	private static ArrayList< Integer > injectedKeys = new ArrayList< Integer >( );

	private static ArrayList< Integer > currentMouse = new ArrayList< Integer >( );
	private static ArrayList< Integer > downMouse = new ArrayList< Integer >( );
	private static ArrayList< Integer > upMouse = new ArrayList< Integer >( );
	private static ArrayList< Integer > injectedMouse = new ArrayList< Integer >( );

	public static void Update( ) {
		ProcessKeyInput( );
		ProcessMouseInput( );
	}

	static void ProcessKeyInput( ) {
		upKeys.clear( );
		for( int i = 0; i < NUM_KEYCODES; i++ ) {
			if( !GetKey( i ) && currentKeys.contains( i ) ) {
				upKeys.add( i );
			}
		}

		downKeys.clear( );
		for( int i = 0; i < NUM_KEYCODES; i++ ) {
			if( GetKey( i ) && !currentKeys.contains( i ) ) {
				downKeys.add( i );
			}
		}

		currentKeys.clear( );
		for( int i = 0; i < NUM_KEYCODES; i++ ) {
			if( GetKey( i ) ) {
				currentKeys.add( i );
			}
		}
	}

	static void ProcessMouseInput( ) {
		upMouse.clear( );
		for( int i = 0; i < NUM_MOUSE_BUTTONS; i++ ) {
			if( !GetMouse( i ) && currentMouse.contains( i ) ) {
				upMouse.add( i );
			}
		}

		downMouse.clear( );
		for( int i = 0; i < NUM_MOUSE_BUTTONS; i++ ) {
			if( GetMouse( i ) && !currentMouse.contains( i ) ) {
				downMouse.add( i );
			}
		}

		currentMouse.clear( );
		for( int i = 0; i < NUM_MOUSE_BUTTONS; i++ ) {
			if( GetMouse( i ) ) {
				currentMouse.add( i );
			}
		}
	}

	public static boolean GetKey( int keyCode ) {
		return Keyboard.isKeyDown( keyCode ) || injectedKeys.contains( keyCode );
	}

	public static boolean GetKeyDown( int keyCode ) {
		return downKeys.contains( keyCode );
	}

	public static boolean GetKeyUp( int keyCode ) {
		return upKeys.contains( keyCode );
	}

	public static boolean GetMouse( int mouseButton ) {
		return Mouse.isButtonDown( mouseButton ) || injectedMouse.contains( mouseButton );
	}

	public static boolean GetMouseDown( int mouseButton ) {
		return downMouse.contains( mouseButton );
	}

	public static boolean GetMouseUp( int mouseButton ) {
		return upMouse.contains( mouseButton );
	}

	public static void InjectKeyPress( int keyCode ) {
		injectedKeys.clear( );
		injectedKeys.add( keyCode );
	}

	public static void InjectMousePress( int mouseCode ) {
		injectedMouse.clear( );
		injectedMouse.add( mouseCode );
	}
}
