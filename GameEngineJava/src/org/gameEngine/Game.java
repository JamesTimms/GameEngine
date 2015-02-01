package org.gameEngine;

import org.gameEngine.engine.core.Camera;
import org.gameEngine.engine.core.Time;
import org.gameEngine.engine.core.Transform;
import org.gameEngine.engine.physics.maths.Vector2f;
import org.gameEngine.engine.physics.maths.Vector3f;
import org.gameEngine.engine.rendering.*;
import org.gameEngine.engine.rendering.lighting.Attenuation;
import org.gameEngine.engine.rendering.lighting.BaseLight;
import org.gameEngine.engine.rendering.lighting.PointLight;
import org.gameEngine.engine.rendering.lighting.SpotLight;
import org.gameEngine.engine.rendering.shaders.Material;
import org.gameEngine.engine.rendering.shaders.PhongShader2;
import org.gameEngine.engine.rendering.shaders.Shader;

/**
 * Created by TekMaTek on 21/03/2014.
 */
public class Game {

	protected Mesh mesh;
	PointLight pointLight1 = PointLight.BuildPointLight(
			BaseLight.BuildBaseLight( new Vector3f( 1.0f, 0.5f, 0.0f ), 0.8f ),
			Attenuation.BuildAttenutation( 0.0f, 0.0f, 0.1f ),
			new Vector3f( -2.0f, 0.0f, 5.0f ), 30.0f );
	SpotLight spotLight = SpotLight.BuildSpotLight( pointLight1, Vector3f.ONE, 0.99f );
	private Shader shader;
	private Transform transform;
	private Camera camera;
	private double temp;

	public Game( ) {
		shader = new PhongShader2( );
		transform = new Transform( );
		camera = new Camera( );

		Transform.setProjection( 70.0f, StartGame.WIDTH, StartGame.HEIGHT, 0.1f, 1000.0f );
		transform.setCamera( camera );
		transform.material =
				Material.BuildMaterial( Texture.loadTexture( "grids.png" ),
										new Vector3f( 1.0f, 1.0f, 1.0f ), 1.0f, 8.0f );

		initMesh( );
		initLights( );
	}

	private void initLights( ) {
		PointLight pointLight2 = PointLight.BuildPointLight(
				BaseLight.BuildBaseLight( new Vector3f( 0.0f, 0.5f, 1.0f ), 2.8f ),
				Attenuation.BuildAttenutation( 0.0f, 0.0f, 1.0f ),
				new Vector3f( 2.0f, 0.0f, 6.0f ), 7.0f );
		PhongShader2.pointLights = new PointLight[] {
				pointLight2
		};
		PhongShader2.spotLights = new SpotLight[] {
				spotLight
		};
	}

	private void initMesh( ) {
		final float FIELD_DEPTH = 10.0f;
		final float FIELD_WIDTH = 10.0f;
		Vertex[] vertices = new Vertex[] {
				new Vertex(
						new Vector3f( -FIELD_WIDTH, 0.0f, -FIELD_DEPTH ),
						new Vector2f( 0.0f, 0.0f ),
						Vector3f.ZERO ),
				new Vertex(
						new Vector3f( -FIELD_WIDTH, 0.0f, FIELD_DEPTH * 3.0f ),
						new Vector2f( 0.0f, 1.0f ),
						Vector3f.ZERO ),
				new Vertex(
						new Vector3f( FIELD_WIDTH * 3.0f, 0.0f, -FIELD_DEPTH ),
						new Vector2f( 1.0f, 0.0f ),
						Vector3f.ZERO ),
				new Vertex(
						new Vector3f( FIELD_WIDTH * 3.0f, 0.0f, FIELD_DEPTH * 3.0f ),
						new Vector2f( 1.0f, 1.0f ),
						Vector3f.ZERO )
		};

		int indices[] = {
				0, 1, 2,
				2, 1, 3
		};

		mesh = new Mesh( vertices, indices, true );
	}

	public void UpdateInput( ) {
		camera.input( );
	}

	public void Update( ) {
		temp += ( double ) Time.GetDeltaTime( ) / ( double ) ( Time.SECOND * 10 );

		transform.setTranslation( ( float ) Math.sin( temp ), -2.0f, 5.0f );
		transform.setScale( 0.5f, 0.5f, 0.5f );

		spotLight.pointLight.position = camera.getPos( );
		spotLight.direction = camera.getForward( ).normalized();
	}

	public void Render( ) {
		Util.setClearColor( new Vector3f( 0.0f, 0.0f, 0.0f ) );
		shader.Bind( );
		shader.updateUniforms( transform );
		mesh.draw( );
	}
}
