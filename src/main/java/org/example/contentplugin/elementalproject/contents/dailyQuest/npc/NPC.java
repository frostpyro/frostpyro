package org.example.contentplugin.elementalproject.contents.dailyQuest.npc;


import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

public class NPC {
    private String name;
    private Entity entity;
    public NPC(String name){
        this.name = name;
    }

    public Entity npcEntity(){
        if(entity != null){
            return entity;
        }

        return null;
    }
}
