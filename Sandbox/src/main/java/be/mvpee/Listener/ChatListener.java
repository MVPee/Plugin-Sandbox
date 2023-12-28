package be.mvpee.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Player player = e.getPlayer();
        String originalMessage = e.getMessage();
        String formattedMessage;
        if (e.getPlayer().isOp())
            formattedMessage = ChatColor.RED.toString() + ChatColor.BOLD + player.getName() + ": " + ChatColor.WHITE + originalMessage;
        else
            formattedMessage = ChatColor.GOLD.toString() + ChatColor.BOLD + player.getName() + ": " + ChatColor.WHITE + originalMessage;

        for (Player target : Bukkit.getOnlinePlayers()) {
            target.sendMessage(formattedMessage);
        }
    }
}