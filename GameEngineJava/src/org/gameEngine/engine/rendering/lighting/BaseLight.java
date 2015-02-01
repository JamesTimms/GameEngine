package org.gameEngine.engine.rendering.lighting;

import org.gameEngine.engine.physics.maths.Vector3f;

/**
 * Created by TekMaTek on 29/01/2015.
 */
public class BaseLight {

	public Vector3f color;
	public float intensity;

	public static BaseLight BuildBaseLight( Vector3f color, float intensity ) {
		BaseLight newBaseLight = new BaseLight( );
		newBaseLight.color = color;
		newBaseLight.intensity = intensity;
		return newBaseLight;
	}

	public static BaseLight WhiteLight( ) {
		return BuildBaseLight( Vector3f.ONE, 1.0f );
	}
}
