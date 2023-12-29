package be.mvpee.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.Arrays;
import java.util.List;

public class CommandListener implements Listener {

    private final List<String> blockedCommands = Arrays.asList(
            "/about", "/bukkit", "/bukkit:?", "/bukkit:about", "/bukkit:help", "/bukkit:pl", "/bukkit:plugins", "/bukkit:ver", "/bukkit.version", "/icanhasbukkit",
            "/list", "/me", "/minecraft:help", "/minecraft:list", "/minecraft:me", "/minecraft:msg", "/minecraft:random", "/minecraft:teammsg", "/minecraft:tell",
            "/minecraft:tm", "/minecraft:trigger", "/minecraft:w", "/msg", "/pl", "/random", "/teammsg", "/tell", "/tm", "/trigger", "/ver", "/version", "w"
    );

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = event.getPlayer();
            if (!player.isOp()) {
                String[] command = event.getMessage().split(" ");
                if (command.length > 0 && blockedCommands.contains(command[0].toLowerCase())) {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "You don't have the permissions for this command.");
                }
            }
        }
    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent event) {
        List<String> completions = event.getCompletions();
        String buffer = event.getBuffer();

        if (buffer.startsWith("/")) {
            String input = buffer.split(" ")[0].substring(1);

            if (blockedCommands.contains(input.toLowerCase())) {
                completions.clear();
                event.setCancelled(true);
            }
        }
    }
}