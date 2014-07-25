package testUtil

import org.gameEngine.engine.core.MainComponent
import org.gameEngine.engine.core.Window

/**
 * Created by TekMaTek on 23/07/2014.
 */
class SetupUtil extends MainComponent {

    private static final String TITLE = "UnitTests";

    public SetupUtil() {

        Window.createWindow( WIDTH, HEIGHT, TITLE )

        SetupUtil game = new SetupUtil( )
        game.StartGame( )
    }

    @Override
    protected void ProcessFrame() {
        game.UpdateInput( )
    }

    @Override
    protected void RenderFrame( ) {
        //Do nothing
    }

}
