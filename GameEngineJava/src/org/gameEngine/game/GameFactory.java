package org.gameEngine.game;

import org.gameEngine.engine.core.input.KeyboardObserver;
import org.gameEngine.engine.core.input.InputFactory;

/**
 * Created by TekMaTek on 25/07/2014.
 */
public class GameFactory {

	public static Game Build( ) {
		KeyboardObserver keyboard = InputFactory.BuildKeyboard( );
		Game newGame = new Game( keyboard );
		return newGame;
	}

}
