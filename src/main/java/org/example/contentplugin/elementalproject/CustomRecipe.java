package org.example.contentplugin.elementalproject;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.example.contentplugin.elementalproject.contents.playerSkill.items.AbilityItem;

public class CustomRecipe {
    static AbilityItem abItem = new AbilityItem();
    public static void register(){
        ItemStack itemSword = abItem.abilityItem(Material.NETHERITE_SWORD, ChatColor.LIGHT_PURPLE, "규칙의 형태" , 1);
        ItemStack itemBow = abItem.abilityItem(Material.WOODEN_SWORD, ChatColor.GREEN, "진화의 형태", 1);
        ItemStack itemGauntlet = abItem.abilityItem(Material.DIAMOND_SWORD, ChatColor.RED, "투쟁의 형태", 1);
        ItemStack itemStaff = abItem.abilityItem(Material.WOODEN_HOE, ChatColor.AQUA, "지혜의 형태", 1);
        NamespacedKey key1, key2, key3, key4;
        key1 = new NamespacedKey(ElementalProject.getPlugin(), "sword");
        key2 = new NamespacedKey(ElementalProject.getPlugin(), "bow");
        key3 = new NamespacedKey(ElementalProject.getPlugin(), "gauntlet");
        key4 = new NamespacedKey(ElementalProject.getPlugin(), "staff");
        ShapedRecipe sword, bow, gauntlet, staff;
        sword = new ShapedRecipe(key1, itemSword);
        bow = new ShapedRecipe(key2, itemBow);
        gauntlet = new ShapedRecipe(key3, itemGauntlet);
        staff = new ShapedRecipe(key4, itemStaff);
    }

}
