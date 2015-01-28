package org.gameEngine.engine.core.shaders;

import org.gameEngine.engine.core.maths.Vector3f;
import org.gameEngine.engine.core.render.Texture;

/**
 * Created by TekMaTek on 28/01/2015.
 */
public class Material {

	private Texture texture;
	private Vector3f color;

	public Material( Texture texture, Vector3f color ) {
		this.texture = texture;
		this.color = color;
	}

	public Texture getTexture( ) {
		return texture;
	}

	public void setTexture( Texture texture ) {
		this.texture = texture;
	}

	public Vector3f getColor( ) {
		return color;
	}

	public void setColor( Vector3f color ) {
		this.color = color;
	}
}
