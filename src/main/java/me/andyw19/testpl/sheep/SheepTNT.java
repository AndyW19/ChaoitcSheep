package me.andyw19.testpl.sheep;

import me.andyw19.testpl.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.SheepRegrowWoolEvent;

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
                Location location = sheepRegrowWoolEvent.getEntity().getLocation().add(0,-1,0);

                Block block = location.getBlock();
                block.setType(Material.TNT);
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

}
