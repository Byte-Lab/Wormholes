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

import me.tbotv63.core.util.container.Element;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.util.UUID;

public interface Destination extends Element<Destination> {

    /**
     * @param entity The {@link org.bukkit.entity.Entity} attempting to use the portal
     *
     * @return the position to send it.
     */
    Vector getPosition(Entity entity);


    World getWorld(Entity entity);

    /**
     * @return The {@link Destination}'s UUID
     */
    UUID getUuid();

    @Override
    Destination getValue();

    String getType();
}
