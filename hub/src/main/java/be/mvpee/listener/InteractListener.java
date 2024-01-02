package be.mvpee.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.ChatColor;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class InteractListener implements Listener {

    private Plugin plugin;

    public InteractListener(Plugin plugin) {
        this.plugin = plugin;
    }

    public void sendPlayerToServer(Player player, String server) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("Connect");
            out.writeUTF(server);

            player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "The server is down for the moment...");
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (itemInHand.getType() == Material.COMPASS && event.getAction().name().contains("RIGHT_CLICK")) {
            sendPlayerToServer(player, "jump");
        }
        if (itemInHand.getType() == Material.NETHER_STAR && event.getAction().name().contains("RIGHT_CLICK")) {
            player.sendMessage(
                    ChatColor.GOLD.toString() + ChatColor.BOLD + "LINKS \n" +
                            " \n" +
                        ChatColor.RED + "Discord: " + ChatColor.WHITE + ".mvpee \n" +
                        ChatColor.RED + "Github: " + ChatColor.WHITE + "https://github.com/MVPee \n" +
                        ChatColor.RED + "Mail: " + ChatColor.WHITE + "me@mvpee.be \n"
            );
        }
    }

}
