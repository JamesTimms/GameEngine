package org.gameEngine.engine.rendering;

import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by TekMaTek on 26/01/2015.
 */
public class Texture {

	public int id;

	private Texture( ) {
	}

	public static Texture BuildTexture( int id ) {
		Texture newTexture = new Texture( );
		newTexture.id = id;

//		glBindTexture(GL_TEXTURE_2D, id );
//
//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
//
//		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
//		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

		return newTexture;
	}

	public static Texture NoTexture( ) {
		return BuildTexture( 0 );
	}

	public static Texture loadTexture( String filename ) {
		String[] splitArray = filename.split( "\\." );
		String ext = splitArray[ splitArray.length - 1 ];

		try {
			int id = TextureLoader.getTexture( ext, new FileInputStream( "./resource/textures/" + filename ) )
								  .getTextureID( );
			return BuildTexture( id );
		} catch( Exception ex ) {
			ex.printStackTrace( );
			System.exit( 1 );
		}

		return null;
	}

	public void bind( ) {
		glBindTexture( GL_TEXTURE_2D, id );
	}
}
