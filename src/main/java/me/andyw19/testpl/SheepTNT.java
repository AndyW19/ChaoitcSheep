package me.andyw19.testpl;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SheepRegrowWoolEvent;

import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class SheepTNT implements Listener {

    @EventHandler
    public void onSheepGraze(SheepRegrowWoolEvent sheepRegrowWoolEvent) {

        Sheep sheep = sheepRegrowWoolEvent.getEntity();
        DyeColor dyeColor = sheep.getColor();

        if (dyeColor == DyeColor.RED) {
            Bukkit.broadcastMessage(ChatColor.RED + "TNT Sheep Triggered!");
            Location location = sheepRegrowWoolEvent.getEntity().getLocation();

            Random rand = new Random();
            int ran = rand.nextInt(3-1) + 1;

            World world = sheepRegrowWoolEvent.getEntity().getWorld();

            triggerTNT(ran, world, location);
        }

    }

    public static void triggerTNT(int ran, World world, Location location) {
        for (int i = 0; i < ran; i++) {
            Entity tnt = world.spawnEntity(location, EntityType.PRIMED_TNT);
        }
    }

}
