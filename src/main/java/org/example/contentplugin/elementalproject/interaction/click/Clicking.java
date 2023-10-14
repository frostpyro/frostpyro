package org.example.contentplugin.elementalproject.interaction.click;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.interaction.Sequence;
import org.example.contentplugin.elementalproject.listners.EntityInteraction;

public class Clicking extends Sequence {

    public void leftClick(EntityDamageByEntityEvent event){
        Entity entity = event.getEntity();
        if(entity instanceof Interaction||event.getDamager() == null) return;
        Player p = (Player) event.getDamager();
        if(true) return;
        int[] array = getArray(p);
        setArray(p, 1);
        resetArray(checkOut(array[0]), p);
    }

    public void rightClick(PlayerInteractAtEntityEvent event){
        Player p = event.getPlayer();
        Entity entity = event.getRightClicked();
        if(entity instanceof Interaction) return;
        if(true) return;
        int[] array = getArray(p);
        setArray(p, -1);
        resetArray(checkOut(array[0]), p);
    }


    private boolean checkOut(int data){
        return data != 0;
    }

}
