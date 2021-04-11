package me.andyw19.testpl.commands;

import me.andyw19.testpl.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EnableSheep implements CommandExecutor, TabCompleter  {

    private final Main main;

    public EnableSheep(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp() || player.hasPermission("csenable")) {
                if (args.length != 2) {
                    player.sendMessage(ChatColor.RED + "Use /csenable <TNTSheep/FireSheep> <true/false>");
                    return true;
                } else {
                    try {
                        boolean bool = Boolean.parseBoolean(args[1]);
                        main.getConfig().set(args[0].toUpperCase(), bool);
                        main.saveConfig();
                        main.reloadConfig();
                        player.sendMessage(ChatColor.YELLOW + "Set " + args[0] + " to " + bool);
                        return true;
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "Use /csenable <TNTSheep/FireSheep> <true/false>");
                    }
                }
                return true;
            }
        }
            return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (args.length == 1) {
            List<String> arguements = new ArrayList<>();
            arguements.add("TNTSHEEP");
            arguements.add("FIRESHEEP");
            arguements.add("SHEEPSPAWNING");

            return arguements;

        } else if (args.length == 2) {
            List<String> arguements = new ArrayList<>();
            arguements.add("true");
            arguements.add("false");

            return arguements;
        }

        return null;
    }
}
