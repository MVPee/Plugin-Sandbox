package be.mvpee;

import be.mvpee.Command.*;
import be.mvpee.Command.admin.*;
import be.mvpee.Listener.*;
import be.mvpee.manager.MoneyManager;
import be.mvpee.manager.PermissionsManager;
import be.mvpee.manager.PlayerManager;
import be.mvpee.manager.ScoreboardManager;
import be.mvpee.menu.tools.ToolsMenuCommand;
import be.mvpee.menu.tools.ToolsMenuListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("[Sandbox] ON");

        //Manager
        PlayerManager playerManager = new PlayerManager(this);
        PermissionsManager permissionsManager = new PermissionsManager(this, playerManager);
        MoneyManager moneyManager = new MoneyManager(this, playerManager);
        ScoreboardManager scoreboardManager = new ScoreboardManager(this, playerManager, moneyManager, permissionsManager);
        Bukkit.getPluginManager().registerEvents(playerManager, this);
        Bukkit.getPluginManager().registerEvents(scoreboardManager, this);


        //Listener
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new HungerListener(), this);
        Bukkit.getPluginManager().registerEvents(new WeatherListener(), this);
        Bukkit.getPluginManager().registerEvents(new PingListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(playerManager, permissionsManager), this);
        Bukkit.getPluginManager().registerEvents(new CommandListener(), this);

        //Command
        getCommand("contact").setExecutor(new ContactCommand());
        getCommand("plugins").setExecutor(new PluginsCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("ping").setExecutor(new PingCommand());
        getCommand("money").setExecutor(new MoneyCommand(moneyManager));
        // admin
        getCommand("status").setExecutor(new StatusCommand(this, permissionsManager));
        getCommand("perm").setExecutor(new PermCommand(permissionsManager));
        getCommand("moneygive").setExecutor(new MoneyGiveCommand(moneyManager, permissionsManager));


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
