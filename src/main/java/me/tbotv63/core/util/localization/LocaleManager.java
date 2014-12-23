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
package me.tbotv63.core.util.localization;

import io.github.bytelab.wormholes.Config;
import io.github.bytelab.wormholes.Main;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class LocaleManager {

    private static final Map<String, Locale> localizationMap = new ConcurrentHashMap<>();

    private LocaleManager() {}

    public static String get(String id, Object... params) {

        return get(id, Config.localization, params);
    }

    public static String get(String id, String locale, Object[] params) {

        if (! localizationMap.containsKey(locale)) {
            load(locale);
        }
        String s = localizationMap.get(locale).getString(id);

        for (int i = 0, paramsLength = params.length; i < paramsLength; i++) {
            s = s.replace("{" + i + "}", params[i].toString());
        }

        return s;
    }

    private static void load(String locale) {

        Properties properties = new Properties();
        try {
            properties.load(Main.class.getClassLoader().getResourceAsStream("lang/" + locale + ".lang"));
            localizationMap.put(locale, new Locale(properties));
        } catch (IOException e) {
            e.printStackTrace();
            localizationMap.put(locale, new Locale());
        }

    }

    public static String[] getMultiple(String id, Object... params) {

        return getMultiple(id, Config.localization, params);
    }

    public static String[] getMultiple(String id, String locale, Object[] params) {

        if (! localizationMap.containsKey(locale)) {
            load(locale);
        }

        String[] r = new String[0];

        int i = 0;
        while (localizationMap.get(locale).hasString(id + "." + i)) {
            r = Arrays.copyOf(r, r.length + 1);
            r[i] = get(id + "." + i, locale, params);
            i += 1;
        }

        if (r.length == 0) {
            r = Arrays.copyOf(r, r.length + 1);
            r[i] = get(id + "." + 0, locale, params);
        }

        return r;
    }


}
