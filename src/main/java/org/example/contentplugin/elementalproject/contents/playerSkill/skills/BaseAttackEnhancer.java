package org.example.contentplugin.elementalproject.contents.playerSkill.skills;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BaseAttackEnhancer {
    private static Map<UUID, Integer> enhance = new HashMap<>();

    public static int getInt(Player p){
        if(enhance.get(p.getUniqueId())==null){
            return 0;
        }
        return enhance.get(p.getUniqueId());
    }
    public static void activeEnhance(Player p, int type){
        p.sendMessage("activated check");
        enhance.put(p.getUniqueId(), type);
    }

    public static void deactivateEnhance(Player p){
        p.sendMessage("deactivated check");
        enhance.put(p.getUniqueId(), 0);
    }

    public static boolean activated(Player p){
        return enhance.getOrDefault(p.getUniqueId(), 0) != 0;
    }

    public static boolean deactivated(Player p){
        return enhance.getOrDefault(p.getUniqueId(), 0) == 0;
    }
}
