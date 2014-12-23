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
import io.github.bytelab.wormholes.Wormhole;
import io.github.bytelab.wormholes.destination.Destination;
import io.github.bytelab.wormholes.destination.FixedDestination;
import io.github.bytelab.wormholes.exception.InsufficientPermissionException;
import me.tbotv63.core.util.command.BaseCommand;
import me.tbotv63.core.util.command.Specification;
import me.tbotv63.core.util.localization.LocaleManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.util.Vector;

import static me.tbotv63.core.util.command.ArgumentType.FLOATING;
import static me.tbotv63.core.util.command.ArgumentType.STRING;

public class CreateAWDCommand extends BaseCommand {

    public CreateAWDCommand() {
        specification = new Specification(STRING, FLOATING, FLOATING, FLOATING, STRING);
        aliases.add("c");
        aliases.add("create");
    }

    @Override
    public boolean execute(CommandSender commandSender, Command command, String s, String[] args) throws InsufficientPermissionException {

        if (! (commandSender instanceof Player)) {
            commandSender.sendMessage(LocaleManager.get("message.error.console"));
            return true;
        }
        else {
            Player sender = (Player) commandSender;

            if (! sender.hasPermission(new Permission("wh.create")) && ! sender.isOp()) { throw new InsufficientPermissionException(); }

            if (Main.getInstance().getWormholes().contains("wormhole", args[0])) {
                sender.sendMessage(LocaleManager.get("message.wormhole.exists", args[0]));
                return true;
            }

            Vector position = new Vector(
              sender.getLocation().getX(),
              sender.getLocation().getY(),
              sender.getLocation().getZ()
            );

            Destination destination;

            World world = Bukkit.getWorld(args[4]);

            if (world == null) {
                sender.sendMessage(LocaleManager.get("message.world.missing", args[5]));
            }

            destination = new FixedDestination(
              new Vector(
                Double.parseDouble(args[1]),
                Double.parseDouble(args[2]),
                Double.parseDouble(args[3])
              ),
              world
            );

            //TODO: Allow for named coordinates & tracked targets.

            Main.getInstance().getDestinations().putElement(destination);
            Main.getInstance().getWormholes().putElement(new Wormhole(position, sender.getWorld(), args[1], destination));

            return true;
        }
    }
}
