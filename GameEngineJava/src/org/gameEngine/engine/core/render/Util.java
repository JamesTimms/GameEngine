package org.gameEngine.engine.core.render;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Created by TekMaTek on 27/10/2014.
 */
public class Util {

	protected static FloatBuffer CrateFloatBuffer( int size ) {
		return BufferUtils.createFloatBuffer( size );
	}

	public static FloatBuffer CreateFlippedBuffer( Vertex[ ] verticies ) {
		FloatBuffer buffer = CrateFloatBuffer( verticies.length * Vertex.SIZE );

		for( int i = 0; i < verticies.length; i ++ ) {
			buffer.put( verticies[ i ].getPosition( ).getX( ) );
			buffer.put( verticies[ i ].getPosition( ).getY( ) );
			buffer.put( verticies[ i ].getPosition( ).getZ( ) );
		}

		buffer.flip( );
		return buffer;
	}

	public static void ClearScreen() {
		glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
	}

	public static void InitGraphics( ) {
		glClearColor( 0.0f, 0.0f, 0.0f, 0.0f );

		glFrontFace( GL_CW );
		glCullFace( GL_BACK );
		glEnable( GL_CULL_FACE );
		glEnable( GL_DEPTH_TEST );

		//TODO: Depth clamp for later

		glEnable( GL_FRAMEBUFFER_SRGB );
	}

}