package be.mvpee.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Location spawn = new Location(Bukkit.getWorld("world"), 0.5, 100, 0.5, 270, 0);
        e.setRespawnLocation(spawn);
    }
}
