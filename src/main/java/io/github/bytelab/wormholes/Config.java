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

import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    static FileConfiguration config = Main.getInstance().getConfig();

    public static boolean useColors = config.getBoolean("color");

    public static boolean playSounds = config.getBoolean("sound");

    public static String soundType = config.getString("soundType");

    public static void pluginConfiguration() {
        FileConfiguration config = Main.getInstance().getConfig();

        config.addDefault("config", 1);
        config.addDefault("prefix", "&a[&bWormholes&a] ");
        config.addDefault("noPermission", "&cERROR: You don't have permission to use that command!");
        config.addDefault("illegalConsoleSender", "&cERROR: That command may not be sent from the console!");
        config.addDefault("wormholeCreation", "&bWormhole has been successfully created!");
        config.addDefault("createArguments", "&cERROR: /wormhole &a<&cname&a> <&cdestX&a> <&cdestY&a> <&cdestZ&a> [&cworld&a]");
        config.addDefault("deleteArguments", "&cERROR: /wormhole delete0 &a<&cname&a>");
        config.addDefault("destinationError", "&cERROR: Destination values must be numbers!");
        config.addDefault("nullWormholeError", "&cERROR: The specified wormhole does not exist!");
        config.addDefault("wormholeRemoveSuccessful", "&bWormhole has been removed successfully!");
        config.addDefault("pluginReloaded", "&bSucessfully reloaded plugin!");
        config.addDefault("renameSuccessful", "&bSucessfully renamed wormhole!");
        config.addDefault("renameArguments", "&cERROR: /wormhole rename &a<&cname&a> <&cnew name&a>");

        config.addDefault("sound", true);
        config.addDefault("soundType", "vacuum");
        config.addDefault("color", false);

        config.options().copyDefaults(true);
        Main.getInstance().saveConfig();

    }

}

