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

import io.github.bytelab.wormholes.command.*;
import io.github.bytelab.wormholes.destination.DestinationContainer;
import io.github.bytelab.wormholes.destination.retrievers.WormholeRetriever;
import io.github.bytelab.wormholes.handler.PlayerMoveHandler;
import me.tbotv63.core.util.command.CExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    private static Main instance;

    private final File databaseFile = new File(getDataFolder(), "wormholes.yml");

    private final DestinationContainer destinationContainer = new DestinationContainer();

    private final WormholeContainer wormholeContainer = new WormholeContainer();

    public static Main getInstance() {

        return instance;
    }

    public WormholeContainer getWormholes() {

        return wormholeContainer;
    }

    public DestinationContainer getDestinations() {

        return destinationContainer;
    }

    public void onDisable() {

        saveDatabase();

        destinationContainer.clear();
        wormholeContainer.clear();

        getLogger().info(getDescription().getName() + " version " + getDescription().getVersion() + " has been disabled!");
    }

    public void onEnable() {

        instance = this;

        Config.pluginConfiguration();

        getCommand("wormholes").setExecutor(
          new CExecutor(
            new HelpCommand(),
            new CreateADCommand(),
            new CreateAWDCommand(),
            new CreateNDCommand(),
            new DeleteCommand(),
            new RenameCommand(),
            new ReloadCommand(),
            new ListCommand(),
            new ListPCommand()
          )
        );

        destinationContainer.addRetriever(new WormholeRetriever());

        wormholeContainer.init();
        TeleportManager.getInstance().init();

        getServer().getPluginManager().registerEvents(new PlayerMoveHandler(), this);

        loadDatabase();


        getLogger().info(getDescription().getName() + " version " + getDescription().getVersion() + " has been enabled!");

    }

    void loadDatabase() {

        FileConfiguration database = new YamlConfiguration();

        if (! databaseFile.exists()) {
            saveDatabase();
        }

        try {

            database.load(databaseFile);

            destinationContainer.load(database.getConfigurationSection("destinations"));
            wormholeContainer.load(database.getConfigurationSection("wormholes"));

        } catch (IOException | InvalidConfigurationException | NullPointerException e) {
            getLogger().warning("Your database file can't be read or is corrupt. Delete it and restart the server.");
            e.printStackTrace();
            getPluginLoader().disablePlugin(this);
        }
    }

    void saveDatabase() {

        FileConfiguration database = new YamlConfiguration();

        destinationContainer.save(database.createSection("destinations"));
        wormholeContainer.save(database.createSection("wormholes"));

        try {
            database.save(databaseFile);
        } catch (IOException e) {
            getLogger().warning("Failed to save database.");
            e.printStackTrace();
        }
    }
}
