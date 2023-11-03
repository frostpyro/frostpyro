package org.example.contentplugin.elementalproject.interaction.click;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.example.contentplugin.elementalproject.contents.playerSkill.Damaging;
import org.example.contentplugin.elementalproject.interaction.Sequence;

import java.util.HashSet;
import java.util.Objects;
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
        int[] array = getArray(p);
        if(!itemCheck(p)) return;
        if(!damagedEntity.contains(entity.getUniqueId())) {
            if(array[0] == 0){
                damaging.damageMethod(p, damagedEntity);
            }
            else{
                setArray(p, 1);
                resetArray(checkOut(array[0]), p);
                event.setCancelled(true);
            }
        } else {
            damagedEntity.remove(entity.getUniqueId());
        }
    }

    public void clickEntityRight(PlayerInteractAtEntityEvent event){
        Entity entity = event.getRightClicked();
        Player p = event.getPlayer();
        if(!(entity instanceof LivingEntity)) return;
        if(!itemCheck(p)) return;
        int[] array = getArray(p);
        setArray(p, -1);
        resetArray(checkOut(array[0]), p);
    }

    public void clickAirLeft(final PlayerInteractEvent event){
        Player p = event.getPlayer();
        Action a = event.getAction();
        if(!itemCheck(p)) return;
        if(a != Action.LEFT_CLICK_AIR && a != Action.LEFT_CLICK_BLOCK) return;
        int[] array = getArray(p);
        if(array[0] == 0){
            damaging.damageMethod(p, damagedEntity);
        }
        setArray(p, 1);
        resetArray(checkOut(array[0]), p);
        event.setCancelled(true);
    }

    public void clickAirRight(final PlayerInteractEvent event){
        Player p = event.getPlayer();
        Action a = event.getAction();
        if(!itemCheck(p)) return;
        if(a != Action.RIGHT_CLICK_AIR && a != Action.RIGHT_CLICK_BLOCK) return;
        int[] array = getArray(p);
        setArray(p, -1);
        resetArray(checkOut(array[0]), p);
        event.setCancelled(true);
    }

    public void skillActiveAtEvent(final PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(getArray(p)[3] == 0) return;
    }

    private boolean checkOut(int data){
        return data != 0;
    }
    @SuppressWarnings("all")
    private boolean itemCheck(Player p){
        ItemStack item = p.getInventory().getItemInMainHand();
        if(!item.hasItemMeta()) return false;
        if(!item.getItemMeta().hasCustomModelData()) return false;
        Material material = item.getType();
        switch(material){
            case NETHERITE_SWORD, WOODEN_HOE, BOW -> {
                return item.getItemMeta().getCustomModelData() == 1;
            }
            case DIAMOND_SWORD -> {
                if(item.getItemMeta().getCustomModelData() != 1) return false;
                ItemStack item2 = p.getInventory().getItemInOffHand();
                if(!item2.hasItemMeta()) return false;
                if(!item2.getItemMeta().hasCustomModelData()) return false;
                return item2.getItemMeta().getCustomModelData() == 1;
            }
        }
        return false;
    }
}
