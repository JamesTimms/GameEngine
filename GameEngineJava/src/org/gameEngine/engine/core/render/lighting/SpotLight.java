package org.gameEngine.engine.core.render.lighting;

import org.gameEngine.engine.core.maths.Vector3f;

/**
 * Created by TekMaTek on 01/02/2015.
 */
public class SpotLight {

	public PointLight pointLight;
	public Vector3f direction;
	public float cutoff;

	private SpotLight( ) {
	}

	public static SpotLight BuildSpotLight( PointLight pointLight, Vector3f direction, float cutoff ) {
		SpotLight newSpotLight = new SpotLight( );
		newSpotLight.pointLight = pointLight;
		newSpotLight.direction = direction.normalized( );
		newSpotLight.cutoff = cutoff;
		return newSpotLight;
	}

	public static SpotLight DefaultSpotLight( ) {
		return BuildSpotLight( PointLight.DefaultPointLight( ), Vector3f.ONE, 2.0f );
	}
}
