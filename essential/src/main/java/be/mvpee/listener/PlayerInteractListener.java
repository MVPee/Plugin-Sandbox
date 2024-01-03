package be.mvpee.listener;

import be.mvpee.manager.PermissionsManager;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteractListener implements Listener {

    private PermissionsManager perm;

    public PlayerInteractListener(PermissionsManager perm) {
        this.perm = perm;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (perm.hasPerm(player, 4, true)) {
            ItemStack item = player.getInventory().getItemInMainHand();

            if (item != null && item.getType() == Material.STICK && item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                if (meta != null && meta.hasDisplayName() &&
                        meta.getDisplayName().equals(ChatColor.RED.toString() + ChatColor.BOLD + "PNJ Remover")) {

                    Entity targetEntity = event.getRightClicked();
                    if (targetEntity != null) {
                        targetEntity.remove();
                        player.sendMessage(ChatColor.GREEN + "Remove entity successfully.");
                    }
                }
            }
        }
    }

}
