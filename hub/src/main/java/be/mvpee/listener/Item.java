package be.mvpee.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public enum Item {

    TELEPORT(
            Material.COMPASS,
            ChatColor.GREEN.toString() + ChatColor.BOLD + "Jump Server " + ChatColor.GRAY + "(Right Click)",
            ChatColor.DARK_GRAY + "Teleport to the jump server."
    ),
    LINK(
            Material.NETHER_STAR,
            ChatColor.RED.toString() + ChatColor.BOLD + "My Links " + ChatColor.GRAY + "(Right Click)",
            ChatColor.DARK_GRAY + "Links to contact me."
    );

    private Material material;
    private String name;
    private String lore;

    Item(Material material, String name, String lore) {
        this.material = material;
        this.name = name;
        this.lore = lore;
    }

    public ItemStack getItems() {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(
                " \n",
                lore,
                " \n"
        ));
        itemMeta.addEnchant(Enchantment.LUCK, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        return item;
    }
}
