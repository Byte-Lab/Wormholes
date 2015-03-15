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

import me.tbotv63.core.util.container.Element;
import me.tbotv63.core.util.container.MixedContainer;
import me.tbotv63.core.util.container.NamedElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class MixedContainerImpl<B, T extends B> implements MixedContainer<B, T> {

    protected final List<NamedElement<T>> namedItemList = new ArrayList<>();

    protected final List<Element<B>> itemList = new ArrayList<>();

    @Override
    public Iterator<B> iterator() {

        return new Iter();
    }

    @Override
    public boolean put(B value, UUID uuid) {

        return itemList.add(new ElementImpl(uuid, value));
    }

    @Override
    public B get(UUID uuid) {

        for (Element<B> element : itemList) {
            if (element.getUuid().equals(uuid)) { return element.getValue(); }
        }
        return null;
    }

    @Override
    public boolean remove(UUID uuid) {

        for (int i = 0; i < namedItemList.size(); i++) {
            NamedElement<T> element = namedItemList.get(i);
            if (element.getUuid().equals(uuid)) { return namedItemList.remove(element); }
        }
        return false;
    }

    @Override
    public boolean remove(B value) {

        return false;
    }

    @Override
    public boolean contains(UUID uuid) {

        return get(uuid) != null;
    }

    @Override
    public boolean contains(B value) {

        for (Element<B> element : itemList) {
            if (element.getValue() == element) { return true; }
        }
        return false;
    }

    @Override
    public B get(int i) {

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
    public boolean put(T value, String prefix, String name, UUID uuid) {

        namedItemList.add(new NamedElementImpl(uuid, prefix, name, value));
        return itemList.add(new ElementImpl(uuid, value));
    }

    @Override
    public boolean canWrite() {

        return true;
    }

    @Override
    public void clear() {

        itemList.clear();
        namedItemList.clear();
    }

    @Override
    public boolean put(T value, String prefix, String name) {

        return put(value, prefix, name, UUID.randomUUID());
    }

    private class Iter implements Iterator<B> {

        private int counter = 0;

        @Override
        public boolean hasNext() {

            return counter < itemList.size();
        }

        @Override
        public B next() {

            return itemList.get(counter++).getValue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class NamedElementImpl implements NamedElement<T> {

        private final UUID uuid;

        private final String prefix;

        private String name;

        private final T value;

        private NamedElementImpl(UUID uuid, String prefix, String name, T value) {
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
        public String getPrefix() {

            return prefix;
        }

        @Override
        public T getValue() {

            return value;
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

    private class ElementImpl implements Element<B> {

        private final UUID uuid;

        private final B value;

        private ElementImpl(UUID uuid, B value) {
            this.uuid = uuid;
            this.value = value;
        }

        @Override
        public UUID getUuid() {

            return uuid;
        }

        @Override
        public B getValue() {

            return value;
        }
    }

    @Override
    public boolean put(T value, String prefixedName) {

        String[] substrings = prefixedName.split(":");
        if (substrings.length != 2) { throw new IllegalArgumentException("Invalid name format: " + prefixedName); }
        return put(value, substrings[0], substrings[1]);
    }


    @Override
    public T get(String prefixedName) {

        String[] substrings = prefixedName.split(":");
        if (substrings.length != 2) { throw new IllegalArgumentException("Invalid name format: " + prefixedName); }
        return get(substrings[0], substrings[1]);
    }


    @Override
    public T get(String prefix, String name) {

        for (NamedElement<T> element : namedItemList) {
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

        for (int i = 0; i < namedItemList.size(); i++) {
            NamedElement<T> element = namedItemList.get(i);
            if (element.getPrefix().equals(name) && element.getName().equals(prefix)) { return namedItemList.remove(element); }
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


}
