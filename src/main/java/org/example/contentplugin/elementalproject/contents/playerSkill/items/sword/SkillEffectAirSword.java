package org.example.contentplugin.elementalproject.contents.playerSkill.items.sword;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.example.contentplugin.elementalproject.ElementalProject;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class SkillEffectAirSword {
    public void airBaseSword(Player p, Set<UUID> entitySet){
        ItemDisplay display =(ItemDisplay) p.getWorld().spawnEntity(p.getLocation().add(p.getLocation().getDirection().multiply(2)).add(0, 1.2,0), EntityType.ITEM_DISPLAY);
        ItemStack itemStack = new ItemStack(Material.CYAN_DYE);
        Transformation transformation = display.getTransformation();
        transformation.getScale().set(12f);

        Random random = new Random();
        int template = 0;
        int c = random.nextInt(3);
        switch(c){
            case 0,1 -> template = c * 10;
            case 2 -> {
                display.setRotation(90, 90);
                transformation.getRightRotation().z = 0.7f;
            }
        }
        display.setTransformation(transformation);
        for(int i = 1; i <= 7; i++){
            int temp = i + template;
            new BukkitRunnable(){
                @Override
                public void run() {
                    ItemMeta meta = itemStack.getItemMeta();
                    meta.setCustomModelData(temp);
                    itemStack.setItemMeta(meta);
                    display.setItemStack(itemStack);
                }
            }.runTaskLater(ElementalProject.getPlugin(), temp);
        }
        for(Entity entity : display.getNearbyEntities(10,10,10)){
            if(entity == p) continue;
            if(!(entity instanceof LivingEntity)) continue;

            if(entity.getLocation().distance(display.getLocation()) > 4.5) continue;

            entitySet.add(entity.getUniqueId());
            ((LivingEntity)entity).damage(5, p);
        }
    }
    public void airEnhanceSword(Player p, Set<UUID> entitySet){
        ItemDisplay display =(ItemDisplay) p.getWorld().spawnEntity(p.getLocation(), EntityType.ITEM_DISPLAY);
    }
    public void airSkill1Sword(Player p, Set<UUID> entitySet){
        ItemDisplay display =(ItemDisplay) p.getWorld().spawnEntity(p.getLocation(), EntityType.ITEM_DISPLAY);
    }
    public void airSkill2Sword(Player p, Set<UUID> entitySet){
        ItemDisplay display =(ItemDisplay) p.getWorld().spawnEntity(p.getLocation(), EntityType.ITEM_DISPLAY);
    }
    public void airSkill3Sword(Player p, Set<UUID> entitySet){
        ItemDisplay display =(ItemDisplay) p.getWorld().spawnEntity(p.getLocation(), EntityType.ITEM_DISPLAY);
    }
    public void airUltSword(Player p, Set<UUID> entitySet){
        for(Entity entity : p.getNearbyEntities(2,2,2)){
            Location ent = entity.getLocation();
            Location pla = p.getLocation();
            Vector vector = (new Vector(ent.getX()-pla.getX(), ent.getY()-pla.getY(), ent.getZ()-pla.getZ())).normalize().multiply(4-ent.distance(pla));
            entity.setVelocity(vector);
        }
    }
}
