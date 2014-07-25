package org.gameEngine.game;

import org.gameEngine.engine.core.input.KeyboardObserver;
import org.gameEngine.engine.core.ObserverArgs;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Game implements Observer {

	private KeyboardObserver input;

	public Game( KeyboardObserver input ) {
		this.input = input;
		input.addObserver( this );
	}

	public void UpdateInput( ) {
		input.CheckForEvents( );//TODO: give own thread at start of game.
	}

	public void Update( ) {

	}

	public void Render( ) {

	}

	@Override
	public void update( Observable o, Object arg ) {
		try {
			ObserverArgs args = ( ObserverArgs ) arg;
			System.out.println( args.GetAndDiscardArg( KeyboardObserver.KEY_PRESSED_EVENT ).toString( ) );
			System.out.println( args.GetAndDiscardArg( KeyboardObserver.KEY_HELD_EVENT ).toString( ) );
			System.out.println( args.GetAndDiscardArg( KeyboardObserver.KEY_RELEASED_EVENT ).toString( ) );
		} catch( Exception ex ) {
			System.out.println( ex.getMessage( ) );
		}
	}
}
