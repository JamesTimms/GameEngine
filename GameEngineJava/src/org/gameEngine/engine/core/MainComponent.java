package org.gameEngine.engine.core;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by James Timms on 21/03/2014.
 */
public class MainComponent {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "3D Engine";

    public MainComponent( ) {

    }

    public void start( ) {
        run( );
    }

    public void stop( ) {

    }

    public void run( ) {
        while( !Window.isCloseRequested( ) ) {
            render( );
        }
    }

    public void render( ) {
        Window.render( );
    }

    public void cleanUp( ) {

    }

    public static void main( String[] args ) {
        Window.createWindow( WIDTH, HEIGHT, TITLE);

        MainComponent game = new MainComponent( );
        game.start( );
    }
}
