package org.example.contentplugin.elementalproject.contents.playerSkill;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class EntityGet {
    public Entity entity(Player p, double range, double radius, Location location){
        for(Entity entity : p.getNearbyEntities(radius, radius, radius)){

            double entityPos = entity.getLocation().distance(location);

            if(entityPos > range) continue;

            return entity;
        }
        return null;
    }
}