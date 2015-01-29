package org.gameEngine.engine.core.render;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

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
		return newTexture;
	}

	public static Texture NoTexture( ) {
		return BuildTexture( 0 );
	}

	public void bind( ) {
		glBindTexture( GL_TEXTURE_2D, id );
	}
}
