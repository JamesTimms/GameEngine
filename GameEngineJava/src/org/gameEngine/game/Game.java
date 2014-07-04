package game;

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
		if( Input.GetKey( Keyboard.KEY_DOWN ) ) {
			System.out.println( "KEY_DOWN" );
		}
		if( Input.GetMouseDown( 2 ) ) {
			System.out.println( "1" );
		}
		if( Input.GetMouse( 1 ) ) {
			System.out.println( "2" );
		}
	}

	public void Update( ) {

	}

	public void Render( ) {

	}
}
