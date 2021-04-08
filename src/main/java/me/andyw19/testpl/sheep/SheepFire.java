package me.andyw19.testpl.sheep;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SheepRegrowWoolEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SheepFire implements Listener {

    @EventHandler
    public void onSheepGraze(SheepRegrowWoolEvent sheepRegrowWoolEvent) {

        Sheep sheep = sheepRegrowWoolEvent.getEntity();
        DyeColor dyeColor = sheep.getColor();

        if (dyeColor == DyeColor.ORANGE) {
            sheep.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 5));
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "Fire Sheep Triggered!");
            Location location = sheepRegrowWoolEvent.getEntity().getLocation();
            Block block = location.getBlock();
            block.setType(Material.FIRE);
        }

    }

}