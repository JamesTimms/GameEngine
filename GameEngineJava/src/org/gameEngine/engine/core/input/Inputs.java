package org.gameEngine.engine.core.input;

import java.util.ArrayList;

/**
 * Created by TekMaTek on 28/07/2014.
 */
public class Inputs {

	protected ArrayList< GameInput > inputs = new ArrayList< GameInput >( );

	public Inputs( ) {
	}

	public Inputs( GameInput... gameInputs ) {
		for( GameInput input : gameInputs ) {
			inputs.add( input );
		}
	}

	public void ProcessInputs( ) {
		
	}

	public void AddInput( GameInput input ) {
		inputs.add( input );
	}

	public boolean RemoveInput( GameInput input ) {
		return inputs.remove( input );
	}

	public void RemoveAllInputs( ) {
		inputs.clear( );
	}

}
