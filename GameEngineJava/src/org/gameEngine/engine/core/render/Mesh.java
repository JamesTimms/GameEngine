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


	public Mesh( ) {
		vertexBO = glGenBuffers( );
		indexBO = glGenBuffers( );
		indicesLength = 0;
	}

	public void AddVertices( Vertex[] vertices, int[] indices ) {
		indicesLength = indices.length;
		glBindBuffer( GL_ARRAY_BUFFER, vertexBO );
		glBufferData( GL_ARRAY_BUFFER, Util.createFlippedBuffer( vertices ), GL_STATIC_DRAW );

		glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, indexBO );
		glBufferData( GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer( indices ), GL_STATIC_DRAW );
	}

	public void Draw( ) {
		glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
		glEnableVertexAttribArray( 0 );

		glBindBuffer( GL_ARRAY_BUFFER, vertexBO );
		glVertexAttribPointer( 0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0 );

		glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, indexBO );
		glDrawElements( GL_TRIANGLES, indicesLength, GL_UNSIGNED_INT, 0 );

		glDisableVertexAttribArray( 0 );
	}
}
