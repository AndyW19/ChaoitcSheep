package me.andyw19.testpl.sheep;

import me.andyw19.testpl.Main;
import org.bukkit.DyeColor;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.entity.SheepRegrowWoolEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class SheepJumper implements Listener {

    private final Main main;

    public SheepJumper(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onSheepGraze(SheepRegrowWoolEvent sheepRegrowWoolEvent) {

        if (main.getConfig().getBoolean("JUMPSHEEP")) {
            Sheep sheep = sheepRegrowWoolEvent.getEntity();
            DyeColor dyeColor = sheep.getColor();

            if (dyeColor == DyeColor.LIME) {
                System.out.println("Lime sheep potion!");
                sheep.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 300000000, 5));
                sheep.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 300000000, 5));
            }
        }
    }

    @EventHandler
    public void onSheepDye(SheepDyeWoolEvent sheepDyeWoolEvent) {

        Sheep sheep = sheepDyeWoolEvent.getEntity();

        if (sheep.getColor() == DyeColor.LIME) {
            System.out.println("Lime sheep!");

            if (main.getConfig().getBoolean("JUMPSHEEP")) {
                sheep.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 300000000, 5));
                sheep.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 300000000, 5));
            }

        }
    }


}
