package org.example.contentplugin.elementalproject.contents.playerSkill;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.contents.Elements;

import javax.annotation.Nullable;

public class Damaging {
    private double rad;
    private double lNR;
    private double fNB;

    public Damaging(){

    }

    public Damaging(double rad, double lNR, double fNB){
        this.rad = rad;
        this.lNR = lNR;
        this.fNB = fNB;
    }
    public void damageMethod(Player p, Entity entity, double damageAmount){
        if(!(entity instanceof LivingEntity)) return;

        ((LivingEntity)entity).damage(damageAmount, p);
        entity.setVelocity(p.getLocation().getDirection().multiply(0.3));
    }
    public Location locationFunction(boolean skillType, Player p){
        Location loc = p.getLocation();
        Vector vector1 = loc.getDirection().normalize();
        Vector vector2 = vector1.clone().crossProduct(new Vector(0,1,0)).normalize();

        if(!skillType) return loc.add(vector2.multiply(fNB)).add(vector1.multiply(lNR));

        return loc.add(vector1.multiply(Math.sin(rad))).add(vector2.multiply(Math.cos(rad)));
    }
    public void spawnParticle(Particle particle , Location location, World w, int delayTask, int particleNum){
        Bukkit.getScheduler().runTaskLater(ElementalProject.getPlugin(), ()-> w.spawnParticle(particle, location, particleNum),delayTask);
    }
}
