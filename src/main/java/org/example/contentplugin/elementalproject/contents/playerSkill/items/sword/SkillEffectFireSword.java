package org.example.contentplugin.elementalproject.contents.playerSkill.items.sword;

import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.StatusModifier;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class SkillEffectFireSword {
    StatusModifier modifier = new StatusModifier();
    ConfigurationSection speedSec = ElementalProject.getPlugin().getConfig().getConfigurationSection("baseSpeed");
    public void fireUltSword(Player player, Set<UUID> entitySet){
        if(speedSec == null) return;

        ItemDisplay display = (ItemDisplay)player.getWorld().spawnEntity(player.getLocation().add(new Vector(0, 6, 0)), EntityType.ITEM_DISPLAY);

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
        double y = location.getY()-5.9;
        location.setY(y);

        for(Entity entity : player.getNearbyEntities(10, 3, 10)){
            if(entity == player) continue;
            if(!(entity instanceof LivingEntity)) continue;
            entitySet.add(entity.getUniqueId());
            ((LivingEntity)entity).damage(10, player);
        }

        w.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 6, 0);
        for(double i = 0; i <= 2; i += 0.01){
            double x = location.getX() + (Math.sin(Math.PI*i));
            double z = location.getZ() + ( Math.cos(Math.PI * i));
            w.spawnParticle(Particle.FLAME, x,y,z, 1);
            w.spawnParticle(Particle.EXPLOSION_LARGE, x,y,z, 1);
        }
        modifier.attackFast(player, speedSec.getDouble("melee"));
        new BukkitRunnable(){
            @Override
            public void run() {
                if(display.isDead()){
                    cancel();
                }
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
                w.playSound(location, Sound.ENTITY_BLAZE_SHOOT, 6, 0);
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
                modifier.attackFast(player, -speedSec.getDouble("melee"));
            }
        }.runTaskLater(ElementalProject.getPlugin(), 180);
    }

    public void fireBaseSword(Player p, Set<UUID> entitySet){
        Random random = new Random();
        ItemDisplay display = (ItemDisplay) p.getWorld().spawnEntity(p.getLocation().add(p.getLocation().getDirection().multiply(2)).add(0, 1.2,0), EntityType.ITEM_DISPLAY);

        int c = random.nextInt(3);
        ItemStack itemStack = new ItemStack(Material.COAL);
        Transformation transformation = display.getTransformation();
        transformation.getScale().set(12f);
        Location location = p.getLocation();

        switch(c){
            case 1 ->transformation.getRightRotation().z = 0.5f;
            case 2 ->transformation.getLeftRotation().z = -1;
        }

        World w = p.getWorld();

        display.setTransformation(transformation);
        display.setBrightness(new Display.Brightness(15, 15));
        w.playSound(location, Sound.ENTITY_BLAZE_SHOOT, 2, 1);
        for(Entity entity : display.getNearbyEntities(10,10,10)){
            if(entity == p) continue;
            if(!(entity instanceof LivingEntity)) continue;

            if((entity.getLocation().distance(display.getLocation()) > 4.5)) continue;

            entitySet.add(entity.getUniqueId());
            ((LivingEntity)entity).damage(5, p);
        }

        for(int i = 1; i <= 10; i++){
            int temp = i;
            new BukkitRunnable(){
                @Override
                public void run() {
                    ItemMeta meta = itemStack.getItemMeta();
                    meta.setCustomModelData(temp);
                    itemStack.setItemMeta(meta);
                    display.setItemStack(itemStack);
                }
            }.runTaskLater(ElementalProject.getPlugin(), i);
        }



        new BukkitRunnable(){
            @Override
            public void run() {
                display.remove();
            }
        }.runTaskLater(ElementalProject.getPlugin(), 9);
    }

    public void fireSkill1Sword(Player p, Set<UUID> entitySet){
        if(speedSec == null) return;


        World w = p.getWorld();

        w.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1, 0);
        modifier.attackFast(p, speedSec.getDouble("melee"));
        new BukkitRunnable(){
            @Override
            public void run() {
                Location location = p.getLocation();
                if(modifier.attackSpeed(p) == 1){
                    cancel();
                }
                Random rand1 = new Random();

                int xRan = rand1.nextInt(3) - 1;
                int zRan = rand1.nextInt(3) - 1;
                int yRan = rand1.nextInt(3);

                double y = location.getY() + yRan;
                double x = location.getX() + xRan;
                double z = location.getZ() + zRan;

                w.spawnParticle(Particle.FLAME, x,y,z, 0);
            }
        }.runTaskTimer(ElementalProject.getPlugin(), 0L, 1L);

        new BukkitRunnable(){
            @Override
            public void run() {
                modifier.attackFast(p, -speedSec.getDouble("melee"));
            }
        }.runTaskLater(ElementalProject.getPlugin(), 50);
    }

    public void fireSkill2Sword(Player p, Set<UUID> entitySet){
        ItemDisplay display = (ItemDisplay) p.getWorld().spawnEntity(p.getLocation(), EntityType.ITEM_DISPLAY);

        ItemStack itemStack = new ItemStack(Material.COAL);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setCustomModelData(31);
        itemStack.setItemMeta(meta);
        display.setItemStack(itemStack);
        Transformation transformation = display.getTransformation();
        transformation.getScale().set(14f);

        World w = p.getWorld();
        display.setTransformation(transformation);
        display.setBrightness(new Display.Brightness(15, 15));

        new BukkitRunnable(){
            @Override
            public void run() {
                if(display.isDead()) cancel();

                display.teleport(p.getLocation());

                for(Entity entity : p.getNearbyEntities(4, 4, 4)){
                    if(entity == p) continue;

                    if(!(entity instanceof LivingEntity)) continue;

                    entitySet.add(entity.getUniqueId());

                    ((LivingEntity)entity).damage(5, p);
                }
                w.playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT,1, 3);
            }
        }.runTaskTimer(ElementalProject.getPlugin(), 0L, 1L);

        new BukkitRunnable(){
            @Override
            public void run() {
                display.remove();
            }
        }.runTaskLater(ElementalProject.getPlugin(), 20 * 3);
    }

    public void fireSkill3Sword(Player p, Set<UUID> entitySet){
        World world = p.getWorld();
        world.spawnParticle(Particle.EXPLOSION_LARGE, p.getLocation(), 0);
        world.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);

        for(long i = 0; i <= 5L; i++){
            new BukkitRunnable(){
                @Override
                public void run() {
                    Location location = p.getLocation();
                    World w = p.getWorld();
                    Random rand1 = new Random();
                    w.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,1,1);
                    int xRan = rand1.nextInt(3) - 1;
                    int zRan = rand1.nextInt(3) - 1;
                    int yRan = rand1.nextInt(3);

                    double y = location.getY() + yRan;
                    double x = location.getX() + xRan;
                    double z = location.getZ() + zRan;

                    w.spawnParticle(Particle.FLAME, x,y,z, 0);

                    for(Entity entity : p.getNearbyEntities(2,2,2)){
                        if(entity == p) continue;

                        if(!(entity instanceof LivingEntity)) continue;

                        entitySet.add(entity.getUniqueId());

                        ((LivingEntity)entity).damage(5, p);
                    }
                    p.setVelocity(p.getLocation().getDirection().multiply(1.5));
                }
            }.runTaskLater(ElementalProject.getPlugin(), i);
        }
        p.setVelocity(p.getLocation().getDirection().multiply(0));
    }
}
