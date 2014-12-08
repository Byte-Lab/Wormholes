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

import io.github.bytelab.wormholes.destination.Destination;
import io.github.bytelab.wormholes.destination.DestinationManager;
import io.github.bytelab.wormholes.destination.FixedDestination;
import io.github.bytelab.wormholes.exception.InsufficientPermissionException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.util.Vector;

public class WormholeCommand implements CommandExecutor {

    FileConfiguration config = Main.getInstance().getConfig();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 1) {
                return false;
            }

            try {
                if (args[0].equals("create") || args[0].equals("c")) { return onCreateCommand(player, args); }

            } catch (InsufficientPermissionException e) {
                sender.sendMessage(config.getString("noPermission").replaceAll("&", "§"));
            }

            return false;
        }
        else {
            sender.sendMessage(config.getString("illegalConsoleSender").replaceAll("&", "§"));
            return false;
        }
    }

    private boolean onCreateCommand(Player sender, String[] args) throws InsufficientPermissionException {
        if (! sender.hasPermission(new Permission("wh.create")) && ! sender.isOp()) { throw new InsufficientPermissionException(); }

        if (args.length < 5 || args.length > 6) {
            sender.sendMessage(
              new String[]{
                "Invalid argument count.",
                "Usage: <name> <destX> <destY> <destZ> [world]"
              }
            );
        }

        //assuming args: <name> <destX> <destY> <destZ> [world]
        Vector position = new Vector(
          sender.getLocation().getX(),
          sender.getLocation().getY(),
          sender.getLocation().getZ()
        );

        Destination destination = new FixedDestination(
          new Vector(
            Double.parseDouble(args[2]),
            Double.parseDouble(args[3]),
            Double.parseDouble(args[4])
          ),
          args.length >= 6 ? Bukkit.getWorld(args[5]) : sender.getWorld()
        );

        //TODO: Allow for named coordinates & tracked targets.

        DestinationManager.getInstance().add(destination);
        WormholeManager.getInstance().add(new Wormhole(position, sender.getWorld(), args[1], destination));

        sender.sendMessage(config.getString("wormholeCreation").replaceAll("&", "§"));

        return true;
    }


}
///wh create test -256 69 115
