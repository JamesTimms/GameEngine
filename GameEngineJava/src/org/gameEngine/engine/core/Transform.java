package org.gameEngine.engine.core;

import org.gameEngine.engine.core.maths.Matrix4f;
import org.gameEngine.engine.core.maths.Vector3f;

/**
 * Created by TekMaTek on 26/01/2015.
 */
public class Transform {

	private Vector3f translation;

	public Transform() {
		this.translation = Vector3f.Zero();
	}

	public Matrix4f getTransformation() {
		Matrix4f translationMat = new Matrix4f().initTranslation( translation.getX(), translation.getY(),
																  translation.getZ() );

		return translationMat;
	}

	public Vector3f getTranslation() {
		return translation;
	}

	public void setTranslation( Vector3f translation ) {
		this.translation = translation;
	}

	public void setTranslation( float x, float y, float z ) {
		this.translation = new Vector3f( x, y, z );
	}
}
