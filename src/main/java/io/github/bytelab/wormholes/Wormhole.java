package io.github.bytelab.wormholes;

import io.github.bytelab.wormholes.destination.WormholeDestination;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.UUID;

public class Wormhole {

    private final UUID uuid;
    private Vector position;
    private World world;
    private String name;

    private WormholeDestination destination;

    public Wormhole(Vector position, World world, String name, WormholeDestination destination) {
        this.position = position;
        this.world = world;
        this.name = name;
        this.destination = destination;
        this.uuid = UUID.randomUUID();
    }

    public Wormhole(Vector position, World world, String name, WormholeDestination destination, UUID uuid) {
        this.position = position;
        this.world = world;
        this.name = name;
        this.destination = destination;
        this.uuid = uuid;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WormholeDestination getDestination() {
        return destination;
    }

    public void setDestination(WormholeDestination destination) {
        this.destination = destination;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void updateInWorld() {
        //TODO: Spawn particles etc.
    }
}
