package me.andyw19.testpl.commands;

import me.andyw19.testpl.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {

    private final Main main;

    public ReloadCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp() || player.hasPermission("csreload")) {

                main.reloadConfig();

                player.sendMessage(ChatColor.GREEN + "Plugin has been reloaded!");

                return true;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }
}

