package org.gameEngine.engine.core;

import org.gameEngine.engine.physics.maths.Matrix4f;
import org.gameEngine.engine.physics.maths.Vector3f;
import org.gameEngine.engine.rendering.shaders.Material;

/**
 * Created by TekMaTek on 26/01/2015.
 */
public class Transform {

	private static float zNear;//Camera stuff here for a mo.
	private static float zFar;
	private static float width;
	private static float height;
	private static float fieldOfView;
	private Camera camera;
	private Vector3f translation;
	private Vector3f rotation;
	private Vector3f scale;

	//TODO: move into gameObject object.
	public Material material = Material.WhiteNoTexture( );
	//

	public Transform( ) {
		this.translation = Vector3f.ZERO;
		this.rotation = Vector3f.ZERO;
		this.scale = Vector3f.ONE;
	}

	public static void setProjection( float fieldOfView, float width, float height, float zNear, float zFar ) {
		Transform.fieldOfView = ( fieldOfView > 1 ) ? fieldOfView : 1;
		Transform.width = width;
		Transform.height = height;
		Transform.zNear = ( zNear > 0 ) ? zNear : 0;
		Transform.zFar = ( zFar > zNear ) ? zFar : zFar + 1.0f;
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

	public Matrix4f getProjectedTransformation( ) {//Camera stuff needs to be moved out.
		Matrix4f transformationMatrix = getTransformMatrix( );
		Matrix4f projectionMatrix = new Matrix4f( ).initPerspective(
				Transform.fieldOfView, Transform.height / Transform.width, Transform.zNear, Transform.zFar );
		Matrix4f cameraRotation = new Matrix4f( ).initCamera( camera.getForward( ), camera.getUp( ) );
		Matrix4f cameraTranslation = new Matrix4f( ).initTranslation(
				-camera.getPos( ).getX( ), -camera.getPos( ).getY( ), -camera.getPos( ).getZ( ) );

		return projectionMatrix.mul( cameraRotation.mul( cameraTranslation.mul( transformationMatrix ) ) );
	}

	public Vector3f getTranslation( ) {
		return translation;
	}

	public void setTranslation( Vector3f translation ) {
		this.translation = translation;
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

	public Camera getCamera( ) {
		return camera;
	}

	public void setCamera( Camera camera ) {
		this.camera = camera;
	}

}
