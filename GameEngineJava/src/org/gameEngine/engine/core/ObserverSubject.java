package org.gameEngine.engine.core;

import java.util.ArrayList;

/**
 * Created by TekMaTek on 06/09/2014.
 */
public class ObserverSubject {

	private ArrayList< Observer > observers = new ArrayList< Observer >( );

	public synchronized void AddObserver( Observer newObserver ) {
		if( newObserver == null ) {
			throw new NullPointerException( );
		}
		if( !observers.contains( newObserver ) ) {
			observers.add( newObserver );
		}
	}

	public synchronized void DeleteObserver( Observer observer ) {
		observers.remove( observer );
	}

	public void NotifyObservers( ) {
		NotifyObservers( null );
	}

	public void NotifyObservers( ObserverArgs arg ) {
		for( int i = observers.size( ) - 1; i >= 0; i-- ) {
			observers.get( i ).Update( this, arg );
		}
	}

	public synchronized void DeleteObservers( ) {
		observers.clear( );
	}

	public synchronized int CountObservers( ) {
		return observers.size( );
	}

}
