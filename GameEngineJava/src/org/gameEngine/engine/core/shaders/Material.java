package org.gameEngine.engine.core.shaders;

import org.gameEngine.engine.core.maths.Vector3f;
import org.gameEngine.engine.core.render.Texture;

/**
 * Created by TekMaTek on 28/01/2015.
 */
public class Material {

	public Texture texture;
	public Vector3f color;

	private Material( ) {
	}

	public static Material BuildMaterial( Texture texture, Vector3f color ) {
		Material newMaterial = new Material( );
		newMaterial.texture = texture;
		newMaterial.color = color;
		return newMaterial;
	}

	public static Material WhiteNoTexture( ) {
		return BuildMaterial( Texture.NoTexture( ), Vector3f.ONE );
	}

}
