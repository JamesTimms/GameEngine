package org.gameEngine.game;

import org.gameEngine.engine.core.Time;
import org.gameEngine.engine.core.Window;

/**
 * Created by James Timms on 21/03/2014.
 */
public class StartGame {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final double FRAME_CAP = 120.0f;
	protected static final String TITLE = "3D Engine";
	public Time time;
	public Window window;
	protected boolean shouldRunGameLoop = false;
	protected Game game;
	protected double timeElapsed = 0.0d;

	protected StartGame( Time time, Window window ) {
		this.time = time;
		this.window = window;
		this.game = GameFactory.Build( );
	}

	public static void main( String[] args ) {
		//Start UpdateInput Observer as separate thread.
		StartGame game = new StartGame(
				new Time( ),
				new Window( WIDTH, HEIGHT, TITLE ) );

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
		long timeLastFrame = time.getTime( );
		long timeThisFrame;

		while( shouldRunGameLoop ) {

			timeThisFrame = time.getTime( );
			time.SetDeltaTime( timeThisFrame - timeLastFrame );
			timeLastFrame = timeThisFrame;

			if( IsReadyForFrame( ) ) {
				ProcessFrame( );
				RenderFrame( );
			}
		}
		CleanUp( );
	}

	protected boolean IsReadyForFrame( ) {
		boolean isReady = ( timeElapsed -= time.GetDeltaTime( ) ) < 0.0d;
		if( isReady ) {
			timeElapsed = ( FRAME_CAP / Time.SECOND );
		}
		return isReady;
	}

	protected void ProcessFrame( ) {
		if( window.IsCloseRequested( ) ) {
			StopGame( );
		}
		game.UpdateInput( );
	}

	protected void RenderFrame( ) {
		game.Render( );
		window.Render( );
	}

	protected void CleanUp( ) {
		window.Dispose( );
	}
}