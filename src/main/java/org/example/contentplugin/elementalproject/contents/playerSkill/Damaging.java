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

    public void damageMethod(Entity p, Set<UUID> entitySet, double damage){
        for(Entity entity : p.getNearbyEntities(5,5,5)){
            if(!(entity instanceof LivingEntity)) continue;
            entitySet.add(entity.getUniqueId());
            ((LivingEntity)entity).damage(damage,p);
        }
    }

    public void reactDamage1(Entity p, LivingEntity entity, Set<UUID> entitySet, double damage){
        entitySet.add(entity.getUniqueId());
        entity.damage(damage, p);
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
                    entitySet.add(entity.getUniqueId());
                    entity.damage(damage, p);
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
    public void windParticle(Entity entity, long tick){
        Location location = entity.getLocation();

        for(int i = 0, j = (int)tick; i <= tick; i++, j--){
            int k = i,l = j;
            new BukkitRunnable(){
                @Override
                public void run() {
                    Vector entityVec1 = new Vector(Math.sin(k/20.0*2*Math.PI), k /20.0, Math.cos(k/20.0*2*Math.PI));
                    Vector entityVec2 = new Vector(Math.sin(l/20.0*2*Math.PI), k /20.0, Math.cos(l/20.0*2*Math.PI));
                    entity.getWorld().spawnParticle(Particle.CLOUD, location.add(entityVec1),1,0);
                    entity.getWorld().spawnParticle(Particle.CLOUD, location.add(entityVec2),1,0);
                }
            }.runTaskLater(ElementalProject.getPlugin(), i);
        }
    }
}
