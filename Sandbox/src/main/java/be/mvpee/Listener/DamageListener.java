package be.mvpee.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                e.setCancelled(true);
            }
            if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                Location spawn = new Location(Bukkit.getWorld("world"), 0.5, 100, 0.5, 270, 0);
                e.getEntity().teleport(spawn);
                e.setCancelled(true);
            }
        }
        else {
            e.setCancelled(true);
        }
    }

}
