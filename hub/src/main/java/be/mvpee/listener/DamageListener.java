package be.mvpee.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                e.getEntity().teleport(new Location(e.getEntity().getWorld(), 0.5, 100, 0.5, 270, 0));
            }
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                e.setCancelled(true);
            }
        }
    }
}
