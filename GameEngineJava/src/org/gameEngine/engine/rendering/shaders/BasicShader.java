package org.gameEngine.engine.rendering.shaders;

import org.gameEngine.engine.core.Transform;
import org.gameEngine.engine.rendering.RenderingUtil;

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

	public void updateUniforms( Transform transform ) {
		if( transform.material.texture != null ) {
			transform.material.texture.bind( );
		} else {
			RenderingUtil.unbindTextures( );
		}
		setUniform4m( "transform", transform.getProjectedTransformation( ) );
		setUniform3f( "color", transform.material.color );
	}
}
