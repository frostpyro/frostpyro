package org.example.contentplugin.elementalproject.control.api.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class AttackEvent extends Event {
    private final static HandlerList handler = new HandlerList();

    private final PlayerInteractEvent event;
    private final EntityDamageEvent damageEvent;
    public AttackEvent(PlayerInteractEvent event, EntityDamageEvent damageEvent) {
        super();
        this.event = event;
        this.damageEvent = damageEvent;
    }

    public Player getPlayer(){
        return event.getPlayer();
    }

    public Entity getEntity(){
        return damageEvent.getEntity();
    }


    @Override
    public HandlerList getHandlers() {
        return handler;
    }
}
