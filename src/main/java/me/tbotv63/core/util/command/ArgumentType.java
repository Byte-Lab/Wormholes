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
package me.tbotv63.core.util.command;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Contains possible argument types, sorted from the most generic type to the most specific type.
 */
public enum ArgumentType {
    INTEGER("integer"),
    FLOATING("floating", INTEGER),
    STRING("string", FLOATING, INTEGER);

    private final ArgumentType[] compat;

    private final String name;

    ArgumentType(String name, ArgumentType... compat) {
        this.compat = compat;
        this.name = name;
    }

    public static ArgumentType getSpecific(String arg) {

        ArgumentType r;

        if (isInteger(arg)) { r = INTEGER; }
        else if (isFloat(arg)) { r = FLOATING; }
        else { r = STRING; }

        //System.out.print("Specific: " + arg + ":" + r.getName());

        return r;
    }

    private static boolean isFloat(String str) {

        return DOUBLE_PATTERN.matcher(str).matches();
    }

    private static boolean isInteger(String str) {

        return INTEGER_PATTERN.matcher(str).matches();
    }

    private static final Pattern DOUBLE_PATTERN = Pattern.compile(
      "[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)" +
        "([eE][+-]?(\\p{Digit}+))?)|(\\.(\\p{Digit}+)([eE][+-]?(\\p{Digit}+))?)|" +
        "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))" +
        "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*"
    );

    private static final Pattern INTEGER_PATTERN = Pattern.compile(
      "[\\x00-\\x20]*[+-]?(NaN|Infinity|(\\d+|(0[xX])?[0-9A-Fa-f]+))[\\x00-\\x20]*"
    );

    public boolean isCompatible(ArgumentType type) {

        return this == type || - Arrays.binarySearch(compat, type) - 1 == 0;
    }

    public String getName() {

        return name;
    }

    public String toString() {

        return "ArgumentType:" + name;
    }
}
