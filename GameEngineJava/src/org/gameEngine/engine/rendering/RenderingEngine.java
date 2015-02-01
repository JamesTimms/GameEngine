package org.gameEngine.engine.rendering;

import org.gameEngine.engine.core.Camera;
import org.gameEngine.engine.core.GameObject;
import org.gameEngine.engine.physics.maths.Vector3f;
import org.gameEngine.engine.rendering.shaders.BasicShader;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by TekMaTek on 01/02/2015.
 */
public class RenderingEngine {

	public static Camera camera;

	public RenderingEngine( ) {
		System.out.println( RenderingUtil.GetOpenGLVersion( ) );
		glClearColor( 0.0f, 0.0f, 0.0f, 0.0f );

		glFrontFace( GL_CW );
		glCullFace( GL_BACK );
		glEnable( GL_CULL_FACE );
		glEnable( GL_DEPTH_TEST );

		glEnable( GL_TEXTURE_2D );
	}

	public void render( GameObject someRootGO ) {
		clearScreen( );
		RenderingUtil.setClearColor( new Vector3f( 0.0f, 0.0f, 0.0f ) );
		someRootGO.render( new BasicShader( ) );
	}

	protected static void clearScreen( ) {
		glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
	}

}
