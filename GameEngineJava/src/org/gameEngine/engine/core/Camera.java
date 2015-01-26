package org.gameEngine.engine.core;

import org.gameEngine.engine.core.maths.Vector3f;

/**
 * Created by TekMaTek on 26/01/2015.
 */
public class Camera {

	public static final Vector3f yAxis = new Vector3f( 0.0f, 1.0f, 0.0f );

	private Vector3f pos;
	private Vector3f forward;
	private Vector3f up;

	public Camera( Vector3f pos, Vector3f forward, Vector3f up ) {
		this.pos = pos;
		this.forward = forward;
		this.up = up;
	}

	public Camera( ) {
		this( new Vector3f( 0.0f, 0.0f, 0.0f ), new Vector3f( 0.0f, 0.0f, 1.0f ), new Vector3f( 0.0f, 1.0f, 0.0f ) );
	}

//	public void input( ) {
//		float moveAmount = ( float ) ( 10 * Time.GetDeltaTime( ) / Time.SECOND );
//		float rotAmount = ( float ) ( 100 * Time.GetDeltaTime( ) / Time.SECOND );
//
//
//	}

	public void move( Vector3f direction, float amount ) {
		pos = pos.add( direction.mul( amount ) );
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
		Vector3f hAxis = yAxis.cross( forward );
		hAxis.normalized( );

		forward.rotate( hAxis, angle );
		forward.normalized( );

		up = forward.cross( hAxis );
		up.normalized( );
	}

	public void rotateY( float angle ) {
		Vector3f hAxis = yAxis.cross( forward );
		hAxis.normalized( );

		forward.rotate( yAxis, angle );
		forward.normalized( );

		up = forward.cross( hAxis );
		up.normalized( );
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

	public void setForward( Vector3f forward ) {
		this.forward = forward;
	}

	public Vector3f getPos( ) {
		return pos;
	}

	public void setPos( Vector3f pos ) {
		this.pos = pos;
	}
}
