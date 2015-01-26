package org.gameEngine.engine.core.render;

import org.gameEngine.engine.core.maths.Vector3f;

/**
 * Created by TekMaTek on 27/10/2014.
 */
public class Vertex {

	public static final int SIZE = 3;

	protected Vector3f position;

	public Vertex( Vector3f position ) {
		this.position = position;
	}

	public Vector3f getPosition( ) {
		return position;
	}

	public void setPosition( Vector3f position ) {
		this.position = position;
	}
}
