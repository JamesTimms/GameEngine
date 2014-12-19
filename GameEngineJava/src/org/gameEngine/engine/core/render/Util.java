package org.gameEngine.engine.core.render;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

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

}