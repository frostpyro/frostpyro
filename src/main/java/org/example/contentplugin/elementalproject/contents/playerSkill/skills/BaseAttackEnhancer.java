package org.example.contentplugin.elementalproject.contents.playerSkill.skills;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BaseAttackEnhancer {
    private Map<UUID, Integer> enhance = new HashMap<>();

    public int getCheck(Player p){
        return enhance.get(p.getUniqueId());
    }

    public void setEnhance(Player p, int val){
        enhance.put(p.getUniqueId(), val);
    }
}
