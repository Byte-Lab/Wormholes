package io.github.bytelab.wormholes.destination;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public interface WormholeDestination {

    /**
     * @param entity The entity attempting to use the portal
     *
     * @return the position to send it.
     */
    public Vector getPosition(Entity entity);


}
