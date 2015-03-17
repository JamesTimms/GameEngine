package org.gameEngine.engine.rendering.shaders;

import org.gameEngine.engine.core.components.Camera;
import org.gameEngine.engine.core.Transform;
import org.gameEngine.engine.rendering.RenderingEngine;

/**
 * Created by TekMaTek on 28/01/2015.
 */
public class ForwardAmbient extends Shader {

	public ForwardAmbient( ) {
		super( );

		addVertextShader( LoadShader( "forward/ambient.vertex" ) );
		addFragmentShader( LoadShader( "forward/ambient.fragment" ) );
		CompileShader( );

		addUniform( "mvp" );
		addUniform( "color" );
	}

	public void updateUniforms( Transform transform, Material material, Camera camera ) {
		dealWithTexture( material );

		setUniform4m( "mvp", transform.getProjectedTransformation( camera ) );
		setUniform3f( "color", RenderingEngine.ambientLight );
	}
}
