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
package io.github.bytelab.wormholes;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class WormholeManager implements Collection<Wormhole> {

    private static WormholeManager instance = new WormholeManager();
    private Map<UUID, Wormhole> uuidWormholeMap = new HashMap<UUID, Wormhole>();
    private Map<String, UUID> nameUUIDMap = new HashMap<String, UUID>();

    public static WormholeManager getInstance() {
        return instance;
    }

    private WormholeManager() {}

    public void init() {
        (new UpdateTask()).runTaskTimer(Main.getInstance(), 0, 2);
    }

    public Wormhole get(String name) {
        return uuidWormholeMap.get(nameUUIDMap.get(name));
    }

    public Wormhole get(UUID uuid) {
        return uuidWormholeMap.get(uuid);
    }


    @Override
    public int size() {
        return uuidWormholeMap.size();
    }

    @Override
    public boolean isEmpty() {
        return uuidWormholeMap.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return uuidWormholeMap.values().contains(o);
    }

    @Override
    public Iterator<Wormhole> iterator() {
        return uuidWormholeMap.values().iterator();
    }

    @Override
    public Object[] toArray() {
        return uuidWormholeMap.values().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return uuidWormholeMap.values().toArray(a);
    }

    @Override
    public boolean add(Wormhole wormhole) {
        boolean changed = uuidWormholeMap.get(wormhole.getUuid()) != wormhole;

        uuidWormholeMap.put(wormhole.getUuid(), wormhole);
        nameUUIDMap.put(wormhole.getName(), wormhole.getUuid());

        return changed;
    }

    @Override
    public boolean remove(Object o) {
        if (! (o instanceof Wormhole)) { return false; }

        boolean changed;
        Wormhole wormhole = (Wormhole) o;

        changed = uuidWormholeMap.remove(wormhole.getUuid(), wormhole);
        changed = nameUUIDMap.remove(wormhole.getName(), wormhole.getUuid()) || changed;

        return changed;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return uuidWormholeMap.values().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Wormhole> c) {
        boolean changed = false;

        for (Wormhole wormhole : c) {
            changed = add(wormhole) || changed;
        }

        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;

        for (Object o : c) {
            changed = remove(o) || changed;
        }

        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;

        for (Object o : c) {
            if (! contains(o)) { changed = remove(o) || changed; }
        }

        return changed;
    }

    @Override
    public void clear() {
        uuidWormholeMap.clear();
        nameUUIDMap.clear();
    }

    //Update Task

    private class UpdateTask extends BukkitRunnable {

        @Override
        public void run() {
            //Iterate through all wormholes and perform update.

            for (Wormhole wormhole : getInstance()) {
                wormhole.updateInWorld();
            }
        }
    }


}
