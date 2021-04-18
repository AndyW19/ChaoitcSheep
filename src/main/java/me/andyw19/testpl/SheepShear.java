package me.andyw19.testpl;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class SheepShear implements Listener {

    private final Main main;

    public SheepShear(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onSheepShear(PlayerShearEntityEvent shearEvent) {
        Sheep sheep = (Sheep) shearEvent.getEntity();
        DyeColor dyeColor = sheep.getColor();
        World world = sheep.getWorld();
        Material mat;

        if (dyeColor != null) {
            switch(dyeColor) {
                case RED:
                    mat = Material.GUNPOWDER;
                    shearSheep(randInt(1,3), world, sheep, mat, shearEvent);
                    break;
                case ORANGE:
                    mat = Material.FIRE_CHARGE;
                    shearSheep(randInt(1,3), world, sheep, mat, shearEvent);
                    break;
                case YELLOW:
                    Material [] matArray = {Material.DANDELION, Material.POPPY, Material.CORNFLOWER,
                            Material.ORANGE_TULIP, Material.PINK_TULIP, Material.RED_TULIP};
                    mat = matArray[randInt(0, matArray.length - 1)];
                    shearSheep(randInt(1,3), world, sheep, mat, shearEvent);
                    break;
            }
        }
    }

    private static int randInt(int min, int max) {

        Random rand = new Random();

        return rand.nextInt((max - min) + 1) + min;

    }

    private void shearSheep (int ran, World world, Sheep sheep, Material material, PlayerShearEntityEvent shearEvent) {
        shearEvent.setCancelled(true);
        ItemStack item = new ItemStack(material, ran);
        sheep.setSheared(true);
        world.dropItemNaturally(sheep.getLocation(), item);
        world.playSound(sheep.getLocation(), Sound.ENTITY_SHEEP_SHEAR, 1, 1);
    }

}
