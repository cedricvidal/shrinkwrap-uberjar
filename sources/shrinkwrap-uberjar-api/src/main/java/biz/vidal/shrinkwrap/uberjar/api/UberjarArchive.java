package biz.vidal.shrinkwrap.uberjar.api;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.container.LibraryContainer;
import org.jboss.shrinkwrap.api.container.ResourceContainer;

import biz.vidal.shrinkwrap.uberjar.api.container.UberjarContainer;

/**
 * Uberjar structure.
 *
 * @author Cedric Vidal
 */
public interface UberjarArchive
      extends
         Archive<UberjarArchive>,
         LibraryContainer<UberjarArchive>,
         ResourceContainer<UberjarArchive>,
         UberjarContainer<UberjarArchive>
{
}
