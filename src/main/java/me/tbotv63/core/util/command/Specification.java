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
package me.tbotv63.core.util.command;

import java.util.Arrays;

public class Specification {

    private final ArgumentType[] types;

    public Specification(ArgumentType... types) {
        this.types = types;
    }

    public boolean compatible(Specification specification) {

        if (this.equals(specification)) { return true; }

        if (this.types.length != specification.types.length) {
            return false;
        }

        for (int i = 0; i < types.length; i++) {
            if (! specification.types[i].isCompatible(this.types[i])) { return false; }
        }

        return true;

    }

    @Override
    public boolean equals(Object obj) {

        return obj instanceof Specification && Arrays.equals(this.types, ((Specification) obj).types);

    }

    @Override
    public String toString() {

        return "Specification{" +
          "types=" + Arrays.toString(types) +
          '}';
    }
}
