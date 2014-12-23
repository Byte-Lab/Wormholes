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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BaseCommand implements CommandElement {

    private final List<CommandElement> elementList = new ArrayList<>();

    protected final List<String> aliases = new ArrayList<>();

    protected Specification specification = new Specification();

    protected BaseCommand(ArgumentType... argumentTypes) {
        specification = new Specification(argumentTypes);
    }

    @Override
    public boolean add(CommandElement element) {

        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(CommandElement element) {

        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getAliases() {

        return aliases.toArray(new String[aliases.size()]);
    }

    @Override
    public Specification getArguments() {

        return specification;
    }

    @Override
    public Iterator<CommandElement> iterator() {

        return elementList.iterator();
    }
}
