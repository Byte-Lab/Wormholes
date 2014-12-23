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
package me.tbotv63.core.util.math.geo;

import org.bukkit.util.Vector;

public class Cuboid {

    private Vector vector1;

    private Vector vector2;

    public Cuboid(Vector vector1, Vector vector2) {
        this.vector1 = new Vector(
          vector1.getX() < vector2.getX() ? vector1.getX() : vector2.getX(),
          vector1.getY() < vector2.getY() ? vector1.getY() : vector2.getY(),
          vector1.getZ() < vector2.getZ() ? vector1.getZ() : vector2.getZ()
        );

        this.vector2 = new Vector(
          vector1.getX() > vector2.getX() ? vector1.getX() : vector2.getX(),
          vector1.getY() > vector2.getY() ? vector1.getY() : vector2.getY(),
          vector1.getZ() > vector2.getZ() ? vector1.getZ() : vector2.getZ()
        );
    }

    public Vector getVector1() {

        return this.vector1;
    }

    public void setVector1(Vector vector1) {

        this.vector1 = vector1;
    }

    public Vector getVector2() {

        return this.vector2;
    }

    public void setVector2(Vector vector2) {

        this.vector2 = vector2;
    }

    public boolean contains(Vector vector) {

        return (
          vector1.getX() < vector.getX()) && (vector.getX() < vector2.getX())
          && (vector1.getY() < vector.getY()) && (vector.getY() < vector2.getY())
          && (vector1.getZ() < vector.getZ()) && (vector.getZ() < vector2.getZ()
        );
    }

    @Override
    public String toString() {

        return "Cuboid{" +
          "vector1=" + vector1 +
          ", vector2=" + vector2 +
          '}';
    }
}
