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

	public static void main( String[] args ) {

		//Start UpdateInput Observer as separate thread.
		Window.createWindow( WIDTH, HEIGHT, TITLE );

		MainComponent game = new MainComponent( );
		game.StartGame( );
	}

	public void StartGame( ) {
		shouldRunGameLoop = true;
		GameLoop( );
	}

	public void StopGame( ) {
		if( !shouldRunGameLoop ) {
			return;
		}
		shouldRunGameLoop = false;
	}

	protected void GameLoop( ) {

		long timeLastFrame = Time.getTime( );
		long timeThisFrame;

		while( shouldRunGameLoop ) {

			timeThisFrame = Time.getTime( );
			Time.SetDeltaTime( timeThisFrame - timeLastFrame );
			timeLastFrame = timeThisFrame;

			if( IsReadyForFrame( ) ) {
				ProcessFrame( );
				RenderFrame( );
			}
		}
		CleanUp( );
	}

	double timeElapsed = 0.0d;
	protected boolean IsReadyForFrame() {
		boolean isReady = ( timeElapsed -= Time.GetDeltaTime() ) < 0.0d;
		if( isReady ) {
			timeElapsed = ( FRAME_CAP / Time.SECOND );
		}
		return isReady;
	}

	protected void ProcessFrame( ) {
		if( Window.isCloseRequested( ) ) {
			StopGame( );
		}
		game.UpdateInput( );
	}

	protected void RenderFrame( ) {
		game.Render( );
		Window.render( );
	}

	protected void CleanUp( ) {
		Window.dispose( );
	}
}
