package biz.vidal.shrinkwrap.uberjar.api.container;
import org.jboss.shrinkwrap.api.Archive;

/**
 * Defines the contract for a component capable of storing 
 * web-related resources.
 * <br/><br/>
 * The actual path to the Web resources within the Archive 
 * is up to the implementations/specifications.
 *
 * @author <a href="mailto:aslak@conduct.no">Aslak Knutsen</a>
 * @version $Revision: $
 * @param <T>
 */
public interface UberjarContainer<T extends Archive<T>>
{
   //-------------------------------------------------------------------------------------||
   // Contracts --------------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   T createConfiguration()  throws IllegalArgumentException;

   T setMainClass(String type) throws IllegalArgumentException;

   T setClassworlds(Archive<?> archive) throws IllegalArgumentException;

}
