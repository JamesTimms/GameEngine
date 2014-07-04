package org.gameEngine.engine.core;

/**
 * Created by James Timms on 21/03/2014.
 */
public class MainComponent {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final double FRAME_CAP = 120.0f;
	private static final String TITLE = "3D Engine";
	private boolean isRunning = false;
	private Game game;

	public MainComponent( ) {

	}
	public static void main( String[] args ) {
		Window.createWindow( WIDTH, HEIGHT, TITLE );

		MainComponent game = new MainComponent( );
		game.start( );
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

		final double frameTime = ( 1.0f / ( FRAME_CAP ) );
		long lastTime = Time.getTime( );
		float timeElapsed = 0.0f;
		int frameCount = 0;
		float secondCounter = 0.0f;

		while( isRunning ) {

			long newTime = Time.getTime( );
			Time.setDeltaTime( newTime - lastTime );
			lastTime = newTime;
			timeElapsed += Time.getDeltaTime( );

			while( timeElapsed > ( frameTime * Time.SECOND ) ) {
				frameCount++;
				secondCounter += timeElapsed;
				timeElapsed = 0.0f;
				if( secondCounter > Time.SECOND ) {
					System.out.println( "FPS: " + frameCount );
					frameCount = 0;
					secondCounter = 0.0f;
				}


				if( Window.isCloseRequested( ) ) {
					stop( );
				}
				game.Input( );
				render( );
			}
		}

		cleanUp( );
	}
	public void render( ) {
		game.Render( );
		Window.render( );
	}
	public void cleanUp( ) {
		Window.dispose( );
	}
}
