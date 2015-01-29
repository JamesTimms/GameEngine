package org.gameEngine.engine.core.render;

import org.gameEngine.engine.core.maths.Vector3f;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by TekMaTek on 19/12/2014.
 */
public class ResourceLoader {

	public static String LoadShader( String filename ) {
		StringBuilder shaderSource = new StringBuilder( );
		BufferedReader shaderReader = null;
		try {
			shaderReader = new BufferedReader( new FileReader( "./resource/shaders/" + filename ) );
			String line;
			while( ( line = shaderReader.readLine( ) ) != null ) {
				shaderSource.append( line ).append( "\n" );
			}
			shaderReader.close( );
		} catch( Exception exception ) {
			exception.printStackTrace( );
			System.exit( 1 );
		}

		return shaderSource.toString( );
	}

	public static Texture loadTexture( String filename ) {
		String[] splitArray = filename.split( "\\." );
		String ext = splitArray[ splitArray.length - 1 ];

		try {
			int id = TextureLoader.getTexture( ext, new FileInputStream( "./resource/textures/" + filename ) )
								  .getTextureID( );
			return Texture.BuildTexture( id );
		} catch( Exception ex ) {
			ex.printStackTrace( );
			System.exit( 1 );
		}

		return null;
	}

	public static Mesh loadMesh( String filename ) {
		String[] splitArray = filename.split( "\\." );
		String ext = splitArray[ splitArray.length - 1 ];

		if( !ext.equals( "obj" ) ) {
			System.err.println( "Error: File formate not supported for mesh data: " + ext );
			new Exception( ).printStackTrace( );
			System.exit( 1 );
		}

		ArrayList< Vertex > vertices = new ArrayList< Vertex >( );
		ArrayList< Integer > indices = new ArrayList< Integer >( );

		BufferedReader meshReader = null;
		try {
			meshReader = new BufferedReader( new FileReader( "./resource/models/" + filename ) );
			String line;
			while( ( line = meshReader.readLine( ) ) != null ) {
				String[] tokens = line.split( " " );
				tokens = Util.removeEmptyString( tokens );
				if( tokens.length == 0 || tokens[ 0 ].equals( '#' ) ) {
					continue;
				} else if( tokens[ 0 ].equals( "v" ) ) {
					vertices.add( new Vertex( new Vector3f(
							Float.valueOf( tokens[ 1 ] ),
							Float.valueOf( tokens[ 2 ] ),
							Float.valueOf( tokens[ 3 ] ) ) ) );
				} else if( tokens[ 0 ].equals( "f" ) ) {
					indices.add( Integer.parseInt( tokens[ 1 ].split( "/" )[ 0 ] ) - 1 );
					indices.add( Integer.parseInt( tokens[ 2 ].split( "/" )[ 0 ] ) - 1 );
					indices.add( Integer.parseInt( tokens[ 3 ].split( "/" )[ 0 ] ) - 1 );
					if( tokens.length > 4 ) {
						indices.add( Integer.parseInt( tokens[ 1 ].split( "/" )[ 0 ] ) - 1 );
						indices.add( Integer.parseInt( tokens[ 3 ].split( "/" )[ 0 ] ) - 1 );
						indices.add( Integer.parseInt( tokens[ 4 ].split( "/" )[ 0 ] ) - 1 );
					}
				}
			}
			meshReader.close( );
		} catch( Exception exception ) {
			exception.printStackTrace( );
			System.exit( 1 );
		}

		Mesh res = new Mesh( );
		Vertex[] vertexData = new Vertex[ vertices.size( ) ];
		vertices.toArray( vertexData );

		Integer[] indexData = new Integer[ indices.size( ) ];
		indices.toArray( indexData );

		res.addVertices( vertexData, Util.toIntArray( indexData ) );

		return res;
	}
}