package org.gameEngine;

import org.gameEngine.engine.core.Camera;
import org.gameEngine.engine.core.GameObject;
import org.gameEngine.engine.core.MeshRenderer;
import org.gameEngine.engine.core.Time;
import org.gameEngine.engine.physics.maths.Vector3f;
import org.gameEngine.engine.rendering.MeshUtil;
import org.gameEngine.engine.rendering.Texture;
import org.gameEngine.engine.rendering.shaders.Material;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Game {

	private GameObject plane;
	private Camera camera;
	private double temp;

	public void init( ) {
		plane = new GameObject( );

		GameObject.getRoot( ).addChild( plane );
		camera = new Camera( );
		camera.setProjection( 70.0f, StartGame.WIDTH, StartGame.HEIGHT, 0.1f, 1000.0f );

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
		camera.input( );
	}

	public void Update( ) {
		temp += Time.GetDeltaTime( );

	}
}
