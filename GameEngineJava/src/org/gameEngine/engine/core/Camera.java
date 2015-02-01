package org.gameEngine.engine.core;

import org.gameEngine.engine.physics.maths.Matrix4f;
import org.gameEngine.engine.physics.maths.Vector2f;
import org.gameEngine.engine.physics.maths.Vector3f;

/**
 * Created by TekMaTek on 26/01/2015.
 */
public class Camera {

	public float zNear;//Camera stuff here for a mo.
	public float zFar;
	public float width;
	public float height;
	public float fieldOfView;

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

	public void input( ) {
		final float SPEED_MOD = 0.25f;
		final float SENSITIVITY = 0.000025f;
		float moveAmount = SPEED_MOD * ( float ) ( 100 * Time.GetDeltaTime( ) / Time.SECOND );

		if( Input.GetKeyDown( Input.KEY_W ) ) {
			move( getForward( ), moveAmount );
		} else if( Input.GetKeyDown( Input.KEY_S ) ) {
			move( getForward( ), -moveAmount );
		} else if( Input.GetKeyDown( Input.KEY_A ) ) {
			move( getLeft( ), moveAmount );
		} else if( Input.GetKeyDown( Input.KEY_D ) ) {
			move( getRight( ), moveAmount );
		}

		if( Input.GetMouseDown( 0 ) ){
			//Moves towards the mouse cursor.
			Vector2f deltaPosition = Input.GetMousePosition( ).sub( Input.CENTER_MOUSE_POS );

			boolean rotY = deltaPosition.getX() != 0;
			boolean rotX = deltaPosition.getY() != 0;

			if( rotY ) {
				rotateY( ( forward.getX() - deltaPosition.getX() ) * SENSITIVITY );
			}
			if( rotX ) {
				rotateX( -( forward.getY() - deltaPosition.getY() ) * SENSITIVITY );
			}
		}
	}

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
		Vector3f hAxis = yAxis.cross( forward ).normalized( );

		forward = forward.rotate( hAxis, angle ).normalized();

		up = forward.cross( hAxis ).normalized();
	}

	public void rotateY( float angle ) {
		Vector3f hAxis = yAxis.cross( forward ).normalized();

		forward = forward.rotate( yAxis, angle ).normalized();

		up = forward.cross( hAxis ).normalized();
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
