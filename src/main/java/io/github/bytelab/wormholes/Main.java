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

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private static Main instance;
    PluginDescriptionFile pdf = this.getDescription();
    Logger logger = Logger.getLogger("Wormholes");

    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        pluginConfiguration();

        instance = this;

        WormholeManager.init();

        getCommand("wh").setExecutor(new WormholeCommand());
        getCommand("wormhole").setExecutor(new WormholeCommand());

        logger.info(pdf.getName() + " version " + pdf.getVersion() + " has been enabled!");
    }

    public void onDisable() {

        logger.info(pdf.getName() + " version " + pdf.getVersion() + " has been disabled!");
    }

    public void pluginConfiguration() {
        FileConfiguration config = getConfig();

        config.addDefault("prefix", "&a[&bWormholes&a] ");
        config.addDefault("noPermission", "&cYou don't have permission to use that command!");
        config.addDefault("illegalConsoleSender", "&cThat command may not be sent from the console!");
        config.addDefault("wormholeCreation", "&bWormhole has been successfully created!");

        config.options().copyDefaults(true);
        saveConfig();
    }

    public void saveFile() {
        //TODO: Save configs and data
    }

    public void loadFile() {
        //TODO: Load configs and data
    }
}
