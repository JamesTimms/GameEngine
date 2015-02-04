package org.gameEngine.engine.core.components;

import org.gameEngine.engine.core.GameObject;
import org.gameEngine.engine.core.Transform;
import org.gameEngine.engine.rendering.shaders.Shader;

/**
 * Created by TekMaTek on 01/02/2015.
 */
public abstract class GameComponent< T > {

	public Transform transform;
	public GameObject gameObject;
	public T componentObject;

//	public GameComponent( T componentObject ) {
//		this.componentObject = componentObject;
//	}

	public void init( ) {

	}

	public void input( ) {

	}

	public void update( ) {

	}

	public void render( Shader shader ) {

	}

	public T getComponent( ) {
		return componentObject;
	}
}
