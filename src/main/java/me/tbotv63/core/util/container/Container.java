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
package me.tbotv63.core.util.container;

import java.util.UUID;

public interface Container<T> extends Iterable<T> {

    /**
     * Adds a value to the {@link Container}.
     *
     * @param value The value to add
     * @param uuid  The value's UUID
     *
     * @return Wether the {@link Container} changed
     *
     * @throws UnsupportedOperationException May be thrown if the {@link Container}
     *                                       is final and doesn't support removing items.
     */
    boolean put(T value, UUID uuid);

    /**
     * Gets a value from the {@link Container} by it's UUID.
     *
     * @param uuid The value's UUID
     *
     * @return The element associated witht that UUID or null.
     */
    T get(UUID uuid);

    /**
     * Removes a value from the {@link Container} by it's UUID.
     *
     * @param uuid The value's UUID
     *
     * @return Wether the {@link Container} changed
     *
     * @throws UnsupportedOperationException May be thrown if the {@link Container}
     *                                       is final and doesn't support removing items.
     */
    boolean remove(UUID uuid);

    /**
     * Removes an value from the {@link Container}.
     *
     * @param value The value to remove
     *
     * @return Wether the {@link Container} changed
     *
     * @throws UnsupportedOperationException May be thrown if the {@link Container}
     *                                       is final and doesn't support removing items.
     */
    boolean remove(T value);

    /**
     * Determines wether the {@link Container} contains a UUID
     *
     * @param uuid The value's UUID
     *
     * @return Wether the {@link Container} contains the given UUID
     */
    boolean contains(UUID uuid);

    /**
     * Determines wether the {@link Container} contains a value
     *
     * @param value The value to check for
     *
     * @return Wether the {@link Container} contains the given value
     */
    boolean contains(T value);

    /**
     * Get an element at a specific location
     *
     * @param i The location to get the element from
     *
     * @return The element retrieved or null.
     */
    T get(int i);

    public int size();

    /**
     * @return Wether the {@link Container} can be read.
     */
    boolean canRead();

    /**
     * @return Wether the {@link Container} can be written.
     */
    boolean canWrite();

    /**
     * Clears the {@link Container}.
     *
     * @throws UnsupportedOperationException May be thrown if the {@link Container}
     *                                       is final and doesn't support removing items.
     */
    void clear();
}
