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
package io.github.bytelab.wormholes.destination;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.util.UUID;

public class FixedDestination implements Destination {

    private Vector position;

    private final UUID uuid;

    private final World world;

    public FixedDestination(Vector position, World world, UUID uuid) {
        this.position = position;
        this.world = world;
        this.uuid = uuid;
    }

    public FixedDestination(Vector position, World world) {
        this(position, world, UUID.randomUUID());
    }

    @Override
    public Vector getPosition(Entity entity) {

        return position;
    }

    @Override
    public World getWorld(Entity entity) {

        return world;
    }

    public World getWorld() {

        return world;
    }

    @Override
    public UUID getUuid() {

        return uuid;
    }

    @Override
    public Destination getValue() {

        return this;
    }

    public Vector getPosition() {

        return position;
    }

    public void setPosition(Vector position) {

        this.position = position;
    }

    @Override
    public String toString() {

        return "FixedDestination{" +
          "position=" + position +
          ", uuid=" + uuid +
          ", world=" + world +
          '}';
    }

    @Override
    public String getType() {

        return "fixed";
    }
}
