package org.gameEngine.game;

import org.gameEngine.engine.core.Time;
import org.gameEngine.engine.core.Window;
import org.gameEngine.engine.core.render.Util;

/**
 * Created by James Timms on 21/03/2014.
 */
public class StartGame {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final long FRAME_CAP = 60;
	protected static final String TITLE = "3D Engine";
	public Window window;
	protected boolean shouldRunGameLoop = false;
	protected Game game;
	protected double timeElapsed = 0.0d;
	long timeLastLoop;
	long timeThisLoop;

	protected StartGame( Window window ) {
		this.window = window;
		this.game = GameFactory.Build();
	}

	public static void main( String[] args ) {
		//Start UpdateInput Observer as separate thread.
		StartGame game = new StartGame(
				new Window( WIDTH, HEIGHT, TITLE ) );

		game.StartGame();
	}

	public void StartGame() {
		shouldRunGameLoop = true;
		InitializeGame();
		GameLoop();
	}

	private void InitializeGame() {
		System.out.println( Util.GetOpenGLVersion() );
//		Util.InitGraphics();
	}

	public void StopGame() {
		if( !shouldRunGameLoop ) {
			return;
		}
		shouldRunGameLoop = false;
	}

	protected void GameLoop() {
		timeLastLoop = Time.getTime();
		long timeLastFrame = Time.getTime();
		long timeThisFrame;

		long totalTime = 0l;
		int frameCount = 0;
		while( shouldRunGameLoop ) {
			if( ( totalTime += Time.perLoopDelta ) > Time.SECOND ) {
				System.out.println( "FPS: " + frameCount );
				totalTime = 0l;
				frameCount = 0;
			}

			if( IsReadyForFrame() ) {
				//Do frame.
				timeThisFrame = Time.getTime();
				Time.SetDeltaTime( timeThisFrame - timeLastFrame );
				timeLastFrame = timeThisFrame;

				frameCount++;
				ProcessFrame();
				RenderFrame();
			}
		}
		CleanUp();
	}

	protected boolean IsReadyForFrame() {
		timeThisLoop = Time.getTime();
		Time.perLoopDelta = timeThisLoop - timeLastLoop;
		timeLastLoop = timeThisLoop;
		long timeThingy = ( FRAME_CAP == 0 ) ? 0 : Time.SECOND / FRAME_CAP;
		boolean isReady = ( timeElapsed += Time.perLoopDelta ) > timeThingy;
		if( isReady ) {
			timeElapsed = 0;
		}
		return isReady;
	}

	protected void ProcessFrame() {
		if( window.IsCloseRequested() ) {
			StopGame();
		}
		game.UpdateInput();
		game.Update();
	}

	protected void RenderFrame() {
		game.Render();
		window.Render();
	}

	protected void CleanUp() {
		window.Dispose();
	}
}
