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
