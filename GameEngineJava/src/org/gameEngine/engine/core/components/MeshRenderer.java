package org.gameEngine.engine.core.components;

import org.gameEngine.engine.rendering.Mesh;
import org.gameEngine.engine.rendering.shaders.Material;
import org.gameEngine.engine.rendering.shaders.Shader;

/**
 * Created by TekMaTek on 01/02/2015.
 */
public class MeshRenderer extends GameComponent {

	private Mesh mesh;
	private Material material;

	public MeshRenderer( Mesh mesh, Material material ) {
		this.mesh = mesh;
		this.material = material;
	}

	public void init( ) {

	}

	public void input( ) {

	}

	public void update( ) {

	}

	public void render( Shader shader ) {
		shader.Bind( );
		shader.updateUniforms( transform, material, Camera.mainCamera );
		mesh.draw( );
	}
}
