package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.ultimate;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.SNS;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.StatusModifier;

import java.util.Set;
import java.util.UUID;

public class FireUltSword implements SNS {
    private final int sec;



    public FireUltSword(int sec){
        this.sec = sec;
    }
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        StatusModifier.cancelMove(player);

        new BukkitRunnable(){
            @Override
            public void run() {

                ItemDisplay display = (ItemDisplay)player.getWorld().spawnEntity(player.getLocation().add(new Vector(0, 4, 0)), EntityType.ITEM_DISPLAY);

                display.setCustomName("fireUlt");

                ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
                ItemMeta meta = itemStack.getItemMeta();
                meta.setCustomModelData(1);
                itemStack.setItemMeta(meta);
                display.setItemStack(itemStack);
                display.setDisplayWidth(5f);
                display.setDisplayHeight(5f);
                display.setRotation(0, 180);
                StatusModifier.activeMove(player);
                StatusModifier.deactivateEnhance(player);
            }
        }.runTaskLater(ElementalProject.getPlugin(), sec * 20L);
    }
}
