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

    public static final boolean useColors;

    public static final boolean playSounds;

    public static final String soundType;

    public static final String localization;

    public static final int suckRadius;

    public static final int pageLength = 10;

    static {
        FileConfiguration config = Main.getInstance().getConfig();

        useColors = config.getBoolean("color");
        playSounds = config.getBoolean("sound");
        soundType = config.getString("soundType");
        localization = config.getString("localization");
        suckRadius = config.getInt("suckRadius");

    }

    public static void pluginConfiguration() {

        FileConfiguration config = Main.getInstance().getConfig();


        config.addDefault("sound", true);
        config.addDefault("soundType", "vacuum");
        config.addDefault("color", false);
        config.addDefault("localization", "en_US");
        config.addDefault("suckRadius", 3);

        config.options().copyDefaults(true);
        Main.getInstance().saveConfig();

    }

}

