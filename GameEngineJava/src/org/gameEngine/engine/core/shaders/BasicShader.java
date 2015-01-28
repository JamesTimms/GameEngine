package org.gameEngine.engine.core.shaders;

import org.gameEngine.engine.core.maths.Matrix4f;
import org.gameEngine.engine.core.render.ResourceLoader;
import org.gameEngine.engine.core.render.Util;

/**
 * Created by TekMaTek on 28/01/2015.
 */
public class BasicShader extends Shader {

	public BasicShader( ) {
		super( );

		addVertextShader( ResourceLoader.LoadShader( "basicVertex.vertex" ) );
		addFragmentShader( ResourceLoader.LoadShader( "basicFragment.fragment" ) );
		CompileShader( );

		addUniform( "transform" );
		addUniform( "color" );
	}

	public void updateUniforms( Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material ) {
		if( material.getTexture() != null ) {
			material.getTexture().bind();
		}else {
			Util.unbindTextures( );
		}
		setUniform4m( "transform", projectedMatrix );
		setUniform3f( "color", material.getColor( ) );
	}
}
