package org.example.contentplugin.elementalproject.contents.playerSkill.items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import java.util.Set;
import java.util.UUID;

public class SkillEffect {
    public void fireUltSkill(Player player, Set<UUID> entitySet){
        ItemDisplay display = (ItemDisplay)player.getWorld().spawnEntity(player.getLocation().add(new Vector(0, 6, 0)), EntityType.ITEM_DISPLAY);

        display.setCustomName("fireUltSword");

        ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setCustomModelData(1);
        itemStack.setItemMeta(meta);
        display.setItemStack(itemStack);
        Transformation transformation = display.getTransformation();



        display.setRotation(0, 90);
        transformation.getLeftRotation().x = 1;
        transformation.getScale().set(3f);

        display.setTransformation(transformation);
        display.setBrightness(new Display.Brightness(15, 15));
        Location location = display.getLocation();
        World w = display.getWorld();
        new BukkitRunnable(){
            @Override
            public void run() {
                if(display.isDead()){
                    cancel();
                }

                double y = location.getY()-5.9;

                for(Entity entity : display.getNearbyEntities(10, 10, 10)){
                    if(entity.equals(player)) continue;

                    if(!(entity instanceof LivingEntity)) continue;

                    entitySet.add(entity.getUniqueId());
                    ((LivingEntity)entity).damage(5, player);
                }
                for(double i = 0; i <= 2; i += 0.01){
                    double x = location.getX() + (10*Math.sin(Math.PI*i));
                    double z = location.getZ() + (10 * Math.cos(Math.PI * i));
                    w.spawnParticle(Particle.FLAME, x,y,z, 0);
                }
            }
        }.runTaskTimer(ElementalProject.getPlugin(), 20L, 20L);
        new BukkitRunnable(){
            @Override
            public void run() {
                display.remove();
                double y = location.getY()-5.9;
                for(double i = 0; i <= 2; i += 0.01){
                    double x = location.getX() + (10*Math.sin(Math.PI*i));
                    double z = location.getZ() + (10 * Math.cos(Math.PI * i));
                    w.spawnParticle(Particle.FLAME, x,y,z, 1);
                }
            }
        }.runTaskLater(ElementalProject.getPlugin(), 180);


    }
}
