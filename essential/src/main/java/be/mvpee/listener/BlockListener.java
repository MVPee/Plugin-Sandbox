package be.mvpee.listener;

import be.mvpee.manager.PermissionsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

    private PermissionsManager perm;

    public BlockListener(PermissionsManager perm) {
        this.perm = perm;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (perm.hasPerm(player, 3))
            return ;
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (perm.hasPerm(player, 3))
            return ;
        e.setCancelled(true);
    }
}
