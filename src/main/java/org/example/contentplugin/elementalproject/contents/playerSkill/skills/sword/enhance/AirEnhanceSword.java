package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.enhance;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;

import java.util.Set;
import java.util.UUID;

public class AirEnhanceSword implements BaseAttack {
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(player.isSneaking()) return;
        player.sendMessage("enhance");
    }
}
