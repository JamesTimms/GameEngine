package org.gameEngine.engine.core.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;


/**
 * Created by TekMaTek on 27/10/2014.
 */
public class Mesh {

	private int vertexBO;
	private int indexBO;
	private int indicesLength;
	private static final int SIZE_OF_BYTE = 4;

	public Mesh( ) {
		vertexBO = glGenBuffers( );
		indexBO = glGenBuffers( );
		indicesLength = 0;
	}

	public void addVertices( Vertex[] vertices, int[] indices ) {
		indicesLength = indices.length;
		glBindBuffer( GL_ARRAY_BUFFER, vertexBO );
		glBufferData( GL_ARRAY_BUFFER, Util.createFlippedBuffer( vertices ), GL_STATIC_DRAW );

		glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, indexBO );
		glBufferData( GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer( indices ), GL_STATIC_DRAW );
	}

	public void draw( ) {
		Util.clearScreen( );
		glEnableVertexAttribArray( 0 );
		glEnableVertexAttribArray( 1 );

		glBindBuffer( GL_ARRAY_BUFFER, vertexBO );
		glVertexAttribPointer( 0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0 );
		glVertexAttribPointer( 1, 2, GL_FLOAT, false, Vertex.SIZE * 4, SIZE_OF_BYTE * 3 );

		glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, indexBO );
		glDrawElements( GL_TRIANGLES, indicesLength, GL_UNSIGNED_INT, 0 );

		glDisableVertexAttribArray( 0 );
		glDisableVertexAttribArray( 1 );
	}
}
