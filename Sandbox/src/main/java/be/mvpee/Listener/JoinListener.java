package be.mvpee.Listener;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
        Location spawn = new Location(Bukkit.getWorld("world"), 0.5, 100, 0.5, 270, 0);
        player.teleport(spawn);

        if (!player.hasPlayedBefore()) {
            firstJoin(player);
        }

        if (!player.getAllowFlight()) {
            player.setAllowFlight(true);
        }

        player.getInventory().clear();

        player.setFlying(true);
        player.setGameMode(GameMode.ADVENTURE);

        player.setHealth(20);
        player.setFoodLevel(20);

        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§l§6Have fun on MVPee's Server!"));

        e.getPlayer().setPlayerListHeaderFooter(
                ChatColor.WHITE.toString() + ChatColor.BOLD + "  Welcome on " + ChatColor.GOLD + ChatColor.BOLD + "play.mvpee.be" + ChatColor.WHITE + ChatColor.BOLD + "!  ",
                ChatColor.GREEN + "Ping " + e.getPlayer().getPing()
        );

        //e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2, true, false, true));
        e.getPlayer().removePotionEffect(PotionEffectType.SPEED);
    }
}