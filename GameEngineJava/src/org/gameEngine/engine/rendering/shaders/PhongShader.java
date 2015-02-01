package org.gameEngine.engine.rendering.shaders;

import org.gameEngine.engine.core.Transform;
import org.gameEngine.engine.rendering.lighting.DirectionalLight;

/**
 * Created by TekMaTek on 28/01/2015.
 */
public class PhongShader extends Shader {

	public static DirectionalLight directionalLight = DirectionalLight.Default( );

	public PhongShader( ) {
		super( );

		addVertextShader( LoadShader( "phong/phongVertex.vertex" ) );
		addFragmentShader( LoadShader( "phong/phongFragment.fragment" ) );
		CompileShader( );

		addUniform( "transform" );
		addUniform( "transformProjected" );
		addUniform( "baseColor" );
		addUniform( "ambientLight" );

		addUniform( "directionLight.base.color" );
		addUniform( "directionLight.base.intensity" );
		addUniform( "directionLight.direction" );

		addUniform( "specularIntensity" );
		addUniform( "specularExponent" );
		addUniform( "eyePos" );
	}

	public void updateUniforms( Transform transform, Material material ) {
		dealWithTexture( material );
		setUniform4m( "transform", transform.getTransformMatrix( ) );
		setUniform4m( "transformProjected", transform.getProjectedTransformation( ) );
		setUniform3f( "baseColor", material.color );
		setUniform3f( "ambientLight", Shader.ambientLight );
		setUniform( "directionLight", directionalLight );

		setUniformf( "specularIntensity", material.specularIntensity );
		setUniformf( "specularExponent", material.specularExponent );
		setUniform3f( "eyePos", transform.camera.getPos( ) );
	}

	protected void setUniform( String uniformName, DirectionalLight directionalLight ) {
		setUniform3f( uniformName + ".base.color", directionalLight.baseLight.color );
		setUniform3f( uniformName + ".direction", directionalLight.getDirection( ) );
		setUniformf( uniformName + ".base.intensity", directionalLight.baseLight.intensity );
	}
}
