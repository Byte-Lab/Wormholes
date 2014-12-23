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

import io.github.bytelab.wormholes.Main;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.util.Random;
import java.util.UUID;

public class WormholeDestination implements NamedDestination {

    private final UUID wormholeUuid;

    private final Random random = new Random();

    private UUID uuid;

    private int nameid = DestinationContainer.nextAnonymousId++;

    public WormholeDestination(UUID wormholeUuid) {
        this(wormholeUuid, UUID.randomUUID());
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

        try {
            return Main.getInstance().getWormholes().get(wormholeUuid).getName();
        } catch (NullPointerException e) {
            return "" + nameid;
        }
    }

    @Override
    public void setName(String name) {

        try {
            Main.getInstance().getWormholes().get(wormholeUuid).setName(name);
        } catch (NullPointerException ignored) {}
    }

    @Override
    public Vector getPosition(Entity entity) {

        try {
            Vector position = Main.getInstance().getWormholes().get(wormholeUuid).getPosition();

            position.setX((position.getX() + (random.nextInt(6) + 2) * (random.nextBoolean() ? 1 : - 1)));
            position.setY((position.getY() + (random.nextInt(6) + 2) * (random.nextBoolean() ? 1 : - 1)));
            position.setZ((position.getZ() + (random.nextInt(6) + 2) * (random.nextBoolean() ? 1 : - 1)));

            System.out.println(position);

            return Main.getInstance().getWormholes().get(wormholeUuid).getPosition();
        } catch (NullPointerException e) {
            Main.getInstance().getDestinations().removeElement(this);

            System.out.println("Using entity position.");

            return entity.getLocation().toVector();
        }
    }

    @Override
    public World getWorld(Entity entity) {

        try {
            return Main.getInstance().getWormholes().get(wormholeUuid).getWorld();
        } catch (NullPointerException e) {
            return entity.getWorld();
        }
    }

    @Override
    public UUID getUuid() {

        return uuid;
    }

    @Override
    public Destination getValue() {

        return this;
    }

    public UUID getWormholeUuid() {

        return wormholeUuid;
    }

    @Override
    public String toString() {

        return "WormholeDestination{" +
          "nameid=" + nameid +
          ", wormholeUuid=" + wormholeUuid +
          ", random=" + random +
          ", uuid=" + uuid +
          '}';
    }

    @Override
    public String getType() {

        return "type";
    }
}
