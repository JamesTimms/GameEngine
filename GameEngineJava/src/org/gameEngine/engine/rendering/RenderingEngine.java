package org.gameEngine.engine.rendering;

import org.gameEngine.engine.core.GameObject;
import org.gameEngine.engine.physics.maths.Vector3f;

/**
 * Created by TekMaTek on 01/02/2015.
 */
public class RenderingEngine {

	public void render( GameObject someRootGO ) {
		RenderingUtil.setClearColor( new Vector3f( 0.0f, 0.0f, 0.0f ) );
		someRootGO.render( );
	}

}
