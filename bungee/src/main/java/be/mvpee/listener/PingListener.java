package be.mvpee.listener;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PingListener implements Listener {

    private Favicon favicon;

    public PingListener(Favicon favicon) {
        this.favicon = favicon;
    }

    @EventHandler
    public void onProxyPing(ProxyPingEvent e) {
        ServerPing ping = e.getResponse();
        ping.setDescription(
                ChatColor.GOLD.toString() + ChatColor.BOLD +    "            MVPee " +
                ChatColor.GREEN +                               "Network " +
                ChatColor.GRAY +                                "[" + ChatColor.WHITE + "1.20.4" + ChatColor.GRAY + "]\n" +
                ChatColor.RED + ChatColor.BOLD  +               "           MVPee testing server..."
        );
        ping.setPlayers(new ServerPing.Players(1000, ProxyServer.getInstance().getOnlineCount(), ping.getPlayers().getSample()));
        ping.setVersion(new ServerPing.Protocol("Connect with 1.20.1", 763));
        ping.setFavicon(favicon);
    }
}
