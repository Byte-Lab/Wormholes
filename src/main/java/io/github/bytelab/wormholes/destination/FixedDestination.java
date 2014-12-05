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
