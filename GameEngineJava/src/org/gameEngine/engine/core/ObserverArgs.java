package org.gameEngine.engine.core;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by TekMaTek on 22/07/2014.
 * The {@link ObserverArgs} Class is used together with the {@link Observer} and {@link ObserverSubject} Classes.
 * You can use {@link ObserverArgs} for passing a single {@link ObserverArgs#eventMessage} as a string and cast it to
 * int or other primitive. You can also use {@link ObserverArgs#eventID} to specify different events for the same
 * observer observerSubject pair.
 * For more advanced parameters you can use the {@link ObserverArgs#AddArg(String, Object)} to add any object the
 * drawback is having to cast the object when you {@link ObserverArgs#GetArg(String)}.
 */
public class ObserverArgs {

	public String eventID;
	public String eventMessage;//Used for single quick event message.
	private Dictionary< String, Object > arguments = new Hashtable< String, Object >( );//Used for more complex data.

	protected ObserverArgs( ) {
	}//Here to prevent ObserverArgs from being creating improperly.

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
			arguments = new Hashtable< String, Object >( );
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
