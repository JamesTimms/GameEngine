package org.gameEngine.engine.core;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Window {

    public static void createWindow( int width, int height, String title ) {
        Display.setTitle( title );
        try {
            Display.setDisplayMode( new DisplayMode( width, height ) );
            Display.create( );
        } catch( LWJGLException exception ) {
            exception.printStackTrace();
        }
    }

    public static void render( ) {
        Display.update( );
    }

    public static boolean isCloseRequested( ) {
        return Display.isCloseRequested( );
    }

    public static int getWidth( ) {
        return  Display.getDisplayMode( ).getWidth( );
    }

    public static int getHeight( ) {
        return  Display.getDisplayMode( ).getHeight( );
    }

    public static String getTitle( ) {
        return Display.getTitle( );
    }
}
