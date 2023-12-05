package org.example.contentplugin.elementalproject.contents.playerSkill.items.martial;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;
import org.example.contentplugin.elementalproject.ElementalProject;

import java.util.Set;
import java.util.UUID;

public class SkillEffectEarthMar {
    public void earthMarBase(Player p, Set<UUID> entitySet){
        ItemDisplay display = (ItemDisplay) p.getWorld().spawnEntity(p.getLocation().add(p.getLocation().getDirection().multiply(2)).add(0, 1.2,0), EntityType.ITEM_DISPLAY);
        ItemStack itemStack = new ItemStack(Material.BROWN_DYE);
        Transformation transformation = display.getTransformation();
        transformation.getScale().set(10f);
        display.setTransformation(transformation);
        display.setBrightness(new Display.Brightness(15, 15));
        for(int i = 1; i <= 10; i++){
            int temp = i + 1000;
            new BukkitRunnable(){
                @Override
                public void run() {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setCustomModelData(temp);
                    itemStack.setItemMeta(itemMeta);
                    display.setItemStack(itemStack);
                }
            }.runTaskLater(ElementalProject.getPlugin(), i);

        }
        for(int i = 0; i<=1; i++){
            new BukkitRunnable(){
                @Override
                public void run() {
                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1, 1);
                    for(Entity entity : display.getNearbyEntities(5,5,5)){
                        if(entity == p) continue;
                        if(!(entity instanceof LivingEntity)) continue;
                        if(entity.getLocation().distance(display.getLocation()) > 4) continue;
                        entitySet.add(entity.getUniqueId());
                        ((LivingEntity) entity).damage(5, p);
                    }
                    display.teleport(p.getLocation().add(p.getLocation().getDirection().multiply(2)).add(0, 1.2,0));
                }
            }.runTaskLater(ElementalProject.getPlugin(), i*5L);
        }
        new BukkitRunnable(){
            @Override
            public void run() {
                display.remove();
            }
        }.runTaskLater(ElementalProject.getPlugin(), 11);
    }
    public void earthMarSkill1(Player p, Set<UUID> entitySet){

    }
    public void earthMarSkill2(Player p, Set<UUID> entitySet){

    }
    public void earthMarSkill3(Player p, Set<UUID> entitySet){

    }
    public void earthMarUlt(Player p, Set<UUID> entitySet){
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 6, 0);
        ItemDisplay display = (ItemDisplay) p.getWorld().spawnEntity(p.getLocation(), EntityType.ITEM_DISPLAY);
        ItemStack itemStack = new ItemStack(Material.BROWN_DYE);
        Transformation transformation = display.getTransformation();
        transformation.getScale().set(34f);
        display.setRotation(0, 0);
        display.setTransformation(transformation);
        display.setBrightness(new Display.Brightness(15, 15));
        for(int i = 1; i <= 5; i++){
            int temp1 = i + 1030;
            new BukkitRunnable(){
                @Override
                public void run() {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setCustomModelData(temp1);
                    itemStack.setItemMeta(itemMeta);
                    display.setItemStack(itemStack);
                }
            }.runTaskLater(ElementalProject.getPlugin(), i*2);
        }

        new BukkitRunnable(){
            @Override
            public void run() {
                display.remove();
            }
        }.runTaskLater(ElementalProject.getPlugin(), 11);

        for(int i = 0; i <= 5; i ++){
            int temp = i * 3;
            double y = p.getLocation().getY();
            new BukkitRunnable(){
                @Override
                public void run() {
                    for(double i = 0; i <= 2.0; i += 0.01){
                        double x = p.getLocation().getX() + (temp*Math.sin(Math.PI * i));
                        double z = p.getLocation().getZ() + (temp*Math.cos(Math.PI * i));

                        p.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, x,y,z, 0);
                    }
                }
            }.runTaskLater(ElementalProject.getPlugin(), i);
        }

        for(Entity entity : display.getNearbyEntities(15, 3, 15)){
            if(entity == p) continue;
            if(!(entity instanceof LivingEntity)) continue;
            entitySet.add(entity.getUniqueId());
            ((LivingEntity)entity).damage(15, p);
            ((LivingEntity)entity).addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 3*20, 2));
        }
    }
}
