package testUtil

import org.gameEngine.engine.core.MainComponent
import org.gameEngine.engine.core.Window

/**
 * Created by TekMaTek on 23/07/2014.
 */
class SetupUtil extends MainComponent {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final String TITLE = "UnitTests";

    public SetupUtil( ) {

        //Start Input Observer as separate thread.

        Window.createWindow( WIDTH, HEIGHT, TITLE );

        SetupUtil game = new SetupUtil( );
        game.StartGame( );
    }



}
