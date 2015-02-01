package org.gameEngine.engine.core;

import org.gameEngine.Game;
import org.gameEngine.engine.rendering.RenderingEngine;
import org.gameEngine.engine.rendering.RenderingUtil;

/**
 * Created by TekMaTek on 01/02/2015.
 */
public class CoreEngine {

	public static final long FRAME_CAP = 60;

	public static int frameCount = 0;
	public Window window;
	protected boolean shouldRunGameLoop = false;
	protected Game game;
	protected RenderingEngine renderingEngine;

	private CoreEngine( ) {
	}

	public static CoreEngine BuildCore( Window window, Game game ) {
		CoreEngine newCoreEngine = new CoreEngine( );
		newCoreEngine.window = window;
		newCoreEngine.game = game;
		newCoreEngine.renderingEngine = new RenderingEngine( );
		return newCoreEngine;
	}

	public void StartGame( ) {
		initRenderingEngine( );
		game.init( );
		shouldRunGameLoop = true;
		GameLoop( );
	}

	private void initRenderingEngine( ) {
		System.out.println( RenderingUtil.GetOpenGLVersion( ) );
		RenderingUtil.initGraphics( );
	}

	public void StopGame( ) {
		shouldRunGameLoop = false;
	}

	protected void GameLoop( ) {
		Time.init( );
		long elapsedFrameTime = 0l;
		while( shouldRunGameLoop ) {
			if( ( elapsedFrameTime += Time.perLoopDelta ) > Time.SECOND ) {
				System.out.println( "FPS: " + frameCount );
				elapsedFrameTime = 0l;
				frameCount = 0;
			}

			if( Time.IsReadyForFrame( ) ) {
				//Do frame.
				Time.UpdateDeltaTime( );
				frameCount++;
				ProcessFrame( );
				RenderFrame( );
			}
		}
		CleanUp( );
	}

	protected void ProcessFrame( ) {
		if( window.IsCloseRequested( ) ) {
			StopGame( );
		}
		game.UpdateInput( );
		game.Update( );
	}

	protected void RenderFrame( ) {
		renderingEngine.render( GameObject.getRoot( ) );
		window.Render( );
	}

	protected void CleanUp( ) {
		window.Dispose( );
	}
}
