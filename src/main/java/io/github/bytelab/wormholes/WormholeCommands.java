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

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class WormholeCommands implements CommandExecutor{

           FileConfiguration config = Main.getPlugin(Main.class).getConfig();
           String noPermission = config.getString("noPermission").replaceAll("&","§");
           String illegalConsoleSender = config.getString("illegalConsoleSender").replaceAll("&","§");
           String prefix = config.getString("prefix").replaceAll("&", "§");
           String creation = config.getString("wormholeCreation").replaceAll("&", "§");
    static Player player;
    static String wormholeName;
    static Vector playerPosition;
    static World world;

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){

        player = (Player) sender;

        if(commandLabel.equalsIgnoreCase("wormhole") || commandLabel.equalsIgnoreCase("wh"))
        {
            if(args.length > 0)
            {

                if(args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("c"))
                {
                    if(sender instanceof Player)
                    {
                        if(sender.hasPermission("wormholes.create"))
                        {
                            if(args.length == 2)
                            {
                                wormholeName = args[1];
                                playerPosition = player.getLocation().toVector();
                                world = player.getWorld();

                                WormholeCreate.create();
                                sender.sendMessage(prefix + creation);
                            }
                            else
                            {
                                sender.sendMessage(prefix + "§a/wormhole create §b<§aname§b>");
                            }
                        }
                        else
                        {
                            sender.sendMessage(noPermission);
                        }
                    }
                    else
                    {
                        sender.sendMessage(illegalConsoleSender);
                    }
                } //Remove a registered wormhole
                else if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("r")){
                /*
                Requires 1 argument and doesn't need to be a player sending it

                args[1] must be a registered wormhole in the list
                 */
                }else if(args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("l")){
                /*
                Requires no arguments and doesn't need to be a player

                May later support paginators and use an arg.
                 */
                } else
                {
                    sender.sendMessage("improper command structure!");
                }

        } else
            {
                sender.sendMessage("improper command structure!");
            }

    }
        return false;
    }
}
