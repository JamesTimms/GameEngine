package org.gameEngine.engine.core;

import org.gameEngine.game.Game;

/**
 * Created by James Timms on 21/03/2014.
 */
public class MainComponent {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final double FRAME_CAP = 120.0f;
	protected static final String TITLE = "3D Engine";
	protected boolean shouldRunGameLoop = false;
	protected Game game = new Game( );

	protected MainComponent( ) {

	}

	protected static void main( String[] args ) {

		//Start Input Observer as separate thread.

		Window.createWindow( WIDTH, HEIGHT, TITLE );

		MainComponent game = new MainComponent( );
		game.StartGame( );
	}

	protected void StartGame( ) {
		shouldRunGameLoop = true;
		ProcessFrame( );
	}

	protected void StopGame( ) {
		if( !shouldRunGameLoop ) {
			return;
		}
		shouldRunGameLoop = false;
	}

	protected void ProcessFrame( ) {

		final double frameTime = ( 1.0f / ( FRAME_CAP ) );
		long timeLastFrame = Time.getTime( );
		float timeElapsed = 0.0f;
		float secondCounter = 0.0f;

		while( shouldRunGameLoop ) {

			long timeThisFrame = Time.getTime( );
			Time.setDeltaTime( timeThisFrame - timeLastFrame );
			timeLastFrame = timeThisFrame;
			timeElapsed += Time.getDeltaTime( );

			while( timeElapsed > ( frameTime * Time.SECOND ) ) {
				secondCounter += timeElapsed;
				timeElapsed = 0.0f;
				if( secondCounter > Time.SECOND ) {
					secondCounter = 0.0f;
				}
				CodeRunPerFrame( );
			}
		}
		CleanUp( );
	}

	protected void CodeRunPerFrame( ) {
		if( Window.isCloseRequested( ) ) {
			StopGame( );
		}
		game.Input( );
		RenderFrame( );
	}

	protected void RenderFrame( ) {
		game.Render( );
		Window.render( );
	}

	protected void CleanUp( ) {
		Window.dispose( );
	}
}
