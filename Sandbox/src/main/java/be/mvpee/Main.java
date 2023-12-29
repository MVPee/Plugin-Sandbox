package be.mvpee;

import be.mvpee.Command.*;
import be.mvpee.Command.admin.*;
import be.mvpee.Listener.*;
import be.mvpee.manager.PlayerManager;
import be.mvpee.menu.tools.ToolsMenuCommand;
import be.mvpee.menu.tools.ToolsMenuListener;
import be.mvpee.scoreboard.ScoreBoard;
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
        // scoreboard
        Bukkit.getPluginManager().registerEvents(new ScoreBoard(), this);

        //Command
        getCommand("contact").setExecutor(new ContactCommand());
        getCommand("plugins").setExecutor(new PluginsCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("ping").setExecutor(new PingCommand());
        // admin
        getCommand("status").setExecutor(new StatusCommand(this));
        getCommand("perm").setExecutor(new PermCommand(this));

        //Manager
        Bukkit.getPluginManager().registerEvents(new PlayerManager(this), this);

        //Tools Menu
        getCommand("tools").setExecutor(new ToolsMenuCommand());
        Bukkit.getPluginManager().registerEvents(new ToolsMenuListener(), this);

//        Bukkit.getScheduler().runTaskTimer(this, () -> {
//            Bukkit.getWorld("world").setTime(13000);
//        }, 0, 2400);
    }

    @Override
    public void onDisable() {
        System.out.println("[Sandbox] OFF");
    }

}
