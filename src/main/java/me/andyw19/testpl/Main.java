package me.andyw19.testpl;

import me.andyw19.testpl.commands.ClearCommand;
import me.andyw19.testpl.sheep.SheepFire;
import me.andyw19.testpl.sheep.SheepTNT;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {

        config.addDefault("TntSheep", true);
        config.addDefault("FireSheep", true);
        config.options().copyDefaults(true);
        saveConfig();

        System.out.println("Test Plugin has loaded");

        getServer().getPluginManager().registerEvents(new BreakBlock(), this);

        if (config.getBoolean("TntSheep")) {
            getServer().getPluginManager().registerEvents(new SheepTNT(), this);
        }
        if (config.getBoolean("FireSheep")) {
            getServer().getPluginManager().registerEvents(new SheepFire(), this);
        }

        getServer().getPluginCommand("clearinv").setExecutor(new ClearCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Test Plugin has unloaded");
    }
}
