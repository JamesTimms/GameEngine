package org.gameEngine.engine.core;

/**
 * Created by James Timms on 21/03/2014.
 */
public class MainComponent {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final String TITLE = "3D Engine";
    public static final double FRAME_CAP = 120.0f;

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

        final double frameTime = ( 1.0d / ( FRAME_CAP ) );
        long lastTime = Time.getTime( );
        long timeElapsed = 0L;
        int frameCount = 0;
        double secondCounter = 0;

        while( isRunning ) {

            long newTime = Time.getTime( );
            Time.setDelta( newTime - lastTime );
            lastTime = newTime;
            timeElapsed += Time.getDeltaTime( );

            while( timeElapsed > ( frameTime * Time.SECOND ) ) {
                frameCount++;
                secondCounter += timeElapsed;
                timeElapsed = 0L;
                if( secondCounter > Time.SECOND ) {
                    System.out.println( "FPS: " + frameCount );
                    frameCount = 0;
                    secondCounter = 0.0d;
                }


                if( Window.isCloseRequested( ) ) {
                    stop( );
                }

                render( );
            }

//            try {
//                Thread.sleep( 1 );
//            } catch( InterruptedException exception ) {
//                exception.printStackTrace( );
//            }
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
