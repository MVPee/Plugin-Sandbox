package be.mvpee.command;

import be.mvpee.manager.PermissionsManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PnjCommand implements CommandExecutor {

    private PermissionsManager perm;

    public PnjCommand(PermissionsManager perm) {
        this.perm = perm;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (perm.hasPerm(player, 4, false)) {
                if (args[0].equalsIgnoreCase("create")) {
                    if (args.length >= 2) {
                        Location npcLocation = player.getLocation();

                        StringBuilder name = new StringBuilder();
                        for (int i = 1; i < args.length; i++)
                            name.append(args[i]).append(" ");
                        IronGolem npcEntity = (IronGolem) npcLocation.getWorld().spawnEntity(npcLocation, EntityType.IRON_GOLEM);
                        npcEntity.setCustomNameVisible(true);
                        npcEntity.setCustomName(ChatColor.GREEN + name.toString() + ChatColor.GRAY + "(Click to Join)");
                        npcEntity.setAI(false);
                        npcEntity.setCollidable(false);
                        npcEntity.setInvulnerable(true);

                        player.sendMessage(ChatColor.GREEN + "Succes.");
                    } else {
                        player.sendMessage(ChatColor.GRAY + "Usage: /pnj create|delete <name>.");
                    }
                }
                else if (args[0].equalsIgnoreCase("delete")) {
                    ItemStack item = new ItemStack(Material.STICK);
                    ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.addEnchant(Enchantment.LUCK, 0, true);
                    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    itemMeta.setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "PNJ Remover");
                    item.setItemMeta(itemMeta);
                    player.getInventory().addItem(item);
                } else {
                    player.sendMessage(ChatColor.GRAY + "Usage: /pnj create|delete <name>.");
                }
            }
        }
        return false;
    }

}
