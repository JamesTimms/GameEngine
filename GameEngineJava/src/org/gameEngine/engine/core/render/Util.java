package org.gameEngine.engine.core.render;

import org.gameEngine.engine.core.maths.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

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
			buffer.put( verticies[ i ].getTexCoord( ).getX( ) );
			buffer.put( verticies[ i ].getTexCoord( ).getY( ) );
		}

		buffer.flip( );
		return buffer;
	}

	public static IntBuffer createFlippedBuffer( int... values ) {
		IntBuffer buffer = createIntBuffer( values.length );
		buffer.put( values );
		buffer.flip( );
		return buffer;
	}

	public static void clearScreen( ) {
		glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
	}

	public static void setTexture( boolean enabled ) {
		if( enabled ) {
			glEnable( GL_TEXTURE_2D );
		} else {
			glDisable( GL_TEXTURE_2D );
		}
	}

	public static void initGraphics( ) {
		glClearColor( 0.0f, 0.0f, 0.0f, 0.0f );

		glFrontFace( GL_CW );
		glCullFace( GL_BACK );
		glEnable( GL_CULL_FACE );
		glEnable( GL_DEPTH_TEST );

		//TODO: Depth clamp for later
		glEnable( GL_TEXTURE_2D );
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

	public static String[] removeEmptyString( String[] tokens ) {
		ArrayList< String > result = new ArrayList< String >( );

		for( int i = 0; i < tokens.length; i++ ) {
			if( !tokens[ i ].equals( "" ) )
				result.add( tokens[ i ] );
		}

		String[] res = new String[ result.size( ) ];
		result.toArray( res );
		return res;
	}

	public static int[] toIntArray( Integer[] indices ) {
		int[] result = new int[ indices.length ];
		for( int i = 0; i < indices.length; i++ ) {
			result[ i ] = indices[ i ].intValue( );
		}
		return result;
	}
}