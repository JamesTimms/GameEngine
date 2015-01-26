package org.gameEngine.game;

import org.gameEngine.engine.core.ResourceLoader;
import org.gameEngine.engine.core.Shader;
import org.gameEngine.engine.core.Time;
import org.gameEngine.engine.core.Transform;
import org.gameEngine.engine.core.input.Inputs;
import org.gameEngine.engine.core.maths.Vector3f;
import org.gameEngine.engine.core.render.Mesh;
import org.gameEngine.engine.core.render.Vertex;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Game {

	protected Inputs input;
	protected Mesh mesh;
	private Shader shader;
	private Transform transform;
	private double temp;

	public Game( Inputs input ) {
		this.input = input;
		mesh = new Mesh();
		shader = new Shader();
		transform = new Transform();

		Vertex[] data = new Vertex[] {
				new Vertex( new Vector3f( -1, -1, 0 ) ),
				new Vertex( new Vector3f( 1, -1, 0 ) ),
				new Vertex( new Vector3f( 0, 1, 0 ) ) };
		mesh.AddVertices( data );

		shader.addVertextShader( ResourceLoader.LoadShader( "basicVertex.vertex" ) );
		shader.addFragmentShader( ResourceLoader.LoadShader( "basicFragment.fragment" ) );
		shader.CompileShader();

		shader.addUniform( "transform" );
	}

	public void UpdateInput() {

	}

	public void Update() {
		temp += ( double ) Time.GetDeltaTime() / ( double ) Time.SECOND;

		float sinTemp = ( float ) Math.sin( Math.sin( temp ) );
		transform.setTranslation( ( float ) Math.sin( temp ), 0.0f, 0.0f );
		transform.setRotation( 0.0f, 0.0f, ( float ) Math.sin( temp ) * 360 );
		transform.setScale( sinTemp, sinTemp, sinTemp );
	}

	public void Render() {
		shader.Bind();
		shader.setUniform4m( "transform", transform.getTransformation() );

		mesh.Draw();
	}
}
