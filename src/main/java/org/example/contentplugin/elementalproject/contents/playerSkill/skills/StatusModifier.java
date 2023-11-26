package org.example.contentplugin.elementalproject.contents.playerSkill.skills;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatusModifier {
    private static Map<UUID, int[]> statMod = new HashMap<>();

    public static int getInt(Player p){
        if(statMod.get(p.getUniqueId())==null){
            return 0;
        }
        return statMod.get(p.getUniqueId())[0];
    }
    public static void activeEnhance(Player p){
        p.sendMessage("activated check");
        statMod.get(p.getUniqueId())[0] = 1;
    }

    public static void deactivateEnhance(Player p){
        p.sendMessage("deactivated check");
        statMod.get(p.getUniqueId())[0] = 0;
    }

    public static boolean activated(Player p){
        int[] stats = statMod.getOrDefault(p.getUniqueId(), new int[3]);
        return stats[0] != 0;
    }

    public static boolean deactivated(Player p){
        statMod.computeIfAbsent(p.getUniqueId(), k -> new int[3]);
        return statMod.get(p.getUniqueId())[0] == 0;
    }

    public static void cancelMove(Player p){
        statMod.get(p.getUniqueId())[1] = 1;
    }

    public static void activeMove(Player p){
        statMod.get(p.getUniqueId())[1] = 0;
    }

    public static boolean moveAble(Player p){
        statMod.computeIfAbsent(p.getUniqueId(), k -> new int[3]);
        return statMod.get(p.getUniqueId())[1] == 0;
    }
}
