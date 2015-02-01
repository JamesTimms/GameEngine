package org.gameEngine.engine.core;

import org.gameEngine.engine.rendering.shaders.Shader;

/**
 * Created by TekMaTek on 01/02/2015.
 */
public abstract class GameComponent {

	protected Transform transform;
	protected GameObject gameObject;

	public void init( ) {

	}

	public void input( ) {

	}

	public void update( ) {

	}

	public void render( Shader shader ) {

	}
}
