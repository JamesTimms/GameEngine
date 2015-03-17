package org.gameEngine.engine.core.components;

import org.gameEngine.StartGame;
import org.gameEngine.engine.core.Transform;
import org.gameEngine.engine.physics.maths.Matrix4f;

/**
 * Created by TekMaTek on 26/01/2015.
 */
public class Camera extends GameComponent {

	public static Camera mainCamera;

	public float zNear;
	public float zFar;
	public float width;
	public float height;
	public float fieldOfView;

	public Camera( ) {
		if( mainCamera == null ) {
			mainCamera = this;
		}
		this.setProjection( 70.0f, StartGame.WIDTH, StartGame.HEIGHT, 0.1f, 1000.0f );
	}

	public Matrix4f cameraProjection( ) {
		return new Matrix4f( ).initPerspective(
				this.fieldOfView, this.height / this.width, this.zNear, this.zFar );
	}

	public Matrix4f GetViewProjection( ) {
		Matrix4f cameraRotation = new Matrix4f( ).initCamera( transform.forward, transform.up );
		Matrix4f cameraTranslation = new Matrix4f( ).initTranslation(
				-transform.getTranslation( ).getX( ), -transform.getTranslation( ).getY( ),
				-transform.getTranslation( ).getZ( ) );

		return cameraProjection( ).mul( cameraRotation.mul( cameraTranslation ) );
	}

	public Transform GetTransform( ) {
		return super.transform;
	}

	public void setProjection( float fieldOfView, float width, float height, float zNear, float zFar ) {
		this.fieldOfView = ( fieldOfView > 1 ) ? fieldOfView : 1;
		this.width = width;
		this.height = height;
		this.zNear = ( zNear > 0 ) ? zNear : 0;
		this.zFar = ( zFar > zNear ) ? zFar : zFar + 1.0f;
	}
}