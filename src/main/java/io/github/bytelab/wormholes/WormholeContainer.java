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

import me.tbotv63.core.util.Loadable;
import me.tbotv63.core.util.container.NamedElement;
import me.tbotv63.core.util.container.directaccess.NamedDAContainer;
import me.tbotv63.core.util.container.impl.NamedContainerImpl;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

public class WormholeContainer extends NamedContainerImpl<Wormhole> implements NamedDAContainer<Wormhole>, Loadable {

    public void init() {

        (new UpdateTask()).runTaskTimer(Main.getInstance(), 0, 2);
    }

    @Override
    public void load(ConfigurationSection mainSection) {

        for (World world : Bukkit.getWorlds()) {
            ConfigurationSection whSection = mainSection.getConfigurationSection(world.getUID().toString());
            if (whSection == null) { continue; }

            Set<String> wormholes = whSection.getKeys(false);

            for (String name : wormholes) {
                ConfigurationSection section = whSection.getConfigurationSection(name);
                ConfigurationSection locationSection = section.getConfigurationSection("location");

                Wormhole wormhole = new Wormhole(
                  new Vector(
                    locationSection.getDouble("posX"),
                    locationSection.getDouble("posY"),
                    locationSection.getDouble("posZ")
                  ),
                  world,
                  name,
                  Main.getInstance().getDestinations().get(UUID.fromString(section.getString("destination"))),
                  UUID.fromString(section.getString("uuid"))
                );

                this.putElement(wormhole);

            }
        }
    }

    @Override
    public boolean putElement(NamedElement<Wormhole> element) {

        return itemList.add(element);
    }

    @Override
    public boolean removeElement(NamedElement<Wormhole> element) {

        return itemList.remove(element);
    }

    @Override
    public boolean containsElement(NamedElement<Wormhole> element) {

        return itemList.contains(element);
    }

    @Override
    public void save(ConfigurationSection whSection) {

        for (Wormhole wormhole : this) {

            ConfigurationSection section;
            if (whSection.getConfigurationSection(wormhole.getWorld().getUID().toString()) == null) {
                section = whSection
                  .createSection(wormhole.getWorld().getUID().toString())
                  .createSection(wormhole.getName());
            }
            else {
                section = whSection
                  .getConfigurationSection(wormhole.getWorld().getUID().toString())
                  .createSection(wormhole.getName());
            }

            ConfigurationSection locationSection = section.createSection("location");

            try {
                locationSection.set("posX", wormhole.getPosition().getX());
                locationSection.set("posY", wormhole.getPosition().getY());
                locationSection.set("posZ", wormhole.getPosition().getZ());
                section.set("destination", wormhole.getDestination().getUuid().toString());
                section.set("uuid", wormhole.getUuid().toString());
            } catch (NullPointerException e) {
                Logger logger = Main.getInstance().getLogger();

                logger.info("Encountered a wormhole with null references. Wormhole won't persist.");
                logger.info(wormhole.toString());
            }

        }
    }

    private class UpdateTask extends BukkitRunnable {

        @Override
        public void run() {

            for (Wormhole wormhole : WormholeContainer.this) {
                wormhole.updateInWorld();
            }
        }
    }


}
