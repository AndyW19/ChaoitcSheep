package me.andyw19.chaoticsheep;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BreakBlock implements Listener {

    @EventHandler
    public void onPlayerBreakEvent(BlockBreakEvent event) {
        Block blockBroken = event.getBlock();

        if(blockBroken.getType() == Material.DIRT) {
            event.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack item = new ItemStack(Material.DIAMOND, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), item);
        }
    }

}
