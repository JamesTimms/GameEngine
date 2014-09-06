package org.gameEngine.engine.core;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by TekMaTek on 22/07/2014.
 */
public class ObserverArgs {

	public String eventID;
	public String eventMessage;//Used for single quick event message.
	private Dictionary< String, Object > arguments = new Hashtable< String, Object >(  );//Used for more complex data.

	protected ObserverArgs( ) { }//Here to prevent ObserverArgs from being creating improperly.

	public static ObserverArgs CreateArgs( String eventID ) {
		ObserverArgs newArgs = new ObserverArgs( );
		newArgs.eventID = eventID;
		return newArgs;
	}

	public static ObserverArgs CreateArgs( String eventID, String newEventMessage ) {
		ObserverArgs newArgs = new ObserverArgs( );
		newArgs.eventMessage = newEventMessage;
		newArgs.eventID = eventID;
		return newArgs;
	}

	public ObserverArgs AddArg( String argID, Object argValue ) {
		if( arguments == null ) {
			arguments  = new Hashtable< String, Object >( );
		}
		arguments.put( argID, argValue );
		return this;
	}

	public Object GetArg( String argID ) {
		return arguments.get( argID );
	}

	public Object GetAndDiscardArg( String argID ) {
		return arguments.remove( argID );
	}

}
