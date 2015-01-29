package org.gameEngine.engine.core.render;

import org.gameEngine.engine.core.maths.Vector3f;

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

	public void addVertices( Vertex[] vertices, int[] indices ) {
		addVertices( vertices, indices, false );
	}

	public void addVertices( Vertex[] vertices, int[] indices, boolean shouldCalcNormals ) {
		if( shouldCalcNormals ) {
			calcNormals( vertices, indices );
		}
		indicesLength = indices.length;
		glBindBuffer( GL_ARRAY_BUFFER, vertexBO );
		glBufferData( GL_ARRAY_BUFFER, Util.createFlippedBuffer( vertices ), GL_STATIC_DRAW );

		glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, indexBO );
		glBufferData( GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer( indices ), GL_STATIC_DRAW );
	}

	public void draw( ) {
		Util.clearScreen( );
		final int POSITION = 0;
		final int TEXTURE_COORDS = 1;
		final int NORMALS = 2;
		final int SIZE_OF_BYTE = 4;
		glEnableVertexAttribArray( POSITION );
		glEnableVertexAttribArray( TEXTURE_COORDS );
		glEnableVertexAttribArray( NORMALS );

		glBindBuffer( GL_ARRAY_BUFFER, vertexBO );
		glVertexAttribPointer( POSITION, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0 );
		glVertexAttribPointer( TEXTURE_COORDS, 2, GL_FLOAT, false, Vertex.SIZE * 4, SIZE_OF_BYTE * 3 );
		glVertexAttribPointer( NORMALS, 3, GL_FLOAT, false, Vertex.SIZE * 4, SIZE_OF_BYTE * 5 );

		glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, indexBO );
		glDrawElements( GL_TRIANGLES, indicesLength, GL_UNSIGNED_INT, 0 );

		glDisableVertexAttribArray( POSITION );
		glDisableVertexAttribArray( TEXTURE_COORDS );
		glDisableVertexAttribArray( NORMALS );
	}

	private void calcNormals( Vertex[] vertices, int[] indices ) {
		for( int i = 0; i < indices.length; i += 3 ) {
			int i0 = indices[ i ];
			int i1 = indices[ i + 1 ];
			int i2 = indices[ i + 2 ];

			Vector3f v1 = vertices[ i1 ].getPosition( ).sub( vertices[ i0 ].getPosition( ) );
			Vector3f v2 = vertices[ i2 ].getPosition( ).sub( vertices[ i0 ].getPosition( ) );

			Vector3f normal = v1.cross( v2 ).normalized( );

			vertices[ i0 ].setNormal( vertices[ i0 ].getNormal( ).add( normal ) );
			vertices[ i1 ].setNormal( vertices[ i0 ].getNormal( ).add( normal ) );
			vertices[ i2 ].setNormal( vertices[ i0 ].getNormal( ).add( normal ) );
		}

		for( int i = 0; i < vertices.length; i++ ) {
			vertices[ i ].setNormal( vertices[ i ].getNormal( ).normalized( ) );
		}
	}
}
