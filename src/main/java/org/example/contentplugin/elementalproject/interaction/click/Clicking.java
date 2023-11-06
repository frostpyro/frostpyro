package org.example.contentplugin.elementalproject.interaction.click;

import org.bukkit.GameMode;
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
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.example.contentplugin.elementalproject.contents.playerSkill.Damaging;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.Skills;

import java.util.*;

public class Clicking {
    private final Set<UUID> damagedEntity = new HashSet<>();
    private Map<UUID, Long> shiftPressTime = new HashMap<>();
    Damaging damaging = new Damaging();

    Skills skills = new Skills();
    @Deprecated
    @SuppressWarnings("all")
    public void leftClick(EntityDamageByEntityEvent event){
        Entity entity = event.getEntity();
        if(!(entity instanceof Interaction)) return;
        Player p = (Player) event.getDamager();
        p.sendMessage("testing");
        for(Entity entity1 : p.getNearbyEntities(10,10,10)){
            if(!(entity1 instanceof LivingEntity)) continue;
            ((LivingEntity)entity1).damage(5, p);
        }
    }
    @Deprecated
    @SuppressWarnings("all")
    public void rightClick(PlayerInteractAtEntityEvent event){
        Player p = event.getPlayer();
        Entity entity = event.getRightClicked();
        if(!(entity instanceof Interaction)) return;
        p.sendMessage("testing2");
        if(true) return;
    }

    public void clickEntityLeft(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();

        if (!(event.getDamager() instanceof Player)) return;
        Player p = (Player) event.getDamager();
        if(event.getCause()== EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK) return;
        if(!itemCheck(p)) return;
        if(!damagedEntity.contains(entity.getUniqueId())) {
            skills.baseAttackSkill(p, damagedEntity);
            skills.skill3(p, damagedEntity);
        } else {
            damagedEntity.remove(entity.getUniqueId());
        }
    }

    public void clickEntityRight(PlayerInteractAtEntityEvent event){
        Entity entity = event.getRightClicked();
        Player p = event.getPlayer();
        if(!(entity instanceof LivingEntity)) return;
        skills.skill1(p, damagedEntity);
        skills.skill2(p, damagedEntity);
    }

    public void clickAirLeft(final PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(!itemCheck(p)) return;
        Action a = event.getAction();
        if(a != Action.LEFT_CLICK_AIR && a != Action.LEFT_CLICK_BLOCK) return;
        skills.baseAttackSkill(p, damagedEntity);
        skills.skill3(p, damagedEntity);
        event.setCancelled(true);
    }

    public void clickAirRight(final PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(!itemCheck(p)) return;
        Action a = event.getAction();
        if(a != Action.RIGHT_CLICK_AIR && a != Action.RIGHT_CLICK_BLOCK) return;
        skills.skill1(p, damagedEntity);
        skills.skill2(p, damagedEntity);
        event.setCancelled(true);
    }

    public void shiftToggle(PlayerToggleSneakEvent event){
        Player p = event.getPlayer();
        if(event.isSneaking()){
            if(shiftPressTime.containsKey(p.getUniqueId())){
                long lastShift = shiftPressTime.get(p.getUniqueId());
                long currentTime = System.currentTimeMillis();
                if(currentTime - lastShift < 1000){
                    skills.ultimateSkill(p, damagedEntity);
                }
            }
            shiftPressTime.put(p.getUniqueId(), System.currentTimeMillis());
        }
    }
    public void skillActiveAtEvent(final PlayerInteractEvent event){
        Player p = event.getPlayer();
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
