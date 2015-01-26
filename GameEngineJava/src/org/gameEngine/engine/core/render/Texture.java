package org.gameEngine.engine.core.render;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

/**
 * Created by TekMaTek on 26/01/2015.
 */
public class Texture {

	private int id;

	public Texture( int id ) {
		this.id = id;
	}

	public void bind( ) {
		glBindTexture( GL_TEXTURE_2D, id );
	}

	public int getId( ) {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}
}
