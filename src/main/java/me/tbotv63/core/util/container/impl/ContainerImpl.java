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
package me.tbotv63.core.util.container.impl;

import me.tbotv63.core.util.container.Container;
import me.tbotv63.core.util.container.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class ContainerImpl<T> implements Container<T> {

    protected final List<Element<T>> itemList = new ArrayList<>();

    @Override
    public Iterator<T> iterator() {

        return new Iter();
    }

    @Override
    public boolean put(T value, UUID uuid) {

        return itemList.add(new ElementImpl(uuid, value));
    }

    @Override
    public T get(UUID uuid) {

        for (Element<T> element : itemList) {
            if (element.getUuid().equals(uuid)) { return element.getValue(); }
        }
        return null;
    }

    @Override
    public boolean remove(UUID uuid) {

        for (int i = 0; i < itemList.size(); i++) {
            Element<T> element = itemList.get(i);
            if (element.getUuid().equals(uuid)) { return itemList.remove(element); }
        }
        return false;
    }

    @Override
    public boolean remove(T value) {

        for (int i = 0; i < itemList.size(); i++) {
            Element<T> element = itemList.get(i);
            if (element.getValue() == element) { return itemList.remove(element); }
        }
        return false;
    }

    @Override
    public boolean contains(UUID uuid) {

        return get(uuid) != null;
    }

    @Override
    public boolean contains(T value) {

        for (Element<T> element : itemList) {
            if (element.getValue() == element) { return true; }
        }
        return false;
    }

    @Override
    public T get(int i) {

        return itemList.get(i).getValue();
    }

    @Override
    public int size() {

        return itemList.size();
    }

    @Override
    public boolean canRead() {

        return true;
    }

    @Override
    public boolean canWrite() {

        return true;
    }

    @Override
    public void clear() {

        itemList.clear();
    }

    private class Iter implements Iterator<T> {

        private int counter = 0;

        @Override
        public boolean hasNext() {

            return counter < itemList.size();
        }

        @Override
        public T next() {

            return itemList.get(counter++).getValue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class ElementImpl implements Element<T> {

        private final UUID uuid;

        private final T value;

        private ElementImpl(UUID uuid, T value) {
            this.uuid = uuid;
            this.value = value;
        }

        @Override
        public UUID getUuid() {

            return uuid;
        }

        @Override
        public T getValue() {

            return value;
        }
    }
}
