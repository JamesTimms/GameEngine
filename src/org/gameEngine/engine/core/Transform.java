package org.gameEngine.engine.core;

import org.gameEngine.engine.core.components.Camera;
import org.gameEngine.engine.physics.maths.Matrix4f;
import org.gameEngine.engine.physics.maths.Vector3f;

/**
 * Created by TekMaTek on 26/01/2015.
 */
public class Transform {

	public Vector3f forward = new Vector3f( 0.0f, 0.0f, 1.0f );
	public Vector3f up = new Vector3f( 0.0f, 1.0f, 0.0f );
	public Vector3f yAxis = new Vector3f( 0.0f, 1.0f, 0.0f );
	private Vector3f translation;
	private Vector3f rotation;
	private Vector3f scale;

	public Transform( ) {
		this.translation = Vector3f.ZERO;
		this.rotation = Vector3f.ZERO;
		this.scale = Vector3f.ONE;
	}

	public Matrix4f getTransformMatrix( ) {
		Matrix4f translationMat =
				new Matrix4f( ).initTranslation( translation.getX( ), translation.getY( ), translation.getZ( ) );
		Matrix4f rotationMat =
				new Matrix4f( ).initRotation( rotation.getX( ), rotation.getY( ), rotation.getZ( ) );
		Matrix4f scaleMat =
				new Matrix4f( ).initScale( scale.getX( ), scale.getY( ), scale.getZ( ) );

		return translationMat.mul( rotationMat.mul( scaleMat ) );
	}

	public Matrix4f getProjectedTransformation( Camera camera ) {//Camera stuff needs to be moved out.
		return camera.GetViewProjection( ).mul( getTransformMatrix( ) );

	}

	public Vector3f getTranslation( ) {
		return translation;
	}

	public void setTranslation( Vector3f translation ) {
		this.translation = translation;
	}

	public void move( Vector3f direction, float amount ) {
		Vector3f newPosition = translation.add( direction.mul( amount ) );
		this.setTranslation( newPosition );
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

	public void setTranslation( float x, float y, float z ) {
		this.translation = new Vector3f( x, y, z );
	}

	public Vector3f getRotation( ) {
		return rotation;
	}

	public void setRotation( Vector3f rotation ) {
		this.rotation = rotation;
	}

	public void setRotation( float x, float y, float z ) {
		this.rotation = new Vector3f( x, y, z );
	}

	public Vector3f getScale( ) {
		return scale;
	}

	public void setScale( Vector3f scale ) {
		this.scale = scale;
	}

	public void setScale( float x, float y, float z ) {
		this.scale = new Vector3f( x, y, z );
	}

}
