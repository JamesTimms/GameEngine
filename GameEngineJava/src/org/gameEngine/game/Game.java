package org.gameEngine.game;

import org.gameEngine.engine.core.Camera;
import org.gameEngine.engine.core.Shader;
import org.gameEngine.engine.core.Time;
import org.gameEngine.engine.core.Transform;
import org.gameEngine.engine.core.maths.Vector2f;
import org.gameEngine.engine.core.maths.Vector3f;
import org.gameEngine.engine.core.render.*;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Game {

	protected Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Camera camera;
	private Texture texture;
	private double temp;

	public Game( ) {
		mesh = new Mesh( );//ResourceLoader.loadMesh( "cube.obj" );
		shader = new Shader( );
		transform = new Transform( );
		camera = new Camera( );
		texture = ResourceLoader.loadTexture( "grids.png" );

		Transform.setProjection( 70.0f, StartGame.WIDTH, StartGame.HEIGHT, 0.1f, 1000.0f );
		transform.setCamera( camera );

		Vertex[] vertices = new Vertex[] {
				new Vertex( new Vector3f( -1, -1, 0 ), new Vector2f( 0.0f, 0.0f ) ),
				new Vertex( new Vector3f( 0, 1, 0 ), new Vector2f( 0.5f, 0.0f ) ),
				new Vertex( new Vector3f( 1, -1, 0 ), new Vector2f( 1.0f, 0.0f ) ),
				new Vertex( new Vector3f( 0, -1, 1 ), new Vector2f( 0.5f, 1.0f ) ) };
		int[] indices = new int[] {
				3, 1, 0,
				2, 1, 3,
				0, 1, 2,
				0, 2, 3 };
		mesh.addVertices( vertices, indices );

		shader.addVertextShader( ResourceLoader.LoadShader( "basicVertex.vertex" ) );
		shader.addFragmentShader( ResourceLoader.LoadShader( "basicFragment.fragment" ) );
		shader.CompileShader( );

		shader.addUniform( "transform" );
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
		Util.setClearColor( new Vector3f( 0.4f, 0.4f, 1.0f ) );
		shader.Bind( );

		shader.setUniform4m( "transform", transform.getProjectedTransformation( ) );
		texture.bind( );
		mesh.draw( );
	}
}
