package org.gameEngine;

import org.gameEngine.engine.core.CoreEngine;
import org.gameEngine.engine.core.Window;

/**
 * Created by James Timms on 21/03/2014.
 */
public class StartGame {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	protected static final String TITLE = "3D Engine";

	public static void main( String[] args ) {
		CoreEngine ignition = CoreEngine.BuildCore(
				new Window( WIDTH, HEIGHT, TITLE ),
				new Game( ) );
		ignition.StartGame();
	}

}
