package org.gameEngine.engine.core.render.lighting;

import org.gameEngine.engine.core.maths.Vector3f;

/**
 * Created by TekMaTek on 29/01/2015.
 */
public class DirectionalLight {

	public BaseLight baseLight;
	public Vector3f direction;

	public static DirectionalLight BuildDirectionalLight( BaseLight baseLight, Vector3f direction ) {
		DirectionalLight newDirLight = new DirectionalLight( );
		newDirLight.baseLight = baseLight;
		newDirLight.direction = direction;
		return newDirLight;
	}

	public static DirectionalLight Default( ) {
		return BuildDirectionalLight( BaseLight.WhiteLight( ), new Vector3f( 0.35f, 0.35f, 0.35f ) );
	}
}
