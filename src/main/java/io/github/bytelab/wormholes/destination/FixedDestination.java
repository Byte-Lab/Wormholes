package io.github.bytelab.wormholes.destination;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class FixedDestination implements WormholeDestination {

    private Vector position;

    public FixedDestination(Vector position) {
        this.position = position;
    }

    @Override
    public Vector getPosition(Entity entity) {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }
}
