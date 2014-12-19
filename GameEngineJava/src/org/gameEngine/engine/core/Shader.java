package org.gameEngine.engine.core;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

/**
 * Created by TekMaTek on 19/12/2014.
 */


public class Shader {

	private int program;

	public Shader() {
		program = glCreateProgram();
		if( program == 0 ) {
			System.err.println( "Shader creation faileD: Could not find valid memory location in constructor." );
			System.exit( 1 );
		}
	}

	public void addVertextShader( String text ) {
		addProgram( text, GL_VERTEX_SHADER );
	}

	public void addGeometryShader( String text ) {
		addProgram( text, GL_GEOMETRY_SHADER );

	}

	public void addFragmentShader( String text ) {
		addProgram( text, GL_FRAGMENT_SHADER );

	}

	public void CompileShader() {
		glLinkProgram( program );

		if( glGetProgrami( program, GL_LINK_STATUS ) == 0 ) {
			System.err.println( glGetShaderInfoLog( program, 1024 ) );
			System.exit( 1 );
		}

		glValidateProgram( program );

		if( glGetProgrami( program, GL_VALIDATE_STATUS ) == 0 ) {
			System.err.println( glGetShaderInfoLog( program, 1024 ) );
			System.exit( 1 );
		}
	}

	public void Bind() {
		glUseProgram( program );
	}

	protected void addProgram( String text, int type ) {
		int shader = glCreateShader( type );

		if( shader == 0 ) {
			System.err.println( "Shader creation failed: Could not find valid memory location when adding shader." );
			System.exit( 1 );
		}

		glShaderSource( shader, text );
		glCompileShader( shader );

		if( glGetShaderi( shader, GL_COMPILE_STATUS ) == 0 ) {
			System.err.println( glGetShaderInfoLog( shader, 1024 ) );
			System.exit( 1 );
		}

		glAttachShader( program, shader );
	}

}
