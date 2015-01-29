package org.gameEngine.game;

import org.gameEngine.engine.core.Camera;
import org.gameEngine.engine.core.Time;
import org.gameEngine.engine.core.Transform;
import org.gameEngine.engine.core.maths.Vector2f;
import org.gameEngine.engine.core.maths.Vector3f;
import org.gameEngine.engine.core.render.Mesh;
import org.gameEngine.engine.core.render.ResourceLoader;
import org.gameEngine.engine.core.render.Util;
import org.gameEngine.engine.core.render.Vertex;
import org.gameEngine.engine.core.shaders.Material;
import org.gameEngine.engine.core.shaders.PhongShader;
import org.gameEngine.engine.core.shaders.Shader;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Game {

	protected Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Camera camera;
	private double temp;

	public Game( ) {
		mesh = new Mesh( );//ResourceLoader.loadMesh( "cube.obj" );
		shader = new PhongShader( );
		transform = new Transform( );
		camera = new Camera( );

		shader.setAmbientLight( new Vector3f( 1.0f, 1.0f, 1.0f ) );

		Transform.setProjection( 70.0f, StartGame.WIDTH, StartGame.HEIGHT, 0.1f, 1000.0f );
		transform.setCamera( camera );
		transform.material =
				Material.BuildMaterial( ResourceLoader.loadTexture( "grids.png" ), new Vector3f( 1.0f, 1.0f, 1.0f ) );

		Vertex[] vertices = new Vertex[] {//position, texture coords, normals.
				new Vertex( new Vector3f( -1.0f, -1.0f, 0.0f ), new Vector2f( 0.0f, 0.0f ), Vector3f.ZERO ),
				new Vertex( new Vector3f( 0.25f, 2.0f, 0.25f ), new Vector2f( 0.5f, 0.0f ), Vector3f.ZERO ),
				new Vertex( new Vector3f( 1.0f, -1.0f, 0.0f ), new Vector2f( 1.0f, 0.0f ), Vector3f.ZERO ),
				new Vertex( new Vector3f( 0.0f, -1.0f, 1.0f ), new Vector2f( 0.5f, 1.0f ), Vector3f.ZERO ) };
		int[] indices = new int[] {
				3, 1, 0,
				2, 1, 3,
				0, 1, 2,
				0, 2, 3 };
		mesh.addVertices( vertices, indices );
	}

	public void UpdateInput( ) {
		camera.input( );
	}

	public void Update( ) {
		temp += ( double ) Time.GetDeltaTime( ) / ( double ) ( Time.SECOND * 10 );

		transform.setTranslation( ( float ) Math.sin( temp ), 0.0f, 5.0f );
		transform.setRotation( 0.0f, ( float ) Math.sin( temp ) * 360, 0.0f );
		transform.setScale( 0.5f, 0.5f, 0.5f );
	}

	public void Render( ) {
		Util.setClearColor( new Vector3f( 0.0f, 0.0f, 0.0f ) );
		shader.Bind( );
		shader.updateUniforms( transform );
		mesh.draw( );
	}
}
