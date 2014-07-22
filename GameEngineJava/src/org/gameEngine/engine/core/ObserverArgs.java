package org.gameEngine.engine.core;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by TekMaTek on 22/07/2014.
 */
public class ObserverArgs< T > {

	private Dictionary< String, T > argLookup = new Hashtable< String, T >(  );

	public ObserverArgs( ) {

	}

	public static ObserverArgs CreateArgs( String argID, Object argValue ) {
		ObserverArgs< Object > newArgs = new ObserverArgs( );
		newArgs.AddArg( argID, argValue );
		return newArgs;
	}

//	newArgs static ObserverArgs CreateArgs( String argID, String argValue ) {
//		ObserverArgs newArgs = new ObserverArgs( );
//		newArgs.AddArg( argID, argValue );
//		return newArgs;
//	}
//
//	public static ObserverArgs CreateArgs( String argID, float argValue ) {
//		ObserverArgs newArgs = new ObserverArgs( );
//		newArgs.AddArg( argID, argValue );
//		return newArgs;
//	}
//
//	public static ObserverArgs CreateArgs( String argID, int argValue ) {
//		ObserverArgs newArgs = new ObserverArgs( );
//		newArgs.AddArg( argID, argValue );
//		return newArgs;
//	}

	public void AddArg( String argID, T argValue ) {
		argLookup.put( argID, T );
	}

	public T GetArg( String argID ) {
		return argLookup.get( argID );
	}

	public T GetAndDiscardArg( String argID ) {
		return argLookup.remove( argID );
	}

}
