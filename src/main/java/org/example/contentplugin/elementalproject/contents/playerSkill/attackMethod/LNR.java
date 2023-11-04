package org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod;

import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public interface LNR {
    void attacking(Player player, Set<UUID> entitySet);
}
