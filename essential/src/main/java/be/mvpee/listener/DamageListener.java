package be.mvpee.listener;

import be.mvpee.manager.PermissionsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    private PermissionsManager perm;

    public DamageListener(PermissionsManager perm) {
        this.perm = perm;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent player = (EntityDamageByEntityEvent) e;
            if (player.getDamager() instanceof Player) {
                if (!perm.hasPerm((Player) player.getDamager(), 3, true)) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
