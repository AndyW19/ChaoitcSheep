package me.andyw19.testpl;

import me.andyw19.testpl.commands.ClearCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Test Plugin has loaded");

        getServer().getPluginManager().registerEvents(new BreakBlock(), this);
        getServer().getPluginManager().registerEvents(new SheepTNT(), this);

        getServer().getPluginCommand("clearinv").setExecutor(new ClearCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Test Plugin has unloaded");
    }
}
