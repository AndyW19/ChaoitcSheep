package me.andyw19.testpl;

import me.andyw19.testpl.commands.EnableSheep;
import me.andyw19.testpl.commands.ReloadCommand;
import me.andyw19.testpl.sheep.SheepFire;
import me.andyw19.testpl.sheep.SheepTNT;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {

        config.addDefault("TNTSHEEP", true);
        config.addDefault("FIRESHEEP", true);
        config.options().copyDefaults(true);
        saveConfig();

        System.out.println("ChaoticSheep has loaded");

        getServer().getPluginManager().registerEvents(new SheepTNT(this), this);
        getServer().getPluginManager().registerEvents(new SheepFire(this), this);

        getServer().getPluginCommand("csreload").setExecutor(new ReloadCommand(this));
        getServer().getPluginCommand("csenable").setExecutor(new EnableSheep(this));
        getServer().getPluginCommand("csenable").setTabCompleter(new EnableSheep(this));
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("ChaoticSheep has unloaded");
    }
}
