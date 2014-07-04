package org.gameEngine.engine.core;

import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

/**
 * Created by TekMaTek on 04/07/2014.
 */
public class Input {

	public static final int NUM_KEYCODES = 256;

	private static ArrayList< Integer > currentKeys = new ArrayList< Integer >( );
	private static ArrayList< Integer > downKeys = new ArrayList< Integer >( );
	private static ArrayList< Integer > upKeys = new ArrayList< Integer >( );

	public static void Update( ) {
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

	public static boolean GetKey( int keyCode ) {
		return Keyboard.isKeyDown( keyCode );
	}

	public static boolean GetKeyDown( int keyCode ) {
		return downKeys.contains( keyCode );
	}

}
