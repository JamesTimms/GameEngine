package org.gameEngine.game;

import org.gameEngine.engine.core.ResourceLoader;
import org.gameEngine.engine.core.Shader;
import org.gameEngine.engine.core.Time;
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
	float temp = 0.0f;
	private Shader shader;

	public Game( Inputs input ) {
		this.input = input;
		mesh = new Mesh();
		shader = new Shader();

		Vertex[] data = new Vertex[] {
				new Vertex( new Vector3f( -1, -1, 0 ) ),
				new Vertex( new Vector3f( 1, -1, 0 ) ),
				new Vertex( new Vector3f( 0, 1, 0 ) ) };
		mesh.AddVertices( data );

		shader.addVertextShader( ResourceLoader.LoadShader( "basicVertex.vertex" ) );
		shader.addFragmentShader( ResourceLoader.LoadShader( "basicFragment.fragment" ) );
		shader.CompileShader();

		shader.addUniform( "uniformFloat" );
	}

	public void UpdateInput() {

	}

	public void Update() {
		temp += ( float ) ( Time.GetDeltaTime() / 1000000 );
		shader.setUniformf( "uniformFloat", ( float ) Math.abs( Math.sin( temp ) ) );
	}

	public void Render() {
		shader.Bind();
		mesh.Draw();
	}
}
