package org.gameEngine.engine.core;

import org.gameEngine.StartGame;
import org.gameEngine.engine.physics.maths.Matrix4f;
import org.gameEngine.engine.physics.maths.Vector3f;

/**
 * Created by TekMaTek on 26/01/2015.
 */
public class Camera {

	public static final Vector3f yAxis = new Vector3f( 0.0f, 1.0f, 0.0f );
	public float zNear;
	public float zFar;
	public float width;
	public float height;
	public float fieldOfView;
	public Transform transform = new Transform( );
	private Vector3f forward;
	private Vector3f up;

	public Camera( Vector3f pos, Vector3f forward, Vector3f up ) {
		this.transform.setTranslation( pos );
		this.forward = forward;
		this.up = up;
	}

	public Camera( ) {
		this( new Vector3f( 0.0f, 0.0f, 0.0f ), new Vector3f( 0.0f, 0.0f, 1.0f ), new Vector3f( 0.0f, 1.0f, 0.0f ) );
		this.setProjection( 70.0f, StartGame.WIDTH, StartGame.HEIGHT, 0.1f, 1000.0f );
	}

	public Matrix4f cameraProjection( ) {
		return new Matrix4f( ).initPerspective(
				this.fieldOfView, this.height / this.width, this.zNear, this.zFar );
	}

	public void setProjection( float fieldOfView, float width, float height, float zNear, float zFar ) {
		this.fieldOfView = ( fieldOfView > 1 ) ? fieldOfView : 1;
		this.width = width;
		this.height = height;
		this.zNear = ( zNear > 0 ) ? zNear : 0;
		this.zFar = ( zFar > zNear ) ? zFar : zFar + 1.0f;
	}

	public void move( Vector3f direction, float amount ) {
		Vector3f newPosition = transform.getTranslation( ).add( direction.mul( amount ) );
		this.transform.setTranslation( newPosition );
	}

	public Vector3f getLeft( ) {
		Vector3f left = forward.cross( up );
		left.normalized( );
		return left;
	}

	public Vector3f getRight( ) {
		Vector3f left = up.cross( forward );
		left.normalized( );
		return left;
	}

	public void rotateX( float angle ) {
		Vector3f hAxis = yAxis.cross( forward ).normalized( );

		forward = forward.rotate( hAxis, angle ).normalized( );

		up = forward.cross( hAxis ).normalized( );
	}

	public void rotateY( float angle ) {
		Vector3f hAxis = yAxis.cross( forward ).normalized( );

		forward = forward.rotate( yAxis, angle ).normalized( );

		up = forward.cross( hAxis ).normalized( );
	}

	public Vector3f getUp( ) {
		return up;
	}

	public void setUp( Vector3f up ) {
		this.up = up;
	}

	public Vector3f getForward( ) {
		return forward;
	}

}
