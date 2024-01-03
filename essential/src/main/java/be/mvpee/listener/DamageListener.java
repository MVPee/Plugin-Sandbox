package be.mvpee.listener;

import be.mvpee.manager.PermissionsManager;
import org.bukkit.entity.IronGolem;
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
            EntityDamageByEntityEvent entity = (EntityDamageByEntityEvent) e;
            if (entity.getDamager() instanceof Player) {
                if (!perm.hasPerm((Player) entity.getDamager(), 3, true)) {
                    e.setCancelled(true);
                }
            }
            if (entity instanceof IronGolem) {
                e.setCancelled(true);
            }
        }
    }
}
