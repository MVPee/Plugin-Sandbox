package be.mvpee;

import be.mvpee.command.HubCommand;
import be.mvpee.listener.PingListener;
import be.mvpee.manager.DbManager;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class bungee extends Plugin{

    private DbManager db;
    private Favicon favicon;

    @Override
    public void onEnable() {

        db = new DbManager("mongodb://localhost:27017", "sandbox", "players");
        favicon = Favicon.create("icon.png");

        ProxyServer.getInstance().getPluginManager().registerListener(this, new PingListener(favicon));
        ProxyServer.getInstance().getPluginManager().registerListener(this, db);
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new HubCommand());
    }

    @Override
    public void onDisable() {
        if (db.getClient() != null) {
            db.getClient().close();
        }
    }
}
