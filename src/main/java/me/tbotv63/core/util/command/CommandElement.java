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

import io.github.bytelab.wormholes.exception.InsufficientPermissionException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandElement extends Iterable<CommandElement> {

    /**
     * Add an element to the {@link me.tbotv63.core.util.command.CommandElement}
     *
     * @param element The element to add
     *
     * @return wether the elements changed
     */
    boolean add(CommandElement element);

    /**
     * Remove an element from the {@link me.tbotv63.core.util.command.CommandElement}
     *
     * @param element The element to remove
     *
     * @return wether the elements changed
     */
    boolean remove(CommandElement element);

    /**
     * Get all aliases, including the primary alias
     *
     * @return All aliases
     */
    String[] getAliases();

    /**
     * @return An array containing all param types this {@link me.tbotv63.core.util.command.CommandElement} takes in order.
     *
     * @see ArgumentType
     */
    Specification getArguments();

    boolean execute(CommandSender commandSender, Command command, String s, String[] args) throws InsufficientPermissionException;


}
