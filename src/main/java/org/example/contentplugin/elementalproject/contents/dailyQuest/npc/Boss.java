package org.example.contentplugin.elementalproject.contents.dailyQuest.npc;

import org.bukkit.Bukkit;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

public class Boss {
    private String name;
    private Entity entity;
    public Boss(String name){
        this.name = name;
    }
    public Entity bossEntity(){

        if(entity != null){
            return entity;
        }
        switch (name){

        }
        return entity;
    }
}
