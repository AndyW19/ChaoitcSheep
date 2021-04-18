package me.andyw19.testpl;

import me.andyw19.testpl.commands.EnableSheep;
import me.andyw19.testpl.commands.ReloadCommand;
import me.andyw19.testpl.sheep.SheepFire;
import me.andyw19.testpl.sheep.SheepFlower;
import me.andyw19.testpl.sheep.SheepTNT;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class Main extends JavaPlugin {

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {

        config.addDefault("SHEEPSPAWNING", true);
        config.addDefault("SHEEPSPAWNING-CAP", 30);
        config.addDefault("SHEEPSPAWNING-FREQUENCY", 30);
        config.addDefault("TNTSHEEP", true);
        config.addDefault("FIRESHEEP", true);
        config.addDefault("FLOWERSHEEP", true);
        config.options().copyDefaults(true);
        saveConfig();

        System.out.println("ChaoticSheep has loaded");

        getServer().getPluginManager().registerEvents(new SheepShear(this), this);

        getServer().getPluginManager().registerEvents(new SheepTNT(this), this);
        getServer().getPluginManager().registerEvents(new SheepFire(this), this);
        getServer().getPluginManager().registerEvents(new SheepFlower(this), this);

        getServer().getPluginCommand("csreload").setExecutor(new ReloadCommand(this));
        getServer().getPluginCommand("csenable").setExecutor(new EnableSheep(this));
        getServer().getPluginCommand("csenable").setTabCompleter(new EnableSheep(this));

        long per = config.getInt("SHEEPSPAWNING_FREQUENCY") * 20L;

        BukkitTask sheepSpawn = new SheepSpawn(this).runTaskTimer(this,200L, per);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("ChaoticSheep has unloaded");
    }
}
