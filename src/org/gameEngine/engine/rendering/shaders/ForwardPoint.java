package org.gameEngine.engine.rendering.shaders;

import org.gameEngine.engine.core.components.Camera;
import org.gameEngine.engine.core.Transform;
import org.gameEngine.engine.rendering.lighting.BaseLight;
import org.gameEngine.engine.rendering.lighting.PointLight;

/**
 * Created by TekMaTek on 28/01/2015.
 */
public class ForwardPoint extends Shader {


	public ForwardPoint( ) {
		super( );

		addVertextShader( LoadShader( "forward/point.vertex" ) );
		addFragmentShader( LoadShader( "forward/point.fragment" ) );
		CompileShader( );

		addUniform( "transform" );
		addUniform( "transformProjected" );

		addUniform( "specularIntensity" );
		addUniform( "specularExponent" );
		addUniform( "eyePos" );

		addUniform( "pointLight.base.color" );
		addUniform( "pointLight.base.intensity" );
		addUniform( "pointLight.atten.constant" );
		addUniform( "pointLight.atten.linear" );
		addUniform( "pointLight.atten.exponent" );
		addUniform( "pointLight.position" );
		addUniform( "pointLight.range" );
	}

	public void updateUniforms( Transform transform, Material material, Camera camera ) {
		dealWithTexture( material );

		setUniform4m( "transform", transform.getTransformMatrix( ) );
		setUniform4m( "transformProjected", transform.getProjectedTransformation( camera ) );
		setUniform( "pointLight", PointLight.pointLight );

		setUniformf( "specularIntensity", material.specularIntensity );
		setUniformf( "specularExponent", material.specularExponent );
		setUniform3f( "eyePos", camera.GetTransform( ).getTranslation( ) );
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

}
