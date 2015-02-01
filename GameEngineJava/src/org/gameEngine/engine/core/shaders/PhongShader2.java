package org.gameEngine.engine.core.shaders;

import org.gameEngine.engine.core.Transform;
import org.gameEngine.engine.core.render.ResourceLoader;
import org.gameEngine.engine.core.render.Util;
import org.gameEngine.engine.core.render.lighting.BaseLight;
import org.gameEngine.engine.core.render.lighting.DirectionalLight;
import org.gameEngine.engine.core.render.lighting.PointLight;
import org.gameEngine.engine.core.render.lighting.SpotLight;

/**
 * Created by TekMaTek on 28/01/2015.
 */
public class PhongShader2 extends Shader {

	private static final int MAX_POINT_LIGHTS = 4;
	private static final int MAX_SPOT_LIGHTS = 4;

	public static DirectionalLight directionalLight = DirectionalLight.Default( );
	public static PointLight[] pointLights = new PointLight[] { };
	public static SpotLight[] spotLights = new SpotLight[] { };

	public PhongShader2( ) {
		super( );

		addVertextShader( ResourceLoader.LoadShader( "pointLight/phongVertex.vertex" ) );
		addFragmentShader( ResourceLoader.LoadShader( "pointLight/phongFragment.fragment" ) );
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

		for( int i = 0; i < MAX_POINT_LIGHTS; i++ ) {
			addUniform( "pointLights[" + i + "].base.color" );
			addUniform( "pointLights[" + i + "].base.intensity" );
			addUniform( "pointLights[" + i + "].atten.constant" );
			addUniform( "pointLights[" + i + "].atten.linear" );
			addUniform( "pointLights[" + i + "].atten.exponent" );
			addUniform( "pointLights[" + i + "].position" );
			addUniform( "pointLights[" + i + "].range" );
		}
		for( int i = 0; i < MAX_SPOT_LIGHTS; i++ ) {
			addUniform( "spotLights[" + i + "].pointLight.base.color" );
			addUniform( "spotLights[" + i + "].pointLight.base.intensity" );
			addUniform( "spotLights[" + i + "].pointLight.atten.constant" );
			addUniform( "spotLights[" + i + "].pointLight.atten.linear" );
			addUniform( "spotLights[" + i + "].pointLight.atten.exponent" );
			addUniform( "spotLights[" + i + "].pointLight.position" );
			addUniform( "spotLights[" + i + "].pointLight.range" );

			addUniform( "spotLights[" + i + "].direction" );
			addUniform( "spotLights[" + i + "].cutoff" );
		}
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
		for( int i = 0; i < pointLights.length; i++ ) {
			setUniform( "pointLights[" + i + "]", pointLights[ i ] );
		}
		for( int i = 0; i < spotLights.length; i++ ) {
			setUniform( "spotLights[" + i + "]", spotLights[ i ] );
		}

		setUniformf( "specularIntensity", transform.material.specularIntensity );
		setUniformf( "specularExponent", transform.material.specularExponent );
		setUniform3f( "eyePos", transform.getCamera( ).getPos( ) );
	}

	protected void setUniform( String uniformName, DirectionalLight directionalLight ) {
		setUniform( uniformName + ".base", directionalLight.baseLight );
		setUniform3f( uniformName + ".direction", directionalLight.getDirection( ) );
	}

	protected void setUniform( String uniformName, BaseLight baseLight ) {
		setUniform3f( uniformName + ".color", baseLight.color );
		setUniformf( uniformName + ".intensity", baseLight.intensity );
	}

	protected void setUniform( String uniformName, PointLight pointLight ) {
		setUniform( uniformName + ".base", pointLight.baseLight );
		setUniformf( uniformName + ".atten.constant", pointLight.atten.constant );
		setUniformf( uniformName + ".atten.linear", pointLight.atten.linear );
		setUniformf( uniformName + ".atten.exponent", pointLight.atten.exponent );
		setUniform3f( uniformName + ".position", pointLight.position );
		setUniformf( uniformName + ".range", pointLight.range );
	}

	protected void setUniform( String uniformName, SpotLight spotLight ) {
		setUniform( uniformName + ".pointLight", spotLight.pointLight );
		setUniform3f( uniformName + ".direction", spotLight.direction );
		setUniformf( uniformName + ".cutoff", spotLight.cutoff );
	}
}
