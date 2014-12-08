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

public class SoundGenerator {

    public static void idleWormhole(Wormhole wormhole) {

        if (Config.playSounds) {
            Sound sound;

            if (Config.soundType.equals("vacuum")) { sound = Sound.ZOMBIE_UNFECT; }
            else if (Config.soundType.equals("vacuum2")) {sound = Sound.BLAZE_BREATH;}
            else if (Config.soundType.equals("wind")) {sound = Sound.AMBIENCE_THUNDER;}
            else if (Config.soundType.equals("ominous")) {sound = Sound.AMBIENCE_CAVE;}
            else { sound = Sound.ZOMBIE_UNFECT; }

            wormhole.getWorld().playSound(
              wormhole.getPosition()
                .toLocation(wormhole.getWorld()), sound, 2, 0.1F
            );
        }
    }

    public static void enterWormhole(Wormhole wormhole) {
        wormhole.getWorld().playSound(
          wormhole.getPosition()
            .toLocation(wormhole.getWorld()), Sound.ENDERMAN_TELEPORT, 2, 2
        );
    }

}
