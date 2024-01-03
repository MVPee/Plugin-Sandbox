package be.mvpee.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player.isOp())
            return ;
        if (event.getClickedInventory() != null && event.getClickedInventory().equals(player.getInventory())) {
            event.setCancelled(true);
            player.updateInventory();
        }
    }
}
