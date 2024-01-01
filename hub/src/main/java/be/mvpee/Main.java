package be.mvpee;

import be.mvpee.listener.DamageListener;
import be.mvpee.listener.RespawnListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import be.mvpee.listener.JoinListener;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("HUB ONN");

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new RespawnListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
    }

}
