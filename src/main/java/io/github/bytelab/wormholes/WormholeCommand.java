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
        String createArguments = config.getString("createArguments").replaceAll("&", "§");
        String noPermission = config.getString("noPermission").replaceAll("&", "§");
        String console = config.getString("illegalConsoleSender").replaceAll("&", "§");
        String wormholeCreation = config.getString("wormholeCreation").replaceAll("&", "§");
        String destinationError = config.getString("destinationError").replaceAll("&", "§");
        String deleteArguments = config.getString("deleteArguments").replaceAll("&", "§");
        String wormholeRemoveError = config.getString("wormholeRemoveError").replaceAll("&", "§");
        String wormholeRemoveSuccessful = config.getString("wormholeRemoveSuccessful").replaceAll("&", "§");
        String prefix = config.getString("prefix").replaceAll("&", "§");
        String pluginReloaded = config.getString("pluginReloaded").replaceAll("&", "§");


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 1) {
                about();
                return false;
            }

            try {
                if (args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("c")) { return onCreateCommand(player, args); }
                if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("d")) { return onDeleteCommand(player, args); }
                if (args[0].equalsIgnoreCase("list")   || args[0].equalsIgnoreCase("l")) { return onListCommand(player, args);   }
                if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")){ return onReloadCommand(player);       }
                if (args[0].equalsIgnoreCase("rename") || args[0].equalsIgnoreCase("rn")){ return onRenameCommand(player, args); }

            } catch (InsufficientPermissionException e) {
                sender.sendMessage(noPermission);
            }

            return false;
        }
        else {
            sender.sendMessage(console);
            return false;
        }
    }

    private void about() {
        //TODO: Add credits and info on plugin here
    }

    private boolean onCreateCommand(Player sender, String[] args) throws InsufficientPermissionException {
        if (! sender.hasPermission(new Permission("wh.create")) && ! sender.isOp()) { throw new InsufficientPermissionException(); }

        if (args.length < 5 || args.length > 6) {
            sender.sendMessage(createArguments);
            return false;
        }

        //assuming args: <name> <destX> <destY> <destZ> [world]
        Vector position = new Vector(
          sender.getLocation().getX(),
          sender.getLocation().getY(),
          sender.getLocation().getZ()
        );

        Destination destination;
        try {
            destination = new FixedDestination(
                    new Vector(
                            Double.parseDouble(args[2]),
                            Double.parseDouble(args[3]),
                            Double.parseDouble(args[4])
                    ),
                    args.length >= 6 ? Bukkit.getWorld(args[5]) : sender.getWorld()
            );
        } catch(NumberFormatException e) {
            sender.sendMessage(destinationError);
            return false;
        }
        //TODO: Allow for named coordinates & tracked targets.

        DestinationManager.getInstance().add(destination);
        WormholeManager.getInstance().add(new Wormhole(position, sender.getWorld(), args[1], destination));

        sender.sendMessage(wormholeCreation);

        return true;
    }

    private boolean onDeleteCommand(Player sender, String[] args) throws InsufficientPermissionException {

        if (! sender.hasPermission(new Permission("wh.delete")) && ! sender.isOp()) { throw new InsufficientPermissionException(); }

        if (args.length != 2) {
            sender.sendMessage(deleteArguments);
            return false;
        }

        Wormhole wormhole = WormholeManager.getInstance().get(args[1]);

        if(wormhole == null) {
            sender.sendMessage(wormholeRemoveError);
            return false;
        }

        WormholeManager.getInstance().remove(wormhole);
        sender.sendMessage(wormholeRemoveSuccessful);
        return true;
    }

    private boolean onListCommand(Player sender, String[] args) throws InsufficientPermissionException {

        if (! sender.hasPermission(new Permission("wh.list")) && ! sender.isOp()) { throw new InsufficientPermissionException(); }

        if(args.length == 1) {

            sender.sendMessage(prefix + "[§7=================================§a]");

            for(Wormhole wormhole : WormholeManager.getInstance()) {

                String name = wormhole.getName();
                String world = wormhole.getWorld().getName();
                sender.sendMessage("§bFound wormhole:§c " + name + " §bin world:§c " + world);

            }
        }
        return true;
    }

    private boolean onReloadCommand(Player sender) throws InsufficientPermissionException {

        if (! sender.hasPermission(new Permission("wh.reload")) && ! sender.isOp()) { throw new InsufficientPermissionException(); }
        //TODO: Add code to reload all configurations
        sender.sendMessage(pluginReloaded);
        return true;
    }

    private boolean onRenameCommand(Player sender, String[] args) throws  InsufficientPermissionException {

        //TODO: Add renaming
        
        return true;
    }
}