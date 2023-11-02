package org.example.contentplugin.elementalproject.contents.playerSkill;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
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
