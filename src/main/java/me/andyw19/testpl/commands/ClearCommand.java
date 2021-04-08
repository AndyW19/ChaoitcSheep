package me.andyw19.testpl.commands;

import me.andyw19.testpl.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ClearCommand implements CommandExecutor {

    private final Main main;

    public ClearCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp() || player.hasPermission("clearinv")) {
                Inventory inv = player.getInventory();
                inv.clear();

                player.sendMessage(ChatColor.GREEN + "Your inventory has been cleared!");

                return true;
            } else {
                player.sendMessage(ChatColor.RED + "You don't have the required permissions");
                return true;
            }
        } else {
            main.getLogger().info("You have to be a player to clear your inventory");
            return true;
        }
    }
}
