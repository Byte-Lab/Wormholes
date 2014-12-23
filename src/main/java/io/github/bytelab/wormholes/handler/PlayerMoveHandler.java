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
package io.github.bytelab.wormholes.handler;

import io.github.bytelab.wormholes.Main;
import io.github.bytelab.wormholes.TeleportManager;
import io.github.bytelab.wormholes.Wormhole;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class PlayerMoveHandler implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerMove(PlayerMoveEvent event) {

        if (
          event.getFrom().getBlockX() == event.getTo().getBlockX()
            && event.getFrom().getBlockY() == event.getTo().getBlockY()
            && event.getFrom().getBlockZ() == event.getTo().getBlockZ()
          ) {
            return;
        }

        new BoundaryChecker(event.getPlayer()).runTaskAsynchronously(Main.getInstance());
    }

    private class BoundaryChecker extends BukkitRunnable {

        private final Player player;

        private BoundaryChecker(Player player) {
            this.player = player;
        }

        public void run() {

            Vector playerPosition = this.player.getLocation().toVector();
            for (Wormhole wormhole : Main.getInstance().getWormholes()) {
                if (wormhole.getTeleportArea().contains(playerPosition)) {
                    TeleportManager.getInstance().scheduleTeleport(this.player, wormhole);
                    break;
                }
            }
        }
    }

}
