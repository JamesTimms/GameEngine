package org.gameEngine.engine.core;

import org.gameEngine.engine.rendering.shaders.Shader;

import java.util.ArrayList;

/**
 * Created by TekMaTek on 01/02/2015.
 */
public class GameObject {

	private static GameObject root = new GameObject( );

	public Transform transform;

	private ArrayList< GameObject > children;
	private ArrayList< GameComponent > components;

	public GameObject( ) {
		children = new ArrayList< GameObject >( );
		components = new ArrayList< GameComponent >( );
		transform = new Transform( );
	}

	public static GameObject getRoot( ) {
		return GameObject.root;
	}

	public void addChild( GameObject child ) {
		children.add( child );
	}

	public void addComponent( GameComponent component ) {
		component.transform = transform;//Leave a reference back to transform.
		components.add( component );
	}

//	public T getComponent< T >( T type ){
//		for( GameComponent component : components ) {
//			if( component.getClass().equals( type )) {
//				return component;
//			}
//		}
//		return null;
//	}

	public void input( ) {
		for( GameComponent component : components ) {
			component.input( );
		}

		for( GameObject child : children ) {
			child.input( );
		}
	}

	public void update( ) {
		for( GameComponent component : components ) {
			component.update( );
		}

		for( GameObject child : children ) {
			child.update( );
		}
	}

	public void render( Shader shader ) {
		for( GameComponent component : components ) {
			component.render( shader );
		}

		for( GameObject child : children ) {
			child.render( shader );
		}
	}
}
