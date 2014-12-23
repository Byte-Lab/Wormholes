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

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

class Locale {

    private final Map<String, String> values;

    public Locale(Properties properties) {
        values = new ConcurrentHashMap<>();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            values.put(
              (String) entry.getKey(),
              ((String) entry.getValue())
                .replace('&', '§')
                .replace("&amp;", "&")
            );
        }
    }

    public Locale() {
        values = new ConcurrentHashMap<>();
    }

    public String getString(String id) {

        return values.containsKey(id) ? values.get(id) : id;
    }

    public boolean hasString(String id) {

        return values.containsKey(id);
    }

}
