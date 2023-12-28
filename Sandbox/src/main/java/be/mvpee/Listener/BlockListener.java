package be.mvpee.Listener;

import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (!player.isOp()) {
            e.getPlayer().sendMessage(ChatColor.RED +"You can't do that!");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (!player.isOp()) {
            e.getPlayer().sendMessage(ChatColor.RED +"You can't do that!");
            e.setCancelled(true);
        }
    }

}
