package org.gameEngine.engine.core.input;

import org.lwjgl.input.Mouse;

/**
 * Created by TekMaTek on 28/07/2014.
 */
public class MouseWrapper {

	public boolean IsMouseDown( int mouseButton ) {
		return Mouse.isButtonDown( mouseButton );
	}

	public int GetMouseSize( ) {
		return Mouse.EVENT_SIZE;
	}

}
