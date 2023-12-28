package be.mvpee.ToolsMenu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public enum Tools {

    CREATIVE(
            Material.FEATHER,
            ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Creative",
            new String[]{"", ChatColor.GRAY + "Put the player in creative."}
    ),
    SURVIVAL(
            Material.CHAINMAIL_BOOTS,
            ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Survival",
            new String[]{"", ChatColor.GRAY + "Put the player in survival."}
    ),
    ADVENTURE(
            Material.BOOK,
            ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Adventure",
            new String[]{"", ChatColor.GRAY + "Put the player in adventure."}
    ),
    CLOSE(
            Material.BARRIER,
            ChatColor.RED.toString() + ChatColor.BOLD + "Close",
            new String[]{""}
    );

    private Material material;
    private String displayName;
    private String[] lore;

    Tools(Material material, String displayName, String[] lore) {
        this.material = material;
        this.displayName = displayName;
        this.lore = lore;
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(Arrays.asList(lore));
        item.setItemMeta(itemMeta);
        return item;

    }
}
