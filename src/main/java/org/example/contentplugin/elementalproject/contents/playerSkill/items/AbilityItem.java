package org.example.contentplugin.elementalproject.contents.playerSkill.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AbilityItem {
    public ItemStack abilityItem(Material material, ChatColor color, String name, int model){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(model);
        itemMeta.setDisplayName(color+name);
        itemMeta.setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
