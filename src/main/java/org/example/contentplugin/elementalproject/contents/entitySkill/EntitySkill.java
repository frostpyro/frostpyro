package org.example.contentplugin.elementalproject.contents.entitySkill;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.example.contentplugin.elementalproject.ElementalProject;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class EntitySkill {
    Set<UUID> coolDown = new HashSet<>();
    public void entitySkill(Entity entity){
        EntityType entityType = entity.getType();

        switch(entityType){
            case ZOMBIE -> {
                if(coolDown.contains(entity.getUniqueId())) return;
                //TODO:add zombie skill
                coolDown.add(entity.getUniqueId());
                Bukkit.getScheduler().runTaskLater(ElementalProject.getPlugin(), ()->{
                    coolDown.remove(entity.getUniqueId());
                }, 20 * 10);
            }
            case SKELETON -> {
                if(coolDown.contains(entity.getUniqueId())) return;
                //TODO:add skeleton skill

                coolDown.add(entity.getUniqueId());
                Bukkit.getScheduler().runTaskLater(ElementalProject.getPlugin(), ()->{
                    coolDown.remove(entity.getUniqueId());
                }, 20 * 10);
            }
        }
    }
}
