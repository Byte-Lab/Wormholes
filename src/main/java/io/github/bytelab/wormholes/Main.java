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

/*
 * Permissions:
 * wormholes.create
 * wormholes.remove
 * wormholes.list
 * wormholes.options
 */
package io.github.bytelab.wormholes;

import io.github.bytelab.wormholes.destination.DestinationManager;
import io.github.bytelab.wormholes.handler.PlayerMoveHandler;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

public class Main extends JavaPlugin {


    PluginDescriptionFile descriptionFile = this.getDescription();

    Logger logger = Logger.getLogger("Wormholes");
    FileConfiguration database;
    File databaseFile;

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        pluginConfiguration();
        instance = this;

        WormholeManager.getInstance().init();
        TeleportManager.getInstance().init();

        //Register Events
        getServer().getPluginManager().registerEvents(new PlayerMoveHandler(), this);

        getCommand("wh").setExecutor(new WormholeCommand());
        getCommand("wormhole").setExecutor(new WormholeCommand());

        loadDatabase();

        logger.info(descriptionFile.getName() + " version " + descriptionFile.getVersion() + " has been enabled!");

    }

    public void onDisable() {

        saveDatabase();

        logger.info(descriptionFile.getName() + " version " + descriptionFile.getVersion() + " has been disabled!");
    }

    public void pluginConfiguration() {
        FileConfiguration config = getConfig();

        config.addDefault("config", 1);
        config.addDefault("prefix", "&a[&bWormholes&a] ");
        config.addDefault("noPermission", "&cYou don't have permission to use that command!");
        config.addDefault("illegalConsoleSender", "&cThat command may not be sent from the console!");
        config.addDefault("wormholeCreation", "&bWormhole has been successfully created!");

        config.options().copyDefaults(true);
        saveConfig();

    }

    public void saveDatabase() {

        databaseFile = new File(getDataFolder(), "wormholes.yml");
        database = new YamlConfiguration();

        DestinationManager.getInstance().putDestinations(database.createSection("destinations"));

        //Save wormholes.
        ConfigurationSection whSection = database.createSection("wormholes");

        for (Wormhole wormhole : WormholeManager.getInstance()) {

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

            locationSection.set("posX", wormhole.getPosition().getX());
            locationSection.set("posY", wormhole.getPosition().getY());
            locationSection.set("posZ", wormhole.getPosition().getZ());
            section.set("destination", wormhole.getDestination().getUuid().toString());
            section.set("uuid", wormhole.getUuid().toString());
        }

        try {
            database.save(databaseFile);
        } catch (IOException e) {
            logger.warning("Failed to save database.");
            e.printStackTrace();
        }
    }

    public void loadDatabase() {
        databaseFile = new File(getDataFolder(), "wormholes.yml");
        database = new YamlConfiguration();

        if (! databaseFile.exists()) {
            database.createSection("wormholes");
            saveDatabase();
        }

        try {
            database.load(databaseFile);
        } catch (IOException e) {
            logger.warning("Failed to load database.");
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            logger.warning("Database file is corrupt.");
            logger.warning("Failed to load config.");
            e.printStackTrace();
        }

        DestinationManager.getInstance().getDestinations(database.getConfigurationSection("destinations"));

        for (World world : Bukkit.getWorlds()) {
            ConfigurationSection whSection = database.getConfigurationSection("wormholes").getConfigurationSection(world.getUID().toString());
            if (whSection == null) { continue; }

            Set<String> wormholes = whSection.getKeys(false);

            for (String name : wormholes) {
                ConfigurationSection section = whSection.getConfigurationSection(name);
                ConfigurationSection locationSection = section.getConfigurationSection("location");

                WormholeManager.getInstance().add(
                  new Wormhole(
                    new Vector(
                      locationSection.getDouble("posX"),
                      locationSection.getDouble("posY"),
                      locationSection.getDouble("posZ")
                    ),
                    world,
                    name,
                    DestinationManager.getInstance().get(UUID.fromString(section.getString("destination"))),
                    UUID.fromString(section.getString("uuid"))
                  )
                );

                System.out.println(DestinationManager.getInstance().get(UUID.fromString(section.getString("destination"))));
            }
        }
    }
}
