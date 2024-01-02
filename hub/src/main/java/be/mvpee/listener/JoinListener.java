package be.mvpee.listener;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

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
        player.teleport(new Location(player.getWorld(), 0.5, 100, 0.5, 270, 0));
        if (!player.hasPlayedBefore()) {
            firstJoin(player);
        }
        player.getInventory().clear();

        player.setExp(1);

        player.getInventory().setItem(4, Item.TELEPORT.getItems());
        player.getInventory().setItem(8, Item.LINK.getItems());
    }

}
