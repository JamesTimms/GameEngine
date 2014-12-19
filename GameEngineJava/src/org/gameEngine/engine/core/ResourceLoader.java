package org.gameEngine.engine.core;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by TekMaTek on 19/12/2014.
 */
public class ResourceLoader {

	public static String LoadShader( String filename ) {
		StringBuilder shaderSource = new StringBuilder();
		BufferedReader shaderReader = null;
		try {
			shaderReader = new BufferedReader( new FileReader( "./resource/shaders/" + filename ) );
			String line;
			while( ( line = shaderReader.readLine() ) != null ) {
				shaderSource.append( line ).append( "\n" );
			}

		} catch( Exception exception ) {
			exception.printStackTrace();
			System.exit( 1 );
		}

		return shaderSource.toString();
	}

}
