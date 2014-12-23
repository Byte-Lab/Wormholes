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
package io.github.bytelab.wormholes.command;

import io.github.bytelab.wormholes.Main;
import io.github.bytelab.wormholes.exception.InsufficientPermissionException;
import me.tbotv63.core.util.command.BaseCommand;
import me.tbotv63.core.util.command.Specification;
import me.tbotv63.core.util.localization.LocaleManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import static me.tbotv63.core.util.command.ArgumentType.STRING;

public class RenameCommand extends BaseCommand {

    public RenameCommand() {
        specification = new Specification(STRING, STRING);
        aliases.add("r");
        aliases.add("rename");
    }

    @Override
    public boolean execute(CommandSender commandSender, Command command, String s, String[] args) throws InsufficientPermissionException {

        if (! commandSender.hasPermission(new Permission("wh.create")) && ! commandSender.isOp()) { throw new InsufficientPermissionException(); }

        if (Main.getInstance().getWormholes().contains("wormhole", args[1])) {
            commandSender.sendMessage(LocaleManager.get("message.wormhole.exists", args[1]));
            return true;
        }

        if (Main.getInstance().getWormholes().contains("wormhole", args[0])) {
            Main.getInstance().getWormholes().get("wormhole", args[0]).setName(args[1]);
            commandSender.sendMessage(LocaleManager.get("message.rename.success"));
        }
        else {
            commandSender.sendMessage(LocaleManager.get("message.wormhole.missing", args[0]));
        }

        return true;
    }
}
