package me.andyw19.testpl.sheep;

import me.andyw19.testpl.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.SheepRegrowWoolEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SheepFire implements Listener {

    private final Main main;

    public SheepFire(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onSheepGraze(SheepRegrowWoolEvent sheepRegrowWoolEvent) {

        if (main.getConfig().getBoolean("FIRESHEEP")) {
            Sheep sheep = sheepRegrowWoolEvent.getEntity();
            DyeColor dyeColor = sheep.getColor();

            if (dyeColor == DyeColor.ORANGE) {
                sheep.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 5));
                Location location = sheepRegrowWoolEvent.getEntity().getLocation();
                Block block = location.getBlock();
                block.setType(Material.FIRE);
            }
        }
    }

    @EventHandler
    public void onSheepFire(EntityDamageEvent event) {

        if (main.getConfig().getBoolean("TNTSHEEP")) {
            if (event.getEntity() instanceof Sheep) {
                Sheep sheep = (Sheep) event.getEntity();
                DyeColor dyeColor = sheep.getColor();
                if (dyeColor == DyeColor.ORANGE) {
                    if((event.getCause() == EntityDamageEvent.DamageCause.FIRE
                            || event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)) {
                        sheep.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 5));
                    }
                }

            }
        }
    }

}