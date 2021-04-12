package me.andyw19.testpl.sheep;

import me.andyw19.testpl.Main;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SheepRegrowWoolEvent;

import java.util.Random;

public class SheepFlower implements Listener {

    private final Main main;

    public SheepFlower(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onSheepGraze(SheepRegrowWoolEvent sheepRegrowWoolEvent) {

        if (main.getConfig().getBoolean("FLOWERSHEEP")) {
            Sheep sheep = sheepRegrowWoolEvent.getEntity();
            DyeColor dyeColor = sheep.getColor();

            if (dyeColor == DyeColor.YELLOW) {
                Material [] flowerArray = {Material.DANDELION, Material.POPPY, Material.SUNFLOWER, Material.BLUE_ORCHID, Material.CORNFLOWER, Material.LILAC, Material.LILY_OF_THE_VALLEY};

                int ranVal = randInt(0,6);

                Location location = sheepRegrowWoolEvent.getEntity().getLocation();
                Location location2 = sheepRegrowWoolEvent.getEntity().getLocation().add(0,-1,0);

                Block block = location.getBlock();
                Block block2 = location2.getBlock();
                block2.setType(Material.GRASS_BLOCK);
                block.setType(flowerArray[ranVal]);
            }
        }
    }

    private static int randInt(int min, int max) {

        Random rand = new Random();

        return rand.nextInt((max - min) + 1) + min;

    }
}
