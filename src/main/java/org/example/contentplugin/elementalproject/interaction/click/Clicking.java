package org.example.contentplugin.elementalproject.interaction.click;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.example.contentplugin.elementalproject.contents.playerSkill.Damaging;
import org.example.contentplugin.elementalproject.interaction.Sequence;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Clicking extends Sequence {
    private final Set<UUID> damagedEntity = new HashSet<>();

    Damaging damaging = new Damaging();

    @Deprecated
    public void leftClick(EntityDamageByEntityEvent event){
        Entity entity = event.getEntity();
        if(!(entity instanceof Interaction)) return;
        Player p = (Player) event.getDamager();
        p.sendMessage("testing");
        for(Entity entity1 : p.getNearbyEntities(10,10,10)){
            if(!(entity1 instanceof LivingEntity)) continue;
            ((LivingEntity)entity1).damage(5, p);
        }
        if(true) return;
        int[] array = getArray(p);
        setArray(p, 1);
        resetArray(checkOut(array[0]), p);
    }
    @Deprecated
    public void rightClick(PlayerInteractAtEntityEvent event){
        Player p = event.getPlayer();
        Entity entity = event.getRightClicked();
        if(!(entity instanceof Interaction)) return;
        p.sendMessage("testing2");
        if(true) return;
        int[] array = getArray(p);
        setArray(p, -1);
        resetArray(checkOut(array[0]), p);
    }

    public void clickEntityLeft(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();

        if (!(event.getDamager() instanceof Player)) return;

        Player p = (Player) event.getDamager();
        if (!damagedEntity.contains(entity.getUniqueId())) {
            for (Entity entity1 : p.getNearbyEntities(5, 5, 5)) {
                if (!(entity1 instanceof LivingEntity)) continue;
                damagedEntity.add(entity1.getUniqueId());
                ((LivingEntity)entity1).damage(5,p);
            }
            // TODO: write something
        } else {
            damagedEntity.remove(entity.getUniqueId());
        }
    }

    public void clickEntityRight(PlayerInteractAtEntityEvent event){
        Entity entity = event.getRightClicked();
        Player p = event.getPlayer();
        if(!(entity instanceof LivingEntity)) return;
    }

    public void clickAirLeft(final PlayerInteractEvent event){
        Player p = event.getPlayer();
        Action a = event.getAction();
        if(a != Action.LEFT_CLICK_AIR && a != Action.LEFT_CLICK_BLOCK) return;


        event.setCancelled(true);
    }

    public void clickAirRight(final PlayerInteractEvent event){
        Player p = event.getPlayer();
        Action a = event.getAction();
        if(a != Action.RIGHT_CLICK_AIR && a != Action.RIGHT_CLICK_BLOCK) return;


        event.setCancelled(true);
    }

    private boolean checkOut(int data){
        return data != 0;
    }

}
