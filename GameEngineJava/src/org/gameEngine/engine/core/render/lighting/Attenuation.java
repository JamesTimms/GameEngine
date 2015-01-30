package org.gameEngine.engine.core.render.lighting;

/**
 * Created by TekMaTek on 29/01/2015.
 */
public class Attenuation {

	public float constant;
	public float linear;
	public float exponent;

	private Attenuation( ) {
	}

	public static Attenuation BuildAttenutation( float constant, float linear, float exponent ) {
		Attenuation newAttenuation = new Attenuation( );
		newAttenuation.constant = constant;
		newAttenuation.linear = linear;
		newAttenuation.exponent = exponent;
		return newAttenuation;
	}

	public static Attenuation DefaultAttenuation( ) {
		return BuildAttenutation( 0.2f, 0.5f, 1.0f );
	}

}
