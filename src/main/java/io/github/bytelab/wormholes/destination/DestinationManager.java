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
package io.github.bytelab.wormholes.destination;

import io.github.bytelab.wormholes.exception.NoSuchTypeException;
import io.github.bytelab.wormholes.exception.UnknownTypeException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.function.Consumer;

public class DestinationManager implements Iterable<Destination> {

    private static DestinationManager instance = new DestinationManager();
    private Map<String, Map<UUID, NamedDestination>> namedDestinationMap = new HashMap<String, Map<UUID, NamedDestination>>();
    private Map<String, UUID> nameUUIDMap = new HashMap<String, UUID>();
    private Map<UUID, Destination> uuidDestinationMap = new HashMap<UUID, Destination>();
    private Map<UUID, Map<UUID, NamedDestination>> uuidMapMap = new HashMap<UUID, Map<UUID, NamedDestination>>();

    private DestinationManager() {}

    public static DestinationManager getInstance() {
        return instance;
    }

    public void add(NamedDestination destination) {
        if (! namedDestinationMap.containsKey(destination.getPrefix())) {
            namedDestinationMap.put(destination.getPrefix(), new HashMap<UUID, NamedDestination>());
        }

        namedDestinationMap.get(destination.getPrefix()).put(destination.getUuid(), destination);
        nameUUIDMap.put(destination.getName(), destination.getUuid());
        uuidMapMap.put(destination.getUuid(), namedDestinationMap.get(destination.getPrefix()));
    }

    public void add(Destination destination) {
        uuidDestinationMap.put(destination.getUuid(), destination);
    }

    public NamedDestination get(String name) {
        return uuidMapMap.get(nameUUIDMap.get(name)).get(nameUUIDMap.get(name));
    }

    public Destination get(UUID uuid) {
        return uuidDestinationMap.get(uuid);
    }


    @Override
    public Iterator<Destination> iterator() {
        return uuidDestinationMap.values().iterator();
    }


    @Override
    public void forEach(Consumer<? super Destination> action) {
        Objects.requireNonNull(action);
        for (Destination destination : this) {
            action.accept(destination);
        }
    }


    @Override
    public Spliterator<Destination> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }

    //Save & Load

    public void getDestinations(ConfigurationSection section) {
        for (String key : section.getKeys(false)) {
            add(getDestination(section.getConfigurationSection(key)));
        }

    }

    public Destination getDestination(ConfigurationSection section) {
        if (section.getString("type").equals("fixed")) { return getFixedDestination(section); }
        if (section.getString("type").equals("wormhole")) { return getWormholeDestination(section); }

        throw new NoSuchTypeException();
    }

    private Destination getFixedDestination(ConfigurationSection section) {

        return new FixedDestination(
          new Vector(
            section.getDouble("posX"),
            section.getDouble("posY"),
            section.getDouble("posZ")
          ),
          Bukkit.getWorld(UUID.fromString(section.getString("worldUuid"))),
          UUID.fromString(section.getString("uuid"))
        );

    }

    private Destination getWormholeDestination(ConfigurationSection section) {

        return new WormholeDestination(
          UUID.fromString(section.getString("wormholeUuid")),
          UUID.fromString(section.getString("uuid"))
        );

    }

    public void putDestinations(ConfigurationSection section) {
        int id = 0;
        for (Destination destination : this) {
            putDestination(
              section.createSection(destination instanceof NamedDestination ? ((NamedDestination) destination).getName() : "" + id++),
              destination
            );
        }
    }

    public void putDestination(ConfigurationSection section, Destination destination) {
        if (destination instanceof FixedDestination) {
            putFixedDestination(section, (FixedDestination) destination);
            return;
        }
        if (destination instanceof WormholeDestination) {
            putWormholeDestination(section, (WormholeDestination) destination);
            return;
        }

        throw new UnknownTypeException(destination.getClass().toGenericString());
    }

    private void putFixedDestination(ConfigurationSection section, FixedDestination destination) {

        section.set("posX", destination.getPosition().getX());
        section.set("posY", destination.getPosition().getY());
        section.set("posZ", destination.getPosition().getZ());
        section.set("worldUuid", destination.getWorld().getUID().toString());
        section.set("uuid", destination.getUuid().toString());
        section.set("type", "fixed");

    }

    private void putWormholeDestination(ConfigurationSection section, WormholeDestination destination) {

        section.set("wormholeUuid", destination.getWormholeUuid());
        section.set("uuid", destination.getUuid());
        section.set("type", "wormhole");

    }


}
