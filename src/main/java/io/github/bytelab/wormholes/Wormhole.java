/*
 * Copyright (c) 2014-2014
 * Byte-Lab <http://bytelab.pw>
 *
 * This file is part of Wormholes.
 *
 * Wormholes is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.bytelab.wormholes;

import io.github.bytelab.wormholes.destination.Destination;
import io.github.bytelab.wormholes.effect.ParticleSpawner;
import io.github.bytelab.wormholes.effect.SoundSpawner;
import io.github.bytelab.wormholes.exception.NullDerpException;
import me.tbotv63.core.util.container.NamedElement;
import me.tbotv63.core.util.math.geo.Cuboid;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.util.UUID;

public class Wormhole implements NamedElement<Wormhole> {

    private final UUID uuid;

    private Vector position;

    private World world;

    private String name;

    private Cuboid teleportArea;

    private Destination destination;

    public Wormhole(Vector position, World world, String name, Destination destination) {
        this(position, world, name, destination, UUID.randomUUID());
    }

    public Wormhole(Vector position, World world, String name, Destination destination, UUID uuid) {

        if (destination == null) { throw new NullDerpException(); }

        this.position = position;
        this.world = world;
        this.name = name;
        this.destination = destination;
        this.uuid = uuid;

        this.teleportArea = new Cuboid(
          new Vector(
            position.getX() - Config.suckRadius - 1.0D,
            position.getY() - Config.suckRadius - 1.0D,
            position.getZ() - Config.suckRadius - 1.0D
          ),
          new Vector(
            position.getX() + Config.suckRadius - 1.0D,
            position.getY() + Config.suckRadius - 1.0D,
            position.getZ() + Config.suckRadius - 1.0D
          )
        );
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

    @Override
    public String getPrefix() {

        return "wormhole";
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Destination getDestination() {

        return destination;
    }

    public void setDestination(Destination destination) {

        this.destination = destination;
    }

    public Vector getPosition(Entity entity) {

        return position;
    }

    public World getWorld(Entity entity) {

        return world;
    }

    public UUID getUuid() {

        return uuid;
    }

    @Override
    public Wormhole getValue() {

        return this;
    }

    public void updateInWorld() {

        ParticleSpawner.idleWormhole(this);
        SoundSpawner.idleWormhole(this);
    }

    public Cuboid getTeleportArea() {

        return this.teleportArea;
    }

    @Override
    public String toString() {

        return "Wormhole{" +
          "destination=" + destination +
          ", uuid=" + uuid +
          ", position=" + position +
          ", world=" + world +
          ", name='" + name + '\'' +
          ", teleportArea=" + teleportArea +
          '}';
    }
}
