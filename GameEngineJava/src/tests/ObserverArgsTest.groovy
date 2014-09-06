import org.gameEngine.engine.core.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by TekMaTek on 29/07/2014.
 */

@RunWith( JUnit4.class )
public class ObserverArgsTest extends groovy.util.GroovyTestCase {

    @Test
    void testArguments() {

        ObserverSubject testObservable = new ObserverSubject(  )
        Observer testObserver = new Observer( ) {
            @Override
            void update( Observable o, Object arg ) {
                ObserverArgs args = (ObserverArgs)arg;
                assertEquals( 23 ,args.GetArg( "int" ) )
                assertEquals( "hello world" ,args.GetArg( "string" ) )
                assertEquals( 2.0f ,args.GetArg( "float" ) )
                assertEquals( 3.0d ,args.GetArg( "double" ) )
                assertEquals( testObject ,args.GetArg( "object" ) )
                assertEquals( 1.byteValue(  ) ,args.GetArg( "byte" ) )
                assertEquals( 5.shortValue(  ) ,args.GetArg( "short" ) )
                assertEquals( 4.longValue(  ) ,args.GetArg( "long" ) )
                assertEquals( true ,args.GetArg( "boolean" ) )
                assertEquals( 'a' ,args.GetArg( "char" ) )
            }
        }
        Object testObject = this
        testObservable.addObserver( testObserver )
        ObserverArgs args = new ObserverArgs( )
        args.AddArg( "int", 23 )
        args.AddArg( "string", "hello world" )
        args.AddArg( "float", 2.0f )
        args.AddArg( "double", 3.0d )
        args.AddArg( "object", testObject )
        args.AddArg( "byte", 1.byteValue(  ) )
        args.AddArg( "short", 5.shortValue(  ) )
        args.AddArg( "long", 4.longValue(  ) )
        args.AddArg( "boolean", true )
        args.AddArg( "char", 'a' )

        testObservable.setChanged( )
        testObservable.notifyObservers( args )

    }

}