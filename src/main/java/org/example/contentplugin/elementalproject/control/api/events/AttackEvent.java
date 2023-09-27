package org.example.contentplugin.elementalproject.control.api.events;


import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.Nonnull;

public class AttackEvent extends Event {
    private static HandlerList handlerList;
    public AttackEvent(boolean isAsync) {
        super(isAsync);
    }

    @Nonnull
    public String getEventName() {
        return super.getEventName();
    }

    @Nonnull
    public HandlerList getHandlers() {
        return getHandlerList();
    }

    public static HandlerList getHandlerList(){
        return handlerList;
    }
}
