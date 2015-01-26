package org.gameEngine.engine.core;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Window {

	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	private static final String DEFAULT_TITLE = "DEFAULT_WINDOW";

	public Window( ) {
		CreateWindow( DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_TITLE );
	}

	public Window( int width, int height, String title ) {
		CreateWindow( width, height, title );
	}

	private void CreateWindow( int width, int height, String title ) {
		Display.setTitle( title );
		try {
			Display.setDisplayMode( new DisplayMode( width, height ) );
			Display.create( );
		} catch( LWJGLException ex ) {
			ex.printStackTrace( );
		}
	}

	public void Render( ) {
		Display.update( );
	}

	public void Dispose( ) {
		Display.destroy( );
	}

	public boolean IsCloseRequested( ) {
		return Display.isCloseRequested( );
	}

	public int GetWidth( ) {
		return Display.getDisplayMode( ).getWidth( );
	}

	public int GetHeight( ) {
		return Display.getDisplayMode( ).getHeight( );
	}

	public String GetTitle( ) {
		return Display.getTitle( );
	}

	public boolean IsCreated( ) {
		return Display.isCreated( );
	}

}
