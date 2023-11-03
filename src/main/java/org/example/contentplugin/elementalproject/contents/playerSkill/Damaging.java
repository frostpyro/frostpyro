package org.example.contentplugin.elementalproject.contents.playerSkill;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.example.contentplugin.elementalproject.ElementalProject;

import java.util.Set;
import java.util.UUID;

public class Damaging {

    public Damaging(){

    }

    public void damageMethod(Entity p, Set<UUID> entitySet){
        for(Entity entity : p.getNearbyEntities(5,5,5)){
            if(!(entity instanceof LivingEntity)) continue;
            entitySet.add(entity.getUniqueId());
            ((LivingEntity)entity).damage(5,p);
        }
    }

    public void reactDamage1(Entity p, LivingEntity entity, Set<UUID> entitySet, double damage){
        entity.damage(damage, p);
        entitySet.add(entity.getUniqueId());
    }

    public void reactSlow(LivingEntity entity, int duration, int amplifier){
        PotionEffect effect = new PotionEffect(PotionEffectType.SLOW, duration, amplifier);
        effect.apply(entity);
    }

    public void reactDamage2(Entity p, LivingEntity entity, Set<UUID> entitySet, double damage, int second){
        for(int i = 0; i <= second; i++){
            new BukkitRunnable(){
                @Override
                public void run() {
                    entity.damage(damage, p);
                    entitySet.add(entity.getUniqueId());
                }
            }.runTaskLater(ElementalProject.getPlugin(), 20L*i);
        }
    }

    public Location skillLocation(Player p, double multiply1, double multiplyCross){
        Location loc = p.getLocation();
        Vector crossVec = new Vector(0,1,0);

        Vector vector = loc.getDirection().normalize();

        Vector finalVec = vector.clone().crossProduct(crossVec).normalize();

        return loc.add(vector.multiply(multiply1)).add(finalVec.multiply(multiplyCross));
    }
    public void spawnParticle(Particle particle , Location location, World w, int delayTask, int particleNum){
        Bukkit.getScheduler().runTaskLater(ElementalProject.getPlugin(), ()-> w.spawnParticle(particle, location, particleNum),delayTask);
    }
}
