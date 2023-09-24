package org.example.contentplugin.elementalproject.control.api.events;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

public class AttackEvent extends Event {
    private static final HandlerList handler = new HandlerList();

    private final PlayerInteractEvent event;
    private final EntityDamageEvent damageEvent;

    private final EntityDeathEvent deathEvent;
    public AttackEvent(PlayerInteractEvent event, EntityDamageEvent damageEvent, EntityDeathEvent deathEvent) {
        super();
        this.event = event;
        this.damageEvent = damageEvent;
        this.deathEvent = deathEvent;
    }
    public Player getPlayer(){
        return event.getPlayer();
    }
    public Entity getEntity(){
        return damageEvent.getEntity();
    }
    public Player getKiller(){
        return deathEvent.getEntity().getKiller();
    }

    public Material getMaterial(){
        return event.getMaterial();
    }
    public ItemStack getItemMeta(){
        return event.getItem();
    }
    public Entity getDeadEntity(){
        return deathEvent.getEntity();
    }
    public Action getAction(){
        return event.getAction();
    }
    @Nonnull
    public HandlerList getHandlers() {
        return handler;
    }

    public static HandlerList getHandlerList(){
        return handler;
    }
}
