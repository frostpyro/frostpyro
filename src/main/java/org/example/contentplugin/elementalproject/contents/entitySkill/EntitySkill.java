package org.example.contentplugin.elementalproject.contents.entitySkill;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.contentplugin.elementalproject.ElementalProject;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class EntitySkill {


    Set<UUID> coolDown = new HashSet<>();
    public void entitySkill(Entity entity, Entity targetEntity){
        PersistentDataContainer data = entity.getPersistentDataContainer();
        if(!data.has(ElementalProject.level(), PersistentDataType.INTEGER)) return;
        if(data.get(ElementalProject.level(), PersistentDataType.INTEGER) < 40) return;

        EntityType entityType = entity.getType();
        if(entity.isDead()){
            coolDown.remove(entity.getUniqueId());
            return;
        }
        new BukkitRunnable(){
            @Override
            public void run() {
                if(entity.getLocation().distance(targetEntity.getLocation()) > 20){
                    cancel();
                }
                if(!(targetEntity instanceof Player)) return;
                if(coolDown.contains(entity.getUniqueId())) return;
                switch(entityType){
                    case ZOMBIE -> {
                        ((LivingEntity)entity).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3, 3));
                        coolDown.add(entity.getUniqueId());
                        Bukkit.getScheduler().runTaskLater(ElementalProject.getPlugin(), ()->{
                            coolDown.remove(entity.getUniqueId());
                        }, 20 * 13);
                    }
                    case SKELETON -> {
                        for(int i = 0; i <= 40; i++){
                            Bukkit.getScheduler().runTaskLater(ElementalProject.getPlugin(), ()->{
                                Projectile projectile = (Projectile) entity.getWorld().spawnEntity(entity.getLocation().add(new Location(entity.getWorld(),0,1.5,0)), EntityType.ARROW);
                                projectile.setShooter((ProjectileSource) entity);
                                projectile.setGravity(true);
                                projectile.setVelocity(entity.getLocation().getDirection().multiply(10));
                            }, i);
                        }
                        coolDown.add(entity.getUniqueId());
                        Bukkit.getScheduler().runTaskLater(ElementalProject.getPlugin(), ()->{
                            coolDown.remove(entity.getUniqueId());
                        }, 20 * 20);
                    }
                    case CREEPER -> {
                        ((Creeper)entity).setExplosionRadius(7);
                        if(entity.isDead()){
                            cancel();
                        }
                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                entity.teleport(targetEntity.getLocation().add(targetEntity.getLocation().getDirection().multiply(-1)));
                                ((Creeper)entity).explode();
                                if(entity.isDead()){
                                    cancel();
                                }
                            }
                        }.runTaskLater(ElementalProject.getPlugin(), 120);
                    }case ZOMBIFIED_PIGLIN -> {


                        Bukkit.getScheduler().runTaskLater(ElementalProject.getPlugin(), ()->{
                            coolDown.remove(entity.getUniqueId());
                        }, 20 * 20);
                    }
                }
            }
        }.runTaskTimer(ElementalProject.getPlugin(), 0, 20);
    }
}
