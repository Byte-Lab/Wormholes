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
import org.bukkit.util.Vector;

import java.util.UUID;

public class CoordinateDestination extends FixedDestination implements NamedDestination {

    private String name;

    public CoordinateDestination(Vector position, World world, String name, UUID uuid) {
        super(position, world, uuid);
        this.name = name;
    }

    public CoordinateDestination(Vector position, World world, String name) {
        super(position, world);
        this.name = name;
    }

    @Override
    public String getPrefix() {
        return "coordinate";
    }

    @Override
    public String getName() {
        return name;
    }
}
