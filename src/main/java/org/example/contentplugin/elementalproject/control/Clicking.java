package org.example.contentplugin.elementalproject.control;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;

import java.util.*;

public abstract class Clicking {

    private Map<UUID, Integer> manaCount = new HashMap<>();

    private Map<UUID, int[]> count = new HashMap<>();

    public int getManaAmount(Player p, PlayerStat playerStat){

        return manaCount.getOrDefault(p.getUniqueId(), playerStat.getMana());

    }

    public void manaReset(Player p, PlayerStat playerStat){
        int mana = getManaAmount(p, playerStat);
        if(mana >= playerStat.getMana()) return;
        Bukkit.getScheduler().runTaskLater(ElementalProject.getPlugin(), ()->{
            Bukkit.getScheduler().scheduleSyncRepeatingTask(ElementalProject.getPlugin(), ()->{
                int manaFinal = mana;
                if(getManaAmount(p, playerStat) > playerStat.getMana()) return;
                manaFinal++;
                manaCount.put(p.getUniqueId(), manaFinal);
            },0,1);
        }, 20);
    }

    public void manaConsume(Player p, PlayerStat playerStat, int consume){
        int mana = getManaAmount(p, playerStat);
        if(mana < consume) return;

        mana -= consume;
        manaCount.put(p.getUniqueId(), mana);
    }
    public abstract void clicking(PlayerInteractEvent event, PlayerStat playerStat);
    public void sequence(PlayerInteractEvent event){
        Player p = event.getPlayer();
        Action a = event.getAction();
        World w = p.getWorld();
        int[] array = count.getOrDefault(p.getUniqueId(), new int[3]);
        switch (a){
            case RIGHT_CLICK_AIR -> {
                if(array[2] == 0 && array[0] != 0 && array[1] != 0){
                    array[2] = 2;
                    w.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1, 3);
                    count.put(p.getUniqueId(), array);
                }
                if(array[1] == 0 && array[0] != 0){
                    array[1] = 2;
                    w.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1, 2);
                    count.put(p.getUniqueId(), array);
                }
                if(array[0] == 0){
                    array[0] = 2;
                    w.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                    count.put(p.getUniqueId(), array);
                }
            }
            case LEFT_CLICK_AIR -> {
                if(array[2] == 0 && array[1] != 0 && array[0] != 0){
                    array[2] = 1;
                    w.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1, 3);
                    count.put(p.getUniqueId(), array);
                }
                if(array[1] == 0 && array[0] != 0){
                    array[1] = 1;
                    w.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1, 2);
                    count.put(p.getUniqueId(), array);
                }
            }
        }
        if(!Arrays.equals(array, new int[3])){
            Bukkit.getScheduler().runTaskLater(ElementalProject.getPlugin(), ()-> count.put(p.getUniqueId(), new int[3]),40);
        }
    }
    public boolean sequent(PlayerInteractEvent event, Sequence sequence){
        Player p = event.getPlayer();
        int[] array = count.getOrDefault(p.getUniqueId(), new int[3]);

        switch (sequence){
            case L -> {
                if(Arrays.equals(array, new int[]{0, 0, 0})) {
                    count.put(p.getUniqueId(), new int[3]);
                    return true;
                }
            }
            case RLL -> {
                if(Arrays.equals(array, new int[]{2, 1, 1})){
                    count.put(p.getUniqueId(), new int[3]);
                    return true;
                }
            }
            case RLR -> {
                if(Arrays.equals(array, new int[]{2, 1, 2})){
                    count.put(p.getUniqueId(), new int[3]);
                    return true;
                }
            }
            case RRL ->{
                if(Arrays.equals(array, new int[]{2, 2, 1})){
                    count.put(p.getUniqueId(), new int[3]);
                    return true;
                }
            }
            case RRR -> {
                if(Arrays.equals(array, new int[]{2, 2, 2})){
                    count.put(p.getUniqueId(), new int[3]);
                    return true;
                }
            }
        }
        return false;
    }
}
