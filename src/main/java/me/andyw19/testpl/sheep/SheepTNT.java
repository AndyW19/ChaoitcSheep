package me.andyw19.testpl.sheep;

import me.andyw19.testpl.Main;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.SheepRegrowWoolEvent;

import java.util.Random;

public class SheepTNT implements Listener {

    private final Main main;

    public SheepTNT(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onSheepGraze(SheepRegrowWoolEvent sheepRegrowWoolEvent) {

        if (main.getConfig().getBoolean("TNTSHEEP")) {
            Sheep sheep = sheepRegrowWoolEvent.getEntity();
            DyeColor dyeColor = sheep.getColor();

            if (dyeColor == DyeColor.RED) {
                Location location = sheepRegrowWoolEvent.getEntity().getLocation();

                Random rand = new Random();
                int ran = rand.nextInt(3-1) + 1;

                World world = sheepRegrowWoolEvent.getEntity().getWorld();

                triggerTNT(ran, world, location);
            }
        }
    }

    @EventHandler
    public void onSheepFire(EntityDamageEvent event) {

        if (main.getConfig().getBoolean("TNTSHEEP")) {
            if (event.getEntity() instanceof Sheep) {
                Sheep sheep = (Sheep) event.getEntity();
                DyeColor dyeColor = sheep.getColor();
                if (dyeColor == DyeColor.RED) {
                    if((event.getCause() == EntityDamageEvent.DamageCause.FIRE
                            || event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)) {
                        event.getEntity().getWorld().createExplosion(sheep.getLocation(), 4f);
                    }
                }

            }
        }
    }

    public static void triggerTNT(int ran, World world, Location location) {
        for (int i = 0; i < ran; i++) {
            Entity tnt = world.spawnEntity(location, EntityType.PRIMED_TNT);
        }
    }

}
