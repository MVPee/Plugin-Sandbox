package be.mvpee.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;

public class PingListener implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        e.setMaxPlayers(69);
        e.setMotd(
                ChatColor.GOLD.toString() + ChatColor.BOLD +    "            MVPee " +
                ChatColor.GREEN +                               "Network " +
                ChatColor.GRAY +                                 "[" + ChatColor.WHITE + "1.20.4" + ChatColor.GRAY + "]\n" +
                ChatColor.RED.toString() + ChatColor.BOLD  +    "           MVPee testing server..."
        );
        try {
            e.setServerIcon(Bukkit.loadServerIcon(new File("icon.png")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
