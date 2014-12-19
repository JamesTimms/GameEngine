package org.gameEngine.game;

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

	public Game( Inputs input ) {
		this.input = input;
		mesh = new Mesh( );
		Vertex[ ] data = new Vertex[ ] {
				new Vertex( new Vector3f( -1, -1, 0 ) ),
				new Vertex( new Vector3f( 1, -1, 0 ) ),
				new Vertex( new Vector3f( 0, 1, 0 ) )};
		mesh.AddVertices( data );
	}

	public void UpdateInput( ) {

	}

	public void Update( ) {

	}

	public void Render( ) {
		mesh.Draw( );
	}
}
