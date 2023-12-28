package be.mvpee;

import be.mvpee.Command.ContactCommand;
import be.mvpee.Command.HelpCommand;
import be.mvpee.ToolsMenu.ToolsMenuCommand;
import be.mvpee.Listener.*;
import be.mvpee.ToolsMenu.ToolsMenuListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("[Sandbox] ON");

        //Listener
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new HungerListener(), this);
        Bukkit.getPluginManager().registerEvents(new WeatherListener(), this);
        Bukkit.getPluginManager().registerEvents(new PingListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new CommandListener(), this);

        Bukkit.getPluginManager().registerEvents(new ScoreBoard(), this);

        //Command
        getCommand("contact").setExecutor(new ContactCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("?").setExecutor(new HelpCommand());

        //Tools Menu
        getCommand("tools").setExecutor(new ToolsMenuCommand());
        Bukkit.getPluginManager().registerEvents(new ToolsMenuListener(), this);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            Bukkit.getWorld("world").setTime(13000);
        }, 0, 2400);
    }

    @Override
    public void onDisable() {
        System.out.println("[Sandbox] OFF");
    }

}
