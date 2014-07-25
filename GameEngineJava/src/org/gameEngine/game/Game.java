package org.gameEngine.game;

import org.gameEngine.engine.core.InputObserver;
import org.gameEngine.engine.core.KeyboardWrapper;
import org.gameEngine.engine.core.ObserverArgs;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Game implements Observer {

	private InputObserver input;

	public Game( ) {
		input = new InputObserver( new KeyboardWrapper( ) );
		input.addObserver( this );
	}

	public void UpdateInput( ) {
		input.ObserveKeyboardActions( );//TODO: give own thread at start of game.
	}

	public void Update( ) {

	}

	public void Render( ) {

	}

	@Override
	public void update( Observable o, Object arg ) {
		try {
			ObserverArgs args = ( ObserverArgs ) arg;
			System.out.println( args.GetAndDiscardArg( InputObserver.KEY_PRESSED_EVENT ).toString( ) );
			System.out.println( args.GetAndDiscardArg( InputObserver.KEY_HELD_EVENT ).toString( ) );
			System.out.println( args.GetAndDiscardArg( InputObserver.KEY_RELEASED_EVENT ).toString( ) );
		} catch( Exception ex ) {
			System.out.println( ex.getMessage() );
		}
	}
}
