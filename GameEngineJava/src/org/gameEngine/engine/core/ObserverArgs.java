package org.gameEngine.engine.core;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by TekMaTek on 22/07/2014.
 */
public class ObserverArgs {

	private Dictionary< String, Object > arguments = new Hashtable< String, Object >(  );

	public ObserverArgs( ) {

	}

	public static ObserverArgs CreateArgs( String argID, Object argValue ) {

		ObserverArgs newArgs = new ObserverArgs( );
		newArgs.AddArg( argID, argValue );
		return newArgs;
	}

	public Class< ? > FindTypeOf( String argID ) {
		return arguments.get( argID ).getClass( );

	}

	public void AddArg( String argID, Object argValue ) {
		arguments.put( argID, argValue );
	}

	public Object GetArg( String argID ) {
		return arguments.get( argID );
	}

	public Object GetAndDiscardArg( String argID ) {
		return arguments.remove( argID );
	}

}
