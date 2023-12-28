package be.mvpee.ToolsMenu;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.ChatColor;

public class ToolsMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.RED.toString() + ChatColor.BOLD + "Tool Menu!")) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            switch (e.getRawSlot()) {
                case 0:
                    player.setGameMode(GameMode.CREATIVE);
                    break;
                case 1:
                    player.setGameMode(GameMode.SURVIVAL);
                    break;
                case 2:
                    player.setGameMode(GameMode.ADVENTURE);
                    break;
                case 8:
                    player.closeInventory();
                    return;
                default:
                    return;
            }
            player.closeInventory();
        }
    }
}
