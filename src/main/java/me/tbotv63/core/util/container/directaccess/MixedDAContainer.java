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
package me.tbotv63.core.util.container.directaccess;

import me.tbotv63.core.util.container.Element;
import me.tbotv63.core.util.container.MixedContainer;
import me.tbotv63.core.util.container.NamedElement;

public interface MixedDAContainer<B, T extends B> extends MixedContainer<B, T> {

    boolean putElement(Element<B> element);

    boolean removeElement(Element<B> element);

    boolean containsElement(Element<B> element);

    boolean putElement(NamedElement<T> element);

    boolean removeElement(NamedElement<T> element);

    boolean containsElement(NamedElement<T> element);

}
