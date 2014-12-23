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
package io.github.bytelab.wormholes.effect;

import io.github.bytelab.wormholes.Config;
import io.github.bytelab.wormholes.Wormhole;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundSpawner {

    public static void idleWormhole(Wormhole wormhole) {

        if (Config.playSounds) {
            Sound sound;

            switch (Config.soundType) {
                case "vacuum":
                    sound = Sound.ZOMBIE_UNFECT;
                    break;
                case "vacuum2":
                    sound = Sound.BLAZE_BREATH;
                    break;
                case "wind":
                    sound = Sound.AMBIENCE_THUNDER;
                    break;
                case "ominous":
                    sound = Sound.AMBIENCE_CAVE;
                    break;
                default:
                    sound = Sound.ZOMBIE_UNFECT;
                    break;
            }

            wormhole.getWorld().playSound(
              wormhole.getPosition()
                .toLocation(wormhole.getWorld()), sound, 1, 0.1F
            );
        }
    }

    public static void enterWormhole(Player player) {

        player.getWorld().playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 2, 2);
    }

}
