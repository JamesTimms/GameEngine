package org.gameEngine.engine.core.shaders;

import org.gameEngine.engine.core.Transform;
import org.gameEngine.engine.core.render.ResourceLoader;
import org.gameEngine.engine.core.render.Util;
import org.gameEngine.engine.core.render.lighting.DirectionalLight;

/**
 * Created by TekMaTek on 28/01/2015.
 */
public class PhongShader extends Shader {

	public static DirectionalLight directionalLight = DirectionalLight.Default( );

	public PhongShader( ) {
		super( );

		addVertextShader( ResourceLoader.LoadShader( "phong/phongVertex.vertex" ) );
		addFragmentShader( ResourceLoader.LoadShader( "phong/phongFragment.fragment" ) );
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

	public void updateUniforms( Transform transform ) {
		if( transform.material.texture != null ) {
			transform.material.texture.bind( );
		} else {
			Util.unbindTextures( );
		}
		setUniform4m( "transform", transform.getTransformMatrix( ) );
		setUniform4m( "transformProjected", transform.getProjectedTransformation( ) );
		setUniform3f( "baseColor", transform.material.color );
		setUniform3f( "ambientLight", Shader.ambientLight );
		setUniform( "directionLight", directionalLight );

		setUniformf( "specularIntensity", transform.material.specularIntensity );
		setUniformf( "specularExponent", transform.material.specularExponent );
		setUniform3f( "eyePos", transform.getCamera( ).getPos( ) );
	}

	protected void setUniform( String uniformName, DirectionalLight directionalLight ) {
		setUniform3f( uniformName + ".base.color", directionalLight.baseLight.color );
		setUniform3f( uniformName + ".direction", directionalLight.getDirection( ) );
		setUniformf( uniformName + ".base.intensity", directionalLight.baseLight.intensity );
	}
}
