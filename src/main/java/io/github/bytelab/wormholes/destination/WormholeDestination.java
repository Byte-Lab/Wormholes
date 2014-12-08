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

import io.github.bytelab.wormholes.WormholeManager;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.util.Random;
import java.util.UUID;

public class WormholeDestination implements NamedDestination {

    private UUID uuid = UUID.randomUUID();
    private UUID wormholeUuid;
    private Random random = new Random();

    public WormholeDestination(UUID wormholeUuid) {
        this.wormholeUuid = wormholeUuid;
    }

    public WormholeDestination(UUID wormholeUuid, UUID uuid) {
        this.wormholeUuid = wormholeUuid;
        this.uuid = uuid;
    }

    @Override
    public String getPrefix() {
        return "wormhole";
    }

    @Override
    public String getName() {
        return WormholeManager.getInstance().get(wormholeUuid).getName();
    }

    @Override
    public Vector getPosition(Entity entity) {
        Vector position = WormholeManager.getInstance().get(wormholeUuid).getPosition();

        position.setX((position.getX() + random.nextInt(6) + 2) * (random.nextBoolean() ? 1 : - 1));
        position.setY((position.getY() + random.nextInt(6) + 2) * (random.nextBoolean() ? 1 : - 1));
        position.setZ((position.getZ() + random.nextInt(6) + 2) * (random.nextBoolean() ? 1 : - 1));

        return WormholeManager.getInstance().get(wormholeUuid).getPosition();
    }

    @Override
    public World getWorld(Entity entity) {
        return WormholeManager.getInstance().get(wormholeUuid).getWorld();
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    public UUID getWormholeUuid() {
        return wormholeUuid;
    }
}
