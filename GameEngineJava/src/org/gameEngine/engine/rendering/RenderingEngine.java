package org.gameEngine.engine.rendering;

import org.gameEngine.engine.core.GameObject;
import org.gameEngine.engine.physics.maths.Vector3f;
import org.gameEngine.engine.rendering.lighting.BaseLight;
import org.gameEngine.engine.rendering.lighting.DirectionalLight;
import org.gameEngine.engine.rendering.shaders.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by TekMaTek on 01/02/2015.
 */
public class RenderingEngine {

	public static Vector3f ambientLight = new Vector3f( 0.2f, 0.2f, 0.2f );
	public static DirectionalLight directionalLight = DirectionalLight.BuildDirectionalLight(
			BaseLight.GreenLight( ), new Vector3f( 0.7f, 0.7f, 0.7f ) );

	public RenderingEngine( ) {
		System.out.println( RenderingUtil.GetOpenGLVersion( ) );
		glClearColor( 0.0f, 0.0f, 0.0f, 0.0f );

		glFrontFace( GL_CW );
		glCullFace( GL_BACK );
		glEnable( GL_CULL_FACE );
		glEnable( GL_DEPTH_TEST );

		glEnable( GL_TEXTURE_2D );
	}

	protected static void clearScreen( ) {
		glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
	}

	public void render( GameObject someRootGO ) {
		clearScreen( );
		RenderingUtil.setClearColor( new Vector3f( 0.0f, 0.0f, 0.0f ) );
		Shader forwardAmbient = new ForwardAmbient( );
		Shader forwardDirectional = new ForwardDirectional( );
		Shader forwardPoint = new ForwardPoint( );
		Shader forwardSpot = new ForwardSpot( );

		someRootGO.render( forwardAmbient );
		glEnable( GL_BLEND );
		glBlendFunc( GL_ONE, GL_ONE );
		glDepthMask( false );
		glDepthFunc( GL_EQUAL );

		someRootGO.render( forwardDirectional );
		someRootGO.render( forwardPoint );
		someRootGO.render( forwardSpot );

		glDepthFunc( GL_LESS );
		glDepthMask( true );
		glDisable( GL_BLEND );
	}

}
