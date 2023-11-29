package org.example.contentplugin.elementalproject.contents.playerSkill.items;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.example.contentplugin.elementalproject.ElementalProject;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class SkillEffectLightSword {
    public void lightBaseSword(Player p, Set<UUID> entitySet){
        Random random = new Random();
        ItemDisplay display = (ItemDisplay) p.getWorld().spawnEntity(p.getLocation().add(p.getLocation().getDirection().multiply(2)).add(0, 1.2,0), EntityType.ITEM_DISPLAY);
        int randomInt = random.nextInt(2) * 10;
        ItemStack itemStack = new ItemStack(Material.GLOWSTONE_DUST);
        for(int i = 1; i <= 10 ; i++){

            int temp = i + randomInt;
            new BukkitRunnable() {
                @Override
                public void run() {
                    ItemMeta meta = itemStack.getItemMeta();
                    meta.setCustomModelData(temp);
                    itemStack.setItemMeta(meta);
                    display.setItemStack(itemStack);
                }
            }.runTaskLater(ElementalProject.getPlugin(), i);
        }
        Transformation transformation = display.getTransformation();
        transformation.getScale().set(12f);
        Location location = p.getLocation();
        World w = p.getWorld();
        display.setTransformation(transformation);
        display.setBrightness(new Display.Brightness(15, 15));
        w.playSound(location, Sound.BLOCK_BEACON_ACTIVATE, 2, 6);
        for(Entity entity : display.getNearbyEntities(10,10,10)){
            if(entity == p) continue;
            if(!(entity instanceof LivingEntity)) continue;
            if((entity.getLocation().distance(display.getLocation()) > 4.5)) continue;

            entitySet.add(entity.getUniqueId());
            ((LivingEntity)entity).damage(5, p);
        }
        new BukkitRunnable(){
            @Override
            public void run() {
                display.remove();
            }
        }.runTaskLater(ElementalProject.getPlugin(), 9);
    }

    public void lightSkill1Sword(Player p, Set<UUID> entitySet){

    }

    public void lightSkill2Sword(Player p, Set<UUID> entitySet){

    }

    public void lightSkill3Sword(Player p, Set<UUID> entitySet){

    }

    public void lightUltSword(Player p, Set<UUID> entitySet){

    }
}
