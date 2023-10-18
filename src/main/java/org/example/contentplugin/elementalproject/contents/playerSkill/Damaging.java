package org.example.contentplugin.elementalproject.contents.playerSkill;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.contents.Elements;

import javax.annotation.Nullable;
import java.util.List;

public class Damaging {

    public Damaging(){

    }


    public void damageEntity(Player p, Location location,double range, double damageAmount, boolean knockBack){
        List<Entity> entityList = p.getNearbyEntities(range, range, range);
        for(Entity entity : entityList){
            if(entity.getCustomName().equals("skillCheck")) continue;
            if(!(entity instanceof LivingEntity)) continue;

            Location entityLoc = entity.getLocation();
            double distance = entityLoc.distance(location);

            if(distance > range) continue;

            ((LivingEntity)entity).damage(damageAmount, p);
            if(knockBack){
                entity.setVelocity(p.getLocation().getDirection().multiply(-0.2).add(new Vector(0, 0.2,0)));
            }
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
