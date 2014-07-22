package org.gameEngine.engine.core;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by TekMaTek on 22/07/2014.
 */
public class InputTest implements Observer {

	@Override
	public void update( Observable o, Object arg ) {
		if( arg instanceof Float ) {
			
		}
		System.out.println(
				( ( originalType ) ( ( ObserverArgs ) arg )
						.GetAndDiscardArg( InputObserver.KEY_PRESSED_EVENT ) ) );
	}
}
