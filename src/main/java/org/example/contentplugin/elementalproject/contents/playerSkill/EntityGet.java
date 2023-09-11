package org.example.contentplugin.elementalproject.contents.playerSkill;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class EntityGet {
    public List<Entity> entity(Player p, double radius){
        return p.getNearbyEntities(radius, radius, radius);
    }
}