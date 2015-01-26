package org.gameEngine.game;

import org.gameEngine.engine.core.input.InputFactory;
import org.gameEngine.engine.core.input.Inputs;

/**
 * Created by TekMaTek on 25/07/2014.
 */
public class GameFactory {

	public static Game Build( ) {
		Inputs inputs = InputFactory.BuildInputContainer( );
		inputs.AddInput( InputFactory.BuildKeyboard( ) );
		inputs.AddInput( InputFactory.BuildMouse( ) );
		Game newGame = new Game( inputs );
		return newGame;
	}

}
