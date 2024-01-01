package be.mvpee.listener;

import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class JoinListener implements Listener {

    private void firstJoin(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.sendMessage(ChatColor.WHITE.toString() + ChatColor.BOLD + "Welcome " + ChatColor.RED + ChatColor.BOLD + player.getName() +
                    ChatColor.WHITE + ChatColor.BOLD + " on " + ChatColor.GOLD + ChatColor.BOLD + "MVPee" + ChatColor.WHITE + ChatColor.BOLD + "'s server!");
        }
        Firework firework = player.getWorld().spawn(player.getPlayer().getLocation(), Firework.class);
        FireworkMeta meta = firework.getFireworkMeta();
        meta.addEffect(FireworkEffect.builder().withColor(Color.RED).withColor(Color.WHITE).withColor(Color.PURPLE).with(FireworkEffect.Type.BALL_LARGE).withFlicker().build());
        meta.setPower(1);
        firework.setFireworkMeta(meta);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player player = e.getPlayer();
        if (!player.hasPlayedBefore()) {
            firstJoin(player);
        }
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20);
        player.setFoodLevel(20);

        e.getPlayer().setPlayerListHeaderFooter(
                ChatColor.WHITE.toString() + ChatColor.BOLD + "  Welcome on " + ChatColor.GOLD + ChatColor.BOLD + "play.mvpee.be" + ChatColor.WHITE + ChatColor.BOLD + "!  ",
                ChatColor.YELLOW + "https://github.com/MVPee"
        );
    }

}
