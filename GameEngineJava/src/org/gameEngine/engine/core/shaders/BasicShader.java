package org.gameEngine.engine.core.shaders;

import org.gameEngine.engine.core.Transform;
import org.gameEngine.engine.core.render.ResourceLoader;
import org.gameEngine.engine.core.render.Util;

/**
 * Created by TekMaTek on 28/01/2015.
 */
public class BasicShader extends Shader {

	public BasicShader( ) {
		super( );

		addVertextShader( ResourceLoader.LoadShader( "basic/basicVertex.vertex" ) );
		addFragmentShader( ResourceLoader.LoadShader( "basic/basicFragment.fragment" ) );
		CompileShader( );

		addUniform( "transform" );
		addUniform( "color" );
	}

	public void updateUniforms( Transform transform ) {
		if( transform.material.texture != null ) {
			transform.material.texture.bind( );
		} else {
			Util.unbindTextures( );
		}
		setUniform4m( "transform", transform.getProjectedTransformation( ) );
		setUniform3f( "color", transform.material.color );
	}
}
