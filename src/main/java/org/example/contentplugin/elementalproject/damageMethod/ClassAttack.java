package org.example.contentplugin.elementalproject.damageMethod;

import org.bukkit.event.player.PlayerInteractEvent;

public interface ClassAttack {
    void lAttack(PlayerInteractEvent event);
    void rLLAttack(PlayerInteractEvent event);
    void rLRAttack(PlayerInteractEvent event);
    void rRLAttack(PlayerInteractEvent event);
    void rRRAttack(PlayerInteractEvent event);
}
