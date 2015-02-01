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

	private GameObject plane;
	private Camera camera;

	public void init( ) {
		plane = new GameObject( );

		GameObject.getRoot( ).addChild( plane );
		camera = new Camera( );

		plane.addComponent( gridMesh( ) );
		plane.transform.camera = camera;
		plane.transform.setTranslation( 0.0f, -2.0f, 5.0f );
	}

	public MeshRenderer gridMesh( ) {
		Material mat = Material.BuildMaterial( Texture.loadTexture( "grids.png" ),
											   new Vector3f( 1.0f, 1.0f, 1.0f ), 1.0f, 8.0f );
		return new MeshRenderer( MeshUtil.BuildSimplePlane( ), mat );
	}

	public void UpdateInput( ) {
		input( camera );
	}


	public void input( Camera camera ) {
		final float SPEED_MOD = 0.25f;
		final float SENSITIVITY = 0.0025f;
		float moveAmount = SPEED_MOD * ( float ) ( 100 * Time.GetDeltaTime( ) );

		float scroll = Mouse.getDWheel( );
		if( Input.GetKeyDown( Input.KEY_W ) || scroll > 0 ) {
			camera.move( camera.getForward( ), moveAmount );
		} else if( Input.GetKeyDown( Input.KEY_S ) || scroll < 0 ) {
			camera.move( camera.getForward( ), -moveAmount );
		} else if( Input.GetKeyDown( Input.KEY_A ) ) {
			camera.move( camera.getLeft( ), moveAmount );
		} else if( Input.GetKeyDown( Input.KEY_D ) ) {
			camera.move( camera.getRight( ), moveAmount );
		}

		if( Input.GetMouseDown( 0 ) ) {
			Vector2f deltaPosition = Input.GetMousePosition( ).sub( Input.CENTER_MOUSE_POS );

			boolean rotY = Math.abs( deltaPosition.getX( ) ) > 100.0f;
			boolean rotX = Math.abs( deltaPosition.getY( ) ) > 100.0f;
			if( rotY ) {
				camera.rotateY( ( float ) ( -( camera.getForward( ).getX( ) - deltaPosition.getX( ) ) * SENSITIVITY *
						Time.GetDeltaTime( ) ) );
			}
			if( rotX ) {
				camera.rotateX( ( float ) ( ( camera.getForward( ).getY( ) - deltaPosition.getY( ) ) * SENSITIVITY *
						Time.GetDeltaTime( ) ) );
			}
		}
	}

	public void Update( ) {

	}
}
