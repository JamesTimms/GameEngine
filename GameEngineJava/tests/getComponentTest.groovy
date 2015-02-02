import org.gameEngine.engine.core.Camera
import org.gameEngine.engine.core.GameObject
import org.junit.Test

/**
 * Created by TekMaTek on 02/02/2015.
 */
class getComponentTest extends groovy.util.GroovyTestCase {

    @Test
    void testGetComponent() {
        GameObject newTestObject = new GameObject( )
        newTestObject.addComponent( new Camera( ) )

        Camera camera
        long startTime = System.nanoTime( )
		camera = newTestObject.getComponent( Camera.class )
//        camera = Camera.mainCamera
        System.out.println( System.nanoTime( ) - startTime )
        System.out.println( camera.getClass( ).getName( ) )
    }

    long getTimeOfAction() {

    }

}
