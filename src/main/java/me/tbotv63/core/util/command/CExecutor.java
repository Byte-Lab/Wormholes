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

import io.github.bytelab.wormholes.exception.InsufficientPermissionException;
import me.tbotv63.core.util.localization.LocaleManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CExecutor implements CommandExecutor {

    private final List<CommandElement> elements;

    private final CommandElement helpElement;

    public CExecutor(CommandElement helpElement, CommandElement... commandElements) {
        this.helpElement = helpElement;
        elements = new ArrayList<>();
        elements.add(helpElement);
        Collections.addAll(elements, commandElements);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (args.length < 1) {
            try {
                return helpElement.execute(commandSender, command, s, args);
            } catch (InsufficientPermissionException e) {
                commandSender.sendMessage(LocaleManager.get("message.error.permission"));
                return false;
            }
        }

        ArgumentType[] t = new ArgumentType[args.length];

        for (int i = 0; i < args.length; i++) {
            t[i] = ArgumentType.getSpecific(args[i]);

        }

        Specification specification = new Specification(Arrays.copyOfRange(t, 1, t.length));

        for (CommandElement element : elements) {
            if (Arrays.asList(element.getAliases()).contains(args[0]) && element.getArguments().equals(specification)) {
                try {
                    return element.execute(commandSender, command, s, Arrays.copyOfRange(args, 1, args.length));
                } catch (InsufficientPermissionException e) {
                    commandSender.sendMessage(LocaleManager.get("message.error.permission"));
                    return false;
                }
            }
        }

        for (CommandElement element : elements) {
            if (Arrays.asList(element.getAliases()).contains(args[0]) && element.getArguments().compatible(specification)) {
                try {
                    return element.execute(commandSender, command, s, Arrays.copyOfRange(args, 1, args.length));
                } catch (InsufficientPermissionException e) {
                    commandSender.sendMessage(LocaleManager.get("message.error.permission"));
                    return false;
                }
            }
        }

        try {
            return helpElement.execute(commandSender, command, s, args);
        } catch (InsufficientPermissionException e) {
            commandSender.sendMessage(LocaleManager.get("message.error.permission"));
            return false;
        }
    }
}
