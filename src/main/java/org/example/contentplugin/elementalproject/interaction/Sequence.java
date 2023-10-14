package org.example.contentplugin.elementalproject.interaction;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.contentplugin.elementalproject.ElementalProject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Sequence {
    private Map<UUID, int[]> sequent = new HashMap<UUID, int[]>();



    public int[] getArray(Player player){
        return sequent.get(player.getUniqueId());
    }

    public void setArray(Player player, int setting){
        int[] array = sequent.getOrDefault(player.getUniqueId(), new int[3]);
        switch(setting){
            case 1 ->{
                if(array[2] == 0 && array[1] != 0 && array[0] != 0){
                    array[2] = 1;
                    sequent.put(player.getUniqueId(), array);
                }
                if(array[1] == 0 && array[0] != 0){
                    array[1] = 1;
                    sequent.put(player.getUniqueId(), array);
                }
            }

            case -1 ->{
                if(array[2] == 0 && array[0] != 0 && array[1] != 0){
                    array[2] = -1;
                    sequent.put(player.getUniqueId(), array);
                }
                if(array[1] == 0 && array[0] != 0){
                    array[1] = -1;
                    sequent.put(player.getUniqueId(), array);
                }
                if(array[0] == 0){
                    array[0] = -1;
                    sequent.put(player.getUniqueId(), array);
                }
            }
        }
    }
    protected void resetArray(boolean bool, Player player){
        new BukkitRunnable(){
            @Override
            public void run() {
                if(bool){
                    sequent.put(player.getUniqueId(), new int[3]);
                }
                else{
                    cancel();
                }
            }
        }.runTaskLater(ElementalProject.getPlugin(), 60L);
    }
}
