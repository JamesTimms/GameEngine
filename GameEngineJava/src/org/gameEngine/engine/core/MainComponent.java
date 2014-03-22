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
    public static final double FRAME_CAP = 5000.0f;

    private boolean isRunning = false;
    private Game game;

    public MainComponent( ) {

    }

    public void start( ) {
        game = new Game( );
        isRunning = true;
        run( );
    }

    public void stop( ) {
        if( !isRunning ) {
            return;
        }
        isRunning = false;
    }

    public void run( ) {

        final double frameTime = 1.0 / FRAME_CAP;
        int frames = 0;
        long frameCount = 0;

        long lastTime = Time.getTime( );
        double unprocessedTime = 0;

        while( isRunning ) {
            long startTime = Time.getTime( );
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double)Time.SECOND;
            frameCount += passedTime;

            while( unprocessedTime > frameTime ) {

                if( Window.isCloseRequested( ) ) {
                    stop( );
                }
                Time.setDelta( frameTime );

                render( );
                frames ++;

                if( frameCount >= Time.SECOND ) {
                    System.out.println( frames );
                    frames = 0;
                    frameCount = 0;
                }
            }
            unprocessedTime += passedTime / (double) Time.SECOND;
            try {
                Thread.sleep( 1 );
            } catch( InterruptedException exception ) {
                exception.printStackTrace( );
            }
        }

        cleanUp( );
    }

    public void render( ) {
        game.render( );
        Window.render( );
    }

    public void cleanUp( ) {
        Window.dispose( );
    }

    public static void main( String[] args ) {
        Window.createWindow( WIDTH, HEIGHT, TITLE);

        MainComponent game = new MainComponent( );
        game.start( );
    }
}
