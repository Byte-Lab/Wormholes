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

import io.github.bytelab.wormholes.Config;
import io.github.bytelab.wormholes.Main;
import io.github.bytelab.wormholes.WormholeContainer;
import io.github.bytelab.wormholes.exception.InsufficientPermissionException;
import me.tbotv63.core.util.command.BaseCommand;
import me.tbotv63.core.util.command.Specification;
import me.tbotv63.core.util.localization.LocaleManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public class ListCommand extends BaseCommand {

    public ListCommand() {
        specification = new Specification();
        aliases.add("list");
        aliases.add("l");
    }

    @Override
    public boolean execute(CommandSender commandSender, Command command, String s, String[] args) throws InsufficientPermissionException {

        if (! commandSender.hasPermission(new Permission("wh.list")) && ! commandSender.isOp()) { throw new InsufficientPermissionException(); }

        WormholeContainer c = Main.getInstance().getWormholes();

        commandSender.sendMessage(LocaleManager.get("message.list.heading", 1));

        for (int i = 0; i < Config.pageLength; i++) {
            if (i >= c.size()) { break; }
            commandSender.sendMessage(LocaleManager.get("message.list.entry", c.get(i).getName(), c.get(i).getDestination().getType()));
        }

        return true;
    }
}
