package org.gameEngine.game;

import org.gameEngine.engine.core.input.KeyboardObserver;
import org.gameEngine.engine.core.input.InputFactory;
import org.gameEngine.engine.core.input.MouseObserver;

/**
 * Created by TekMaTek on 25/07/2014.
 */
public class GameFactory {

	public static Game Build( ) {
		KeyboardObserver keyboard = InputFactory.BuildKeyboard( );
		MouseObserver mouse = InputFactory.BuildMouse( );
		Game newGame = new Game( keyboard );
		return newGame;
	}

}
