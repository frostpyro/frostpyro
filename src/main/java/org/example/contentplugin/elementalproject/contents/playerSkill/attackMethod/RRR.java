package org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Set;
import java.util.UUID;

public interface RRR {
    void attacking(Player player, Set<UUID> entitySet);
}
