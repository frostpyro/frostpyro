package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.enhance;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.StatusModifier;

import java.util.Set;
import java.util.UUID;

public class FireEnhanceSword implements BaseAttack {

    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(player.isSneaking()) return;
        if(StatusModifier.getInt(player) != 4) return;

    }
}
