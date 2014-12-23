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

import io.github.bytelab.wormholes.destination.retrievers.Retriever;
import io.github.bytelab.wormholes.exception.NoSuchTypeException;
import io.github.bytelab.wormholes.exception.UnknownTypeException;
import me.tbotv63.core.util.Loadable;
import me.tbotv63.core.util.container.Element;
import me.tbotv63.core.util.container.NamedElement;
import me.tbotv63.core.util.container.directaccess.MixedDAContainer;
import me.tbotv63.core.util.container.impl.MixedContainerImpl;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DestinationContainer extends MixedContainerImpl<Destination, Destination> implements Loadable, MixedDAContainer<Destination, Destination> {

    //Save & Load

    public static int nextAnonymousId = 0;

    private List<Retriever> retrievers = new ArrayList<Retriever>();

    public void load(ConfigurationSection section) {

        for (String key : section.getKeys(false)) {
            Destination destination = loadDestination(section.getConfigurationSection(key));
            putElement(destination);
        }

    }

    Destination loadDestination(ConfigurationSection section) {

        if (section.getString("type").equals("fixed")) { return loadFixedDestination(section); }
        if (section.getString("type").equals("wormhole")) { return loadWormholeDestination(section); }

        throw new NoSuchTypeException();
    }

    private Destination loadFixedDestination(ConfigurationSection section) {

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

    private Destination loadWormholeDestination(ConfigurationSection section) {

        return new WormholeDestination(
          UUID.fromString(section.getString("wormholeUuid")),
          UUID.fromString(section.getString("uuid"))
        );

    }

    public void save(ConfigurationSection section) {

        int id = 0;
        for (Destination destination : this) {
            saveDestination(
              section.createSection(destination instanceof NamedDestination ? ((NamedDestination) destination).getName() : "" + id++),
              destination
            );
        }
    }

    void saveDestination(ConfigurationSection section, Destination destination) {

        if (destination instanceof FixedDestination) {
            saveFixedDestination(section, (FixedDestination) destination);
            return;
        }
        if (destination instanceof WormholeDestination) {
            saveWormholeDestination(section, (WormholeDestination) destination);
            return;
        }

        throw new UnknownTypeException(destination.getClass().toGenericString());
    }

    private void saveFixedDestination(ConfigurationSection section, FixedDestination destination) {

        section.set("posX", destination.getPosition().getX());
        section.set("posY", destination.getPosition().getY());
        section.set("posZ", destination.getPosition().getZ());
        section.set("worldUuid", destination.getWorld().getUID().toString());
        section.set("uuid", destination.getUuid().toString());
        section.set("type", "fixed");

    }

    private void saveWormholeDestination(ConfigurationSection section, WormholeDestination destination) {

        section.set("wormholeUuid", destination.getWormholeUuid().toString());
        section.set("uuid", destination.getUuid().toString());
        section.set("type", "wormhole");

    }

    @Override
    public boolean putElement(Element<Destination> element) {

        return itemList.add(element);
    }

    @Override
    public boolean removeElement(Element<Destination> element) {

        return itemList.remove(element);
    }

    @Override
    public boolean containsElement(Element<Destination> element) {

        return itemList.contains(element);
    }

    @Override
    public boolean putElement(NamedElement<Destination> element) {

        return itemList.add(element) || namedItemList.add(element);
    }

    @Override
    public boolean removeElement(NamedElement<Destination> element) {

        return itemList.remove(element) || namedItemList.remove(element);
    }

    @Override
    public boolean containsElement(NamedElement<Destination> element) {

        return itemList.contains(element);
    }

    @Override
    public Destination get(String prefixedName) {

        System.out.println(prefixedName);

        String[] substrings = prefixedName.split(":");
        if (substrings.length != 2) { throw new IllegalArgumentException("Invalid name format: " + prefixedName); }
        return get(substrings[0], substrings[1]);
    }

    @Override
    public Destination get(String prefix, String name) {

        System.out.println("Actual get started.");

        for (NamedElement<Destination> element : namedItemList) {
            if (element.getPrefix().equals(prefix) && element.getName().equals(name)) {
                return element.getValue();
            }
        }

        System.out.println("Using retrievers...");

        for (Retriever retriever : retrievers) {
            System.out.println("Retriever: " + retriever);
            Destination r = retriever.get(prefix, name);
            if (r != null) {
                putElement(r);
                return r;
            }
        }

        return null;
    }

    public void addRetriever(Retriever r) {

        retrievers.add(r);
    }

    public void removeRetriever(Retriever r) {

        retrievers.remove(r);
    }

}
