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
import org.gameEngine.engine.core.render.lighting.Attenuation;
import org.gameEngine.engine.core.render.lighting.BaseLight;
import org.gameEngine.engine.core.render.lighting.PointLight;
import org.gameEngine.engine.core.shaders.Material;
import org.gameEngine.engine.core.shaders.PhongShader2;
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
		shader = new PhongShader2( );
		transform = new Transform( );
		camera = new Camera( );

//		PhongShader.directionalLight = DirectionalLight.BuildDirectionalLight(
//				BaseLight.WhiteLight( ), Vector3f.ONE );

		Transform.setProjection( 70.0f, StartGame.WIDTH, StartGame.HEIGHT, 0.1f, 1000.0f );
		transform.setCamera( camera );
		transform.material =
				Material.BuildMaterial( ResourceLoader.loadTexture( "grids.png" ),
										new Vector3f( 1.0f, 1.0f, 1.0f ), 1.0f, 8.0f );

//		Vertex[] vertices = new Vertex[] {//position, texture coords, normals.
//				new Vertex( new Vector3f( -1.0f, -1.0f, 0.5773f ), new Vector2f( 0.0f, 0.0f ), Vector3f.ZERO ),
//				new Vertex( new Vector3f( 0.0f, -1.0f, -1.15475f ), new Vector2f( 0.5f, 0.0f ), Vector3f.ZERO ),
//				new Vertex( new Vector3f( 1.0f, -1.0f, 0.5773f ), new Vector2f( 1.0f, 0.0f ), Vector3f.ZERO ),
//				new Vertex( new Vector3f( 0.0f, 1.0f, 0.0f ), new Vector2f( 0.5f, 1.0f ), Vector3f.ZERO ) };
//		int[] indices = new int[] {
//				0, 3, 1,
//				1, 3, 2,
//				2, 3, 0,
//				1, 2, 0 };

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

		mesh.addVertices( vertices, indices, true );

		//TODO: Extract lighting stuff.
		PointLight pointLight1 = PointLight.BuildPointLight(
				BaseLight.BuildBaseLight( new Vector3f( 1.0f, 0.5f, 0.0f ), 0.8f ),
				Attenuation.BuildAttenutation( 0.0f, 0.0f, 1.0f ),
				new Vector3f( -2.0f, 0.0f, 5.0f ), 3.0f );

		PointLight pointLight2 = PointLight.BuildPointLight(
				BaseLight.BuildBaseLight( new Vector3f( 0.0f, 0.5f, 1.0f ), 2.8f ),
				Attenuation.BuildAttenutation( 0.0f, 0.0f, 1.0f ),
				new Vector3f( 2.0f, 0.0f, 6.0f ), 3.0f );
		PhongShader2.pointLights = new PointLight[] {
				pointLight1,
				pointLight2
		};
	}

	public void UpdateInput( ) {
		camera.input( );
	}

	public void Update( ) {
		temp += ( double ) Time.GetDeltaTime( ) / ( double ) ( Time.SECOND * 10 );

		transform.setTranslation( ( float ) Math.sin( temp ), -2.0f, 5.0f );
//		transform.setRotation( 0.0f, ( float ) Math.sin( temp ) * 360, 0.0f );
		transform.setScale( 0.5f, 0.5f, 0.5f );
	}

	public void Render( ) {
		Util.setClearColor( new Vector3f( 0.0f, 0.0f, 0.0f ) );
		shader.Bind( );
		shader.updateUniforms( transform );
		mesh.draw( );
	}
}
