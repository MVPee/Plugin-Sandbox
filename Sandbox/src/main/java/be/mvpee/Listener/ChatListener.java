package be.mvpee.Listener;

import be.mvpee.manager.PermissionsManager;
import be.mvpee.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final PlayerManager playerManager;
    private final PermissionsManager permissionsManager;

    public ChatListener(PlayerManager playerManager, PermissionsManager permissionsManager) {
        this.playerManager = playerManager;
        this.permissionsManager = permissionsManager;
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Player player = e.getPlayer();
        String originalMessage = e.getMessage();
        String formattedMessage;
        ChatColor codeColor = playerManager.getColorWithCode(permissionsManager.getPermissions(player.getName()));
        formattedMessage = codeColor.toString() + ChatColor.BOLD + player.getName() + ": " + ChatColor.WHITE + originalMessage;

        for (Player target : Bukkit.getOnlinePlayers()) {
            target.sendMessage(formattedMessage);
        }
    }
}