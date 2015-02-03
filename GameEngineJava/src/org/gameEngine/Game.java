package org.gameEngine;

import org.gameEngine.engine.core.*;
import org.gameEngine.engine.physics.maths.Vector2f;
import org.gameEngine.engine.physics.maths.Vector3f;
import org.gameEngine.engine.rendering.MeshUtil;
import org.gameEngine.engine.rendering.Texture;
import org.gameEngine.engine.rendering.shaders.Material;
import org.lwjgl.input.Mouse;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Game {

	GameObject cameraGO;

	public void init( ) {
		GameObject plane = new GameObject( );
		cameraGO = new GameObject( );

		GameObject.getRoot( ).addChild( plane );
		GameObject.getRoot( ).addChild( cameraGO );
		cameraGO.addComponent( new Camera( ) );

		plane.addComponent( gridMesh( ) );
		plane.transform.setTranslation( 0.0f, -2.0f, 5.0f );
	}

	public MeshRenderer gridMesh( ) {
		Material mat = Material.BuildMaterial( Texture.loadTexture( "grids.png" ),
											   new Vector3f( 1.0f, 1.0f, 1.0f ), 1.0f, 8.0f );
		return new MeshRenderer( MeshUtil.BuildSimplePlane( ), mat );
	}

	public void UpdateInput( ) {
		input( cameraGO );
	}

	public void input( GameObject go ) {
		final float SPEED_MOD = 0.25f;
		final float SENSITIVITY = 0.0025f;
		float moveAmount = SPEED_MOD * ( float ) ( 100 * Time.GetDeltaTime( ) );

		float scroll = Mouse.getDWheel( );
		if( Input.GetKeyDown( Input.KEY_W ) || scroll > 0 ) {
			go.transform.move( go.transform.forward, moveAmount );
		} else if( Input.GetKeyDown( Input.KEY_S ) || scroll < 0 ) {
			go.transform.move( go.transform.forward, -moveAmount );
		} else if( Input.GetKeyDown( Input.KEY_A ) ) {
			go.transform.move( go.transform.getLeft( ), moveAmount );
		} else if( Input.GetKeyDown( Input.KEY_D ) ) {
			go.transform.move( go.transform.getRight( ), moveAmount );
		}

		Vector2f deltaPosition = Input.GetMousePosition( ).sub( Input.CENTER_MOUSE_POS );

		boolean rotY = Math.abs( deltaPosition.getX( ) ) > 100.0f;
		boolean rotX = Math.abs( deltaPosition.getY( ) ) > 100.0f;
		if( rotY ) {
			go.transform.rotateY( ( float ) ( -( go.transform.forward.getX( ) - deltaPosition.getX( ) ) *
					SENSITIVITY *
					Time.GetDeltaTime( ) ) );
		}
		if( rotX ) {
			go.transform.rotateX(
					( float ) ( ( go.transform.forward.getY( ) - deltaPosition.getY( ) ) * SENSITIVITY *
							Time.GetDeltaTime( ) ) );
		}
	}

	public void Update( ) {

	}
}
