package be.mvpee;

import be.mvpee.command.TestCommand;
import be.mvpee.listener.BlockListener;
import be.mvpee.listener.ChatListener;
import be.mvpee.listener.JoinListener;
import be.mvpee.manager.DbManager;
import be.mvpee.manager.PermissionsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private DbManager db;

    @Override
    public void onEnable() {
        DbManager db = new DbManager("mongodb://localhost:27017", "sandbox", "players");
        PermissionsManager perm = new PermissionsManager(db);

        Bukkit.getPluginManager().registerEvents(new BlockListener(perm), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(perm), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);

        getCommand("test").setExecutor(new TestCommand(perm));
    }

    @Override
    public void onDisable() {
        if (db.getClient() != null) {
            db.getClient().close();
        }
    }
}
