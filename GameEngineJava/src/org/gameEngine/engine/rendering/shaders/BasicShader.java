package org.gameEngine.engine.rendering.shaders;

import org.gameEngine.engine.core.Camera;
import org.gameEngine.engine.core.Transform;

/**
 * Created by TekMaTek on 28/01/2015.
 */
public class BasicShader extends Shader {

	public BasicShader( ) {
		super( );

		addVertextShader( LoadShader( "basic/basicVertex.vertex" ) );
		addFragmentShader( LoadShader( "basic/basicFragment.fragment" ) );
		CompileShader( );

		addUniform( "transform" );
		addUniform( "color" );
	}

	public void updateUniforms( Transform transform, Material material, Camera camera ) {
		dealWithTexture( material );

		setUniform4m( "transform", transform.getProjectedTransformation( camera ) );
		setUniform3f( "color", material.color );
	}
}
