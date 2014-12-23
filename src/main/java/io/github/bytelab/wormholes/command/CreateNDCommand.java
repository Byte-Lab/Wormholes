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

import io.github.bytelab.wormholes.exception.InsufficientPermissionException;
import me.tbotv63.core.util.command.BaseCommand;
import me.tbotv63.core.util.command.Specification;
import me.tbotv63.core.util.localization.LocaleManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import static me.tbotv63.core.util.command.ArgumentType.STRING;

public class CreateNDCommand extends BaseCommand {

    public CreateNDCommand() {
        specification = new Specification(STRING, STRING);
        aliases.add("c");
        aliases.add("create");
    }

    @Override
    public boolean execute(CommandSender commandSender, Command command, String s, String[] args) throws InsufficientPermissionException {

        commandSender.sendMessage(LocaleManager.get("message.error.notimplemented"));
        return true;
                /*
        if (! (commandSender instanceof Player)) {
            commandSender.sendMessage(LocaleManager.get("message.error.console"));
            return true;
        }
        else {
            Player sender = (Player) commandSender;

            if (! sender.hasPermission(new Permission("wh.create")) && ! sender.isOp()) { throw new InsufficientPermissionException(); }

            if(Wormholes.getInstance().getWormholes().contains("wormhole", args[0])) {
                sender.sendMessage(LocaleManager.get("message.wormhole.exists", args[0]));
                return true;
            }

            Vector position = new Vector(
              sender.getLocation().getX(),
              sender.getLocation().getY(),
              sender.getLocation().getZ()
            );

            Destination destination;

            try {
                destination = Wormholes.getInstance().getDestinations().get(args[1]);
            } catch(IllegalArgumentException e) {
                sender.sendMessage(LocaleManager.getMultiple("message.name.invalid", args[1]));
                return true;
            }

            if(destination == null) {
                sender.sendMessage(LocaleManager.get("message.destination.missing"));
                return true;
            }

            Wormholes.getInstance().getWormholes().putElement(new Wormhole(position, sender.getWorld(), args[0], destination));

            return true;

        }
         */
    }
}
