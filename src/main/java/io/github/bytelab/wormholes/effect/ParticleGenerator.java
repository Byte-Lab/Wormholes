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
import io.github.bytelab.wormholes.particle.ParticleEffect;

public class ParticleGenerator {

    public static void idleWormhole(Wormhole wormhole) {
        ParticleEffect.RED_DUST.display(
          0.5F, 0.5F, 0.5F, Config.useColors ? 0.1F : 0.00001F, 100, wormhole.getPosition()
            .toLocation(wormhole.getWorld(), 0, 0), 100D
        );
        ParticleEffect.RED_DUST.display(
          1.2F, 1.2F, 1.2F, 0.00001F, 1500, wormhole.getPosition()
            .toLocation(wormhole.getWorld(), 0, 0), 100D
        );
        ParticleEffect.PORTAL.display(
          1.5F, 1.5F, 1.5F, 1F, 1000, wormhole.getPosition()
            .toLocation(wormhole.getWorld(), 0, 0), 100D
        );
    }

    public static void enterWormhole(Wormhole wormhole) {
        ParticleEffect.WITCH_MAGIC.display(
          0F, 0F, 0F, 1F, 40, wormhole.getPosition()
            .toLocation(wormhole.getWorld(), 0, 0), 2D
        );
    }

}
