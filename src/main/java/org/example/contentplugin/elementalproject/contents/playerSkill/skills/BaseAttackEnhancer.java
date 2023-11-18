package org.example.contentplugin.elementalproject.contents.playerSkill.skills;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BaseAttackEnhancer {
    private Map<UUID, Integer> enhance = new HashMap<>();

    private int getInt(Player p){
        return enhance.get(p.getUniqueId());
    }

    public void setEnhance(Player p, int val){
        if(val == 0){
            enhance.put(p.getUniqueId(), val);
        }
        else{
            enhance.put(p.getUniqueId(), 1);
        }
    }

    public boolean enhancer(Player p){
        enhance.putIfAbsent(p.getUniqueId(), 0);
        return enhance.get(p.getUniqueId())==0;
    }
}
