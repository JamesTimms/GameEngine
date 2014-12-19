package org.gameEngine.engine.core.render;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;


/**
 * Created by TekMaTek on 27/10/2014.
 */
public class Mesh {

	private int vbo;
	private int size;


	public Mesh( ) {
		vbo = glGenBuffers( );
		size = 0;
	}

	public void AddVertices( Vertex[ ] vertices ) {
		size = vertices.length * Vertex.SIZE;
		glBindBuffer( GL_ARRAY_BUFFER, vbo );
		glBufferData( GL_ARRAY_BUFFER, Util.CreateFlippedBuffer( vertices ), GL_STATIC_DRAW );

	}

	public void Draw( ) {
		glEnableVertexAttribArray( 0 );

		glBindBuffer( GL_ARRAY_BUFFER, vbo );
		glVertexAttribPointer( 0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0 );
		glDrawArrays( GL_TRIANGLES, 0, size );

		glDisableVertexAttribArray( 0 );
	}
}
