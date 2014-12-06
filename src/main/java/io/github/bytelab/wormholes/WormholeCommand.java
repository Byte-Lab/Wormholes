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

import io.github.bytelab.wormholes.destination.FixedDestination;
import io.github.bytelab.wormholes.destination.WormholeDestination;
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

            try {

                if (args[1].equals("create") || args[1].equals("c")) { return onCreateCommand(player, args); }

            } catch (InsufficientPermissionException e) {
                sender.sendMessage(config.getString("noPermission").replaceAll("&", "§"));
            }
        }

        sender.sendMessage(config.getString("illegalConsoleSender").replaceAll("&", "§"));
        return false;
    }

    private boolean onCreateCommand(Player sender, String[] args) throws InsufficientPermissionException {
        if (! sender.hasPermission(new Permission("wh.create")) && ! sender.isOp()) { throw new InsufficientPermissionException(); }
        //assuming args: <name> <destX> <destY> <destZ>
        Vector position = new Vector(
          sender.getLocation().getX(),
          sender.getLocation().getY(),
          sender.getLocation().getZ()
        );

        WormholeDestination destination = new FixedDestination(
          new Vector(
            Double.parseDouble(args[2]),
            Double.parseDouble(args[3]),
            Double.parseDouble(args[4])
          )
        );

        //TODO: Allow for named coordinates & tracked targets.

        WormholeManager.wormholes.add(new Wormhole(position, sender.getWorld(), args[1], destination));

        sender.sendMessage(config.getString("wormholeCreation").replaceAll("&", "§"));

        return true;
    }


}
