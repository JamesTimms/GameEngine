package org.gameEngine.engine.core;

import org.lwjgl.input.Keyboard;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Game {

	public Game( ) {

	}

	public void Input( ) {
		Input.Update( );
		if( Input.GetKeyDown( Keyboard.KEY_UP ) ) {
			System.out.println( "KEY_UP" );
		}
	}

	public void Update( ) {

	}

	public void Render( ) {

	}
}
