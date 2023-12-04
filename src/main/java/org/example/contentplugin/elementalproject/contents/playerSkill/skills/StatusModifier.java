package org.example.contentplugin.elementalproject.contents.playerSkill.skills;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * class about sharing values
 */
public class StatusModifier {
    private static Map<UUID, double[]> statMod = new HashMap<>();

    public static double getInt(Player p){
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
        double[] stats = statMod.getOrDefault(p.getUniqueId(), new double[3]);
        return stats[0] != 0;
    }

    public static boolean deactivated(Player p){
        statMod.computeIfAbsent(p.getUniqueId(), k -> new double[3]);
        return statMod.get(p.getUniqueId())[0] == 0;
    }

    public static void cancelMove(Player p){
        statMod.get(p.getUniqueId())[1] = 1;
    }

    public static void activeMove(Player p){
        statMod.get(p.getUniqueId())[1] = 0;
    }

    public static boolean moveAble(Player p){
        statMod.computeIfAbsent(p.getUniqueId(), k -> new double[3]);
        return statMod.get(p.getUniqueId())[1] == 0;
    }

    public static void attackFast(Player p, double speed){
        statMod.computeIfAbsent(p.getUniqueId(), k -> new double[3]);
        if(speed + statMod.get(p.getUniqueId())[2] < 1) statMod.get(p.getUniqueId())[2] = 1;
        statMod.get(p.getUniqueId())[2] = speed + statMod.get(p.getUniqueId())[2];
    }

    public static double attackSpeed(Player p){
        if(statMod.get(p.getUniqueId())[2] == 0||statMod.get(p.getUniqueId())[2] < 1) {
            statMod.get(p.getUniqueId())[2] = 1;
            return 1;
        }
        return statMod.get(p.getUniqueId())[2];
    }
}
