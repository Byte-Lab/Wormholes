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

import me.tbotv63.core.util.container.NamedContainer;
import me.tbotv63.core.util.container.NamedElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class NamedContainerImpl<T> implements NamedContainer<T> {

    protected final List<NamedElement<T>> itemList = new ArrayList<>();

    @Override
    public Iterator<T> iterator() {

        return new Iter();
    }

    @Override
    public boolean put(T value, UUID uuid) {

        return put(value, null, null, uuid);
    }

    @Override
    public T get(UUID uuid) {

        for (NamedElement<T> element : itemList) {
            if (element.getUuid().equals(uuid)) { return element.getValue(); }
        }
        return null;
    }

    @Override
    public boolean remove(UUID uuid) {

        for (int i = 0; i < itemList.size(); i++) {
            NamedElement<T> element = itemList.get(i);
            if (element.getUuid().equals(uuid)) { return itemList.remove(element); }
        }
        return false;
    }

    @Override
    public boolean put(T value, String prefix, String name, UUID uuid) {

        return itemList.add(new Element(uuid, prefix, name, value));
    }

    @Override
    public boolean canRead() {

        return true;
    }

    @Override
    public boolean put(T value, String prefix, String name) {

        return put(value, prefix, name, UUID.randomUUID());
    }

    @Override
    public boolean canWrite() {

        return true;
    }

    @Override
    public void clear() {

        itemList.clear();
    }

    @Override
    public boolean put(T value, String prefixedName) {

        String[] substrings = prefixedName.split(":");
        if (substrings.length != 2) { throw new IllegalArgumentException("Invalid name format: " + prefixedName); }
        return put(value, substrings[0], substrings[1]);
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
    }

    @Override
    public T get(String prefixedName) {

        String[] substrings = prefixedName.split(":");
        if (substrings.length != 2) { throw new IllegalArgumentException("Invalid name format: " + prefixedName); }
        return get(substrings[0], substrings[1]);
    }

    private class Element implements NamedElement<T> {

        private final UUID uuid;

        private final String prefix;

        private String name;

        private final T value;

        private Element(UUID uuid, String prefix, String name, T value) {
            this.uuid = uuid;
            this.prefix = prefix;
            this.name = name;
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

        @Override
        public String getPrefix() {

            return prefix;
        }

        @Override
        public String getName() {

            return name;
        }

        @Override
        public void setName(String name) {

            this.name = name;
        }
    }

    @Override
    public T get(String prefix, String name) {

        for (NamedElement<T> element : itemList) {
            if (element.getPrefix().equals(prefix) && element.getName().equals(name)) { return element.getValue(); }
        }
        return null;
    }


    @Override
    public boolean remove(String prefixedName) {

        String[] substrings = prefixedName.split(":");
        if (substrings.length != 2) { throw new IllegalArgumentException("Invalid name format: " + prefixedName); }
        return remove(substrings[0], substrings[1]);
    }

    @Override
    public boolean remove(String prefix, String name) {

        for (int i = 0; i < itemList.size(); i++) {
            NamedElement<T> element = itemList.get(i);
            if (element.getPrefix().equals(prefix) && element.getName().equals(name)) { return itemList.remove(element); }
        }
        return false;
    }

    @Override
    public boolean contains(String prefixedName) {

        return get(prefixedName) != null;
    }

    @Override
    public boolean contains(String prefix, String name) {

        return get(prefix, name) != null;
    }


    @Override
    public boolean remove(T value) {

        for (int i = 0; i < itemList.size(); i++) {
            NamedElement<T> element = itemList.get(i);
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

        for (NamedElement<T> element : itemList) {
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


}
