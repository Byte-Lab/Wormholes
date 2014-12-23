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

public interface MixedContainer<B, T extends B> extends Container<B> {

    /**
     * Add an entry to the {@link NamedContainer}
     *
     * @param value  The value to store
     * @param prefix The entry's name prefix
     * @param name   The entry's name
     * @param uuid   The entry's UUID
     *
     * @return Wether the {@link NamedContainer} changed
     *
     * @throws UnsupportedOperationException May be thrown if the {@link NamedContainer}
     *                                       is final and doesn't support adding items.
     */
    boolean put(T value, String prefix, String name, UUID uuid);

    /**
     * Add an entry to the {@link NamedContainer}
     * <p/>
     * Due to the fact that there is no UUID provided, the manager must itsself assign one to the passed value.
     *
     * @param value  The value to store
     * @param prefix The entry's name prefix
     * @param name   The entry's name
     *
     * @return Wether the {@link NamedContainer} changed
     *
     * @throws UnsupportedOperationException May be thrown if the {@link NamedContainer}
     *                                       is final and doesn't support adding items.
     */
    boolean put(T value, String prefix, String name);

    /**
     * Add an entry to the {@link NamedContainer}
     * <p/>
     * Due to the fact that there is no UUID provided, the manager must itsself assign one to the passed value.
     *
     * @param value        The value to store
     * @param prefixedName The prefix and name as one String in the format prefix:name
     *
     * @return Wether the {@link NamedContainer} changed
     *
     * @throws IllegalArgumentException      May be thrown if a malformed name is encountered.
     * @throws UnsupportedOperationException May be thrown if the {@link NamedContainer}
     *                                       is final and doesn't support adding items.
     */
    boolean put(T value, String prefixedName);

    /**
     * Gets an element from the {@link NamedContainer} by it's prefix and name.
     *
     * @param prefixedName The prefix and name as one String in the format prefix:name
     *
     * @return The element associated with that name or null
     *
     * @throws IllegalArgumentException May be thrown if a malformed name is encountered.
     */
    T get(String prefixedName);

    /**
     * Gets an element from the {@link NamedContainer} by it's prefix and name.
     *
     * @param prefix The entry's name prefix
     * @param name   The entry's name
     *
     * @return The element associated with that name or null
     */
    T get(String prefix, String name);

    /**
     * Removes an element from the {@link NamedContainer} by it's prefix and name.
     *
     * @param prefixedName The prefix and name as one String in the format prefix:name
     *
     * @return Wether the {@link NamedContainer} changed
     *
     * @throws IllegalArgumentException      May be thrown if a malformed name is encountered.
     * @throws UnsupportedOperationException May be thrown if the {@link NamedContainer}
     *                                       is final and doesn't support removing items.
     */
    boolean remove(String prefixedName);

    /**
     * Removes an element from the {@link NamedContainer} by it's prefix and name.
     *
     * @param prefix The entry's name prefix
     * @param name   The entry's name
     *
     * @return Wether the {@link NamedContainer} changed
     *
     * @throws UnsupportedOperationException May be thrown if the {@link NamedContainer}
     *                                       is final and doesn't support removing items.
     */
    boolean remove(String prefix, String name);

    /**
     * Determines wether the {@link Container} contains a prefix-name pair
     *
     * @param prefixedName The prefix and name to check for as one String in the format prefix:name
     *
     * @return Wether the {@link Container} contains the given prefix-name pair
     */
    boolean contains(String prefixedName);

    /**
     * Determines wether the {@link Container} contains a value prefix-name pair
     *
     * @param prefix The prefix to check for
     * @param name   The name to check for
     *
     * @return Wether the {@link Container} contains the given prefix-name pair
     */
    boolean contains(String prefix, String name);
}
