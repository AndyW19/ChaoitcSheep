package me.andyw19.testpl;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

import static org.bukkit.DyeColor.*;


public class SheepSpawn extends BukkitRunnable {

    private final Main main;

    public SheepSpawn(Main main) {
        this.main = main;
    }

    @Override

    public void run() {
        for (Player on : Bukkit.getOnlinePlayers()) {

            List<Entity> nearby =  on.getNearbyEntities(25,25,25);
            int countSheep = 0;
            for (Entity tmp: nearby) {
                if (tmp instanceof Sheep) {
                    countSheep++;
                }
            }

            if (countSheep < 10) {
                Location toSpawn = null;

                int x = on.getLocation().getBlockX() + randInt(-20, 20);
                int z = on.getLocation().getBlockZ() + randInt(-20, 20);
                World world = on.getWorld();

                for (int y = 30; y < 100; y++) {
                    toSpawn = new Location(world, x, y, z);
                    if (toSpawn.getBlock().getType() == Material.AIR && toSpawn.add(0, 3, 0).getBlock().getType() == Material.AIR) {
                        toSpawn = new Location(on.getWorld(), x, y, z);
                        break;
                    }
                }

                int lRand = randInt(1, 4);
                int dRand = randInt(1, 16);

                DyeColor dyeColor;

                switch (dRand) {
                    case 1:
                        dyeColor = LIME;
                        break;
                    case 2:
                        dyeColor = RED;
                        break;
                    case 3:
                        dyeColor = BLACK;
                        break;
                    case 4:
                        dyeColor = BLUE;
                        break;
                    case 5:
                        dyeColor = BROWN;
                        break;
                    case 6:
                        dyeColor = ORANGE;
                        break;
                    case 7:
                        dyeColor = LIGHT_BLUE;
                        break;
                    case 8:
                        dyeColor = LIGHT_GRAY;
                        break;
                    case 9:
                        dyeColor = GREEN;
                        break;
                    case 10:
                        dyeColor = GRAY;
                        break;
                    case 11:
                        dyeColor = PINK;
                        break;
                    case 12:
                        dyeColor = PURPLE;
                        break;
                    case 13:
                        dyeColor = WHITE;
                        break;
                    case 14:
                        dyeColor = CYAN;
                        break;
                    case 15:
                        dyeColor = YELLOW;
                        break;
                    case 16:
                        dyeColor = MAGENTA;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + dRand);
                }


                for (int i = 0; i < lRand; i++) {
                    Sheep sheep = (Sheep) world.spawnEntity(toSpawn, EntityType.SHEEP);
                    sheep.setColor(dyeColor);
                }
            }
        }

    }

    private static int randInt(int min, int max) {

        Random rand = new Random();

        return rand.nextInt((max - min) + 1) + min;

    }
}

