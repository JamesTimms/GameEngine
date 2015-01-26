package org.gameEngine.engine.core.render;

import org.gameEngine.engine.core.maths.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_SRGB;

/**
 * Created by TekMaTek on 27/10/2014.
 */
public class Util {

	protected static FloatBuffer createFloatBuffer( int size ) {
		return BufferUtils.createFloatBuffer( size );
	}

	protected static IntBuffer createIntBuffer( int size ) {
		return BufferUtils.createIntBuffer( size );
	}

	public static FloatBuffer createFlippedBuffer( Vertex[] verticies ) {
		FloatBuffer buffer = createFloatBuffer( verticies.length * Vertex.SIZE );

		for( int i = 0; i < verticies.length; i++ ) {
			buffer.put( verticies[ i ].getPosition( ).getX( ) );
			buffer.put( verticies[ i ].getPosition( ).getY( ) );
			buffer.put( verticies[ i ].getPosition( ).getZ( ) );
		}

		buffer.flip( );
		return buffer;
	}

	public static IntBuffer createFlippedBuffer( int... values ) {
		IntBuffer buffer = createIntBuffer( values.length );
		buffer.put( values );
		buffer.flip();
		return buffer;
	}

	public static void ClearScreen( ) {
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

	public static FloatBuffer createFlippedBuffer( Matrix4f matrix ) {
		FloatBuffer buffer = createFloatBuffer( 4 * 4 );

		for( int i = 0; i < 4; i++ ) {
			for( int j = 0; j < 4; j++ ) {
				buffer.put( matrix.get( i, j ) );
			}
		}
		buffer.flip( );
		return buffer;
	}

	public static String GetOpenGLVersion( ) {
		return "OpenGL Version: " + glGetString( GL_VERSION );
	}

}