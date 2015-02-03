package org.gameEngine.engine.rendering.shaders;

import org.gameEngine.engine.core.components.Camera;
import org.gameEngine.engine.core.Transform;
import org.gameEngine.engine.rendering.RenderingEngine;
import org.gameEngine.engine.rendering.lighting.BaseLight;
import org.gameEngine.engine.rendering.lighting.DirectionalLight;

/**
 * Created by TekMaTek on 28/01/2015.
 */
public class ForwardDirectional extends Shader {

	public ForwardDirectional( ) {
		super( );

		addVertextShader( LoadShader( "forward/directional.vertex" ) );
		addFragmentShader( LoadShader( "forward/directional.fragment" ) );
		CompileShader( );

		addUniform( "transform" );
		addUniform( "transformProjected" );

		addUniform( "directionLight.base.color" );
		addUniform( "directionLight.base.intensity" );
		addUniform( "directionLight.direction" );

		addUniform( "specularIntensity" );
		addUniform( "specularExponent" );
		addUniform( "eyePos" );
	}

	public void updateUniforms( Transform transform, Material material, Camera camera ) {
		dealWithTexture( material );

		setUniform4m( "transform", transform.getTransformMatrix( ) );
		setUniform4m( "transformProjected", transform.getProjectedTransformation( camera ) );
		setUniform( "directionLight", RenderingEngine.directionalLight );

		setUniformf( "specularIntensity", material.specularIntensity );
		setUniformf( "specularExponent", material.specularExponent );
		setUniform3f( "eyePos", camera.GetTransform( ).getTranslation( ) );
	}

	protected void setUniform( String uniformName, DirectionalLight directionalLight ) {
		setUniform( uniformName + ".base", directionalLight.baseLight );
		setUniform3f( uniformName + ".direction", directionalLight.getDirection( ) );
	}

	protected void setUniform( String uniformName, BaseLight baseLight ) {
		setUniform3f( uniformName + ".color", baseLight.color );
		setUniformf( uniformName + ".intensity", baseLight.intensity );
	}

}
