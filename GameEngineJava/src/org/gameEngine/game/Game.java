package org.gameEngine.game;

import org.gameEngine.engine.core.*;
import org.gameEngine.engine.core.input.Inputs;
import org.gameEngine.engine.core.render.Mesh;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Game {

	protected Inputs input;
	protected Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Camera camera;
	private double temp;

	public Game( Inputs input ) {
		this.input = input;
		mesh = ResourceLoader.loadMesh( "cube.obj" );
		shader = new Shader( );
		transform = new Transform( );
		camera = new Camera( );

		Transform.setProjection( 70.0f, StartGame.WIDTH, StartGame.HEIGHT, 0.1f, 1000.0f );
		transform.setCamera( camera );

//		Vertex[] vertices = new Vertex[] {
//				new Vertex( new Vector3f( -1, -1, 0 ) ),
//				new Vertex( new Vector3f( 1, -1, 0 ) ),
//				new Vertex( new Vector3f( 0, 1, 0 ) ),
//				new Vertex( new Vector3f( 0, -1, 1 ) ) };
//		int[] indices = new int[] {
//				0, 1, 3,
//				3, 1, 2,
//				2, 1, 0,
//				0, 2, 3 };
//		mesh.AddVertices( vertices, indices );

		shader.addVertextShader( ResourceLoader.LoadShader( "basicVertex.vertex" ) );
		shader.addFragmentShader( ResourceLoader.LoadShader( "basicFragment.fragment" ) );
		shader.CompileShader( );

		shader.addUniform( "transform" );
	}

	public void UpdateInput( ) {

	}

	public void Update( ) {
		temp += ( double ) Time.GetDeltaTime( ) / ( double ) ( Time.SECOND * 10 );

		transform.setTranslation( ( float ) Math.sin( temp ), 0.0f, 5.0f );
		transform.setRotation( 0.0f, ( float ) Math.sin( temp ) * 360, 0.0f );
		transform.setScale( 0.5f, 0.5f, 0.5f );
	}

	public void Render( ) {
		shader.Bind( );
		shader.setUniform4m( "transform", transform.getProjectedTransformation( ) );

		mesh.Draw( );
	}
}
