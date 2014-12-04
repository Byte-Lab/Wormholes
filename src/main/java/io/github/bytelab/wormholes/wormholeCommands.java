package main.java.io.github.bytelab.wormholes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class wormholeCommands implements CommandExecutor{

           FileConfiguration config = main.getPlugin(main.class).getConfig();
           String noPermission = config.getString("noPermission").replaceAll("&","§");
           String illegalConsoleSender = config.getString("illegalConsoleSender").replaceAll("&","§");
           String prefix = config.getString("prefix").replaceAll("&", "§");
    static Player player;
    static String wormholeName;
    static Vector playerPosition;

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){

        player = (Player) sender;

        if(commandLabel.equalsIgnoreCase("wh"))
        {   //Create the wormhole
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

                            wormholeCreate.create();
                        }
                        else
                        {
                            sender.sendMessage(prefix + "§acreate §b<§aname§b>");
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
            else if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("r"))
            {
                /*
                Requires 1 argument and doesn't need to be a player sending it

                args[1] must be a registered wormhole in the list
                 */
            }
            else if(args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("l"))
            {
                /*
                Requires no arguments and doesn't need to be a player

                May later support paginators and use an arg.
                 */
            }
        }
        return false;
    }
}