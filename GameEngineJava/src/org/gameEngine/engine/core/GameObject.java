package org.gameEngine.engine.core;

import java.util.ArrayList;

/**
 * Created by TekMaTek on 01/02/2015.
 */
public class GameObject {

	public Transform transform;
	private ArrayList< GameObject > children;
	private ArrayList< GameComponent > components;

	public GameObject( ) {
		children = new ArrayList< GameObject >( );
		components = new ArrayList< GameComponent >( );
		transform = new Transform( );
	}

	public void addChild( GameObject child ) {
		children.add( child );
	}

	public void addComponent( GameComponent component ) {
		component.transform = transform;//Leave a reference back to transform.
		components.add( component );
	}

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

	public void render( ) {
		for( GameComponent component : components ) {
			component.render( );
		}

		for( GameObject child : children ) {
			child.render( );
		}
	}
}
