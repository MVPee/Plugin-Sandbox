package be.mvpee.listener;

import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player player = e.getPlayer();
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20);
        player.setFoodLevel(20);

        e.getPlayer().setPlayerListHeaderFooter(
                ChatColor.WHITE.toString() + ChatColor.BOLD + "  Welcome on " + ChatColor.GOLD + ChatColor.BOLD + "play.mvpee.be" + ChatColor.WHITE + ChatColor.BOLD + "!  ",
                ChatColor.YELLOW + "https://github.com/MVPee"
        );
    }

}
