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
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TeleportManager {

    private static final long serialVersionUID = 1;

    private final Map<Player, Wormhole> playerWormholeMap = new ConcurrentHashMap<Player, Wormhole>();

    private static final TeleportManager instance = new TeleportManager();

    public static TeleportManager getInstance() {

        return instance;
    }

    public void scheduleTeleport(Player player, Wormhole wormhole) {

        this.playerWormholeMap.put(player, wormhole);
    }

    public void init() {

        new TeleportTask().runTaskTimer(Main.getInstance(), 5L, 5L);
    }

    private class TeleportTask
      extends BukkitRunnable {

        private TeleportTask() {}

        public void run() {

            for (Map.Entry<Player, Wormhole> entry : TeleportManager.this.playerWormholeMap.entrySet()) {
                Player player = entry.getKey();
                Wormhole wormhole = entry.getValue();
                Destination destination = wormhole.getDestination();
                Vector position = destination != null ? destination.getPosition(player) : player.getLocation().toVector();
                World world = destination != null ? destination.getWorld(player) : player.getLocation().getWorld();

                System.out.println("W: " + wormhole);
                System.out.println("D: " + destination);

                ParticleSpawner.enterWormhole(wormhole);
                SoundSpawner.enterWormhole(player);
                player.teleport(new Location(world, position.getX(), position.getY(), position.getZ()));
            }
            TeleportManager.this.playerWormholeMap.clear();
        }
    }
}
