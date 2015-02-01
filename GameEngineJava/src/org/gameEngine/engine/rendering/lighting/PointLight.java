package org.gameEngine.engine.rendering.lighting;

import org.gameEngine.engine.physics.maths.Vector3f;

/**
 * Created by TekMaTek on 29/01/2015.
 */
public class PointLight {

	public BaseLight baseLight;
	public Attenuation atten;
	public Vector3f position;
	public float range;

	private PointLight( ) {
	}

	public static PointLight BuildPointLight( BaseLight baseLight, Attenuation atten, Vector3f position, float range ) {
		PointLight newPointLight = new PointLight( );
		newPointLight.baseLight = baseLight;
		newPointLight.atten = atten;
		newPointLight.position = position;
		newPointLight.range = range;
		return newPointLight;
	}

	public static PointLight DefaultPointLight( ) {
		return BuildPointLight(
				BaseLight.WhiteLight( ), Attenuation.DefaultAttenuation( ), new Vector3f( 2.0f, 2.0f, 2.0f ), 3.0f );
	}
}
