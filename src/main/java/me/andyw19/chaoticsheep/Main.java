package me.andyw19.chaoticsheep;

import me.andyw19.chaoticsheep.commands.ClearCommand;
import me.andyw19.chaoticsheep.commands.ReloadCommand;
import me.andyw19.chaoticsheep.sheep.SheepFire;
import me.andyw19.chaoticsheep.sheep.SheepTNT;
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

        getServer().getPluginManager().registerEvents(new SheepTNT(this), this);
        getServer().getPluginManager().registerEvents(new SheepFire(this), this);

        getServer().getPluginCommand("clearinv").setExecutor(new ClearCommand(this));
        getServer().getPluginCommand("reloadpl").setExecutor(new ReloadCommand(this));
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Test Plugin has unloaded");
    }
}
