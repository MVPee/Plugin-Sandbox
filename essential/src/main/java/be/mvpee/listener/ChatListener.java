package be.mvpee.listener;

import be.mvpee.manager.PermissionsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private PermissionsManager perm;

    public ChatListener(PermissionsManager perm) {
        this.perm = perm;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        e.setCancelled(true);
        ChatColor rankColor = perm.getPermsColor(player);
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.sendMessage(rankColor.toString() + ChatColor.BOLD + player.getName() + ": " + ChatColor.WHITE + e.getMessage());
        }
    }
}
