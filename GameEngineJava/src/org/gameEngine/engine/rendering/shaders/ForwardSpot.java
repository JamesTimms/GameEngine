package org.gameEngine.engine.rendering.shaders;

import org.gameEngine.engine.core.components.Camera;
import org.gameEngine.engine.core.Transform;
import org.gameEngine.engine.physics.maths.Vector3f;
import org.gameEngine.engine.rendering.lighting.Attenuation;
import org.gameEngine.engine.rendering.lighting.BaseLight;
import org.gameEngine.engine.rendering.lighting.PointLight;
import org.gameEngine.engine.rendering.lighting.SpotLight;

/**
 * Created by TekMaTek on 28/01/2015.
 */
public class ForwardSpot extends Shader {

	public ForwardSpot( ) {
		super( );

		addVertextShader( LoadShader( "forward/spot.vertex" ) );
		addFragmentShader( LoadShader( "forward/spot.fragment" ) );
		CompileShader( );

		addUniform( "transform" );
		addUniform( "transformProjected" );

		addUniform( "specularIntensity" );
		addUniform( "specularExponent" );
		addUniform( "eyePos" );

		addUniform( "spotLight.pointLight.base.color" );
		addUniform( "spotLight.pointLight.base.intensity" );
		addUniform( "spotLight.pointLight.atten.constant" );
		addUniform( "spotLight.pointLight.atten.linear" );
		addUniform( "spotLight.pointLight.atten.exponent" );
		addUniform( "spotLight.pointLight.position" );
		addUniform( "spotLight.pointLight.range" );
		addUniform( "spotLight.direction" );
		addUniform( "spotLight.cutoff" );
	}

	public void updateUniforms( Transform transform, Material material, Camera camera ) {
		dealWithTexture( material );

		setUniform4m( "transform", transform.getTransformMatrix( ) );
		setUniform4m( "transformProjected", transform.getProjectedTransformation( camera ) );
		setUniform( "spotLight", SpotLight.spotLight );

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

	protected void setUniform( String uniformName, SpotLight spotLight ) {
		setUniform( uniformName + ".pointLight", spotLight.pointLight );
		setUniform3f( uniformName + ".direction", spotLight.direction );
		setUniformf( uniformName + ".cutoff", spotLight.cutoff );
	}

}
