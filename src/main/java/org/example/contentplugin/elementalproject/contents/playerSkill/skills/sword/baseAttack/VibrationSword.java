package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.baseAttack;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;

import java.util.Set;
import java.util.UUID;

public class VibrationSword implements BaseAttack {
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(player.isSneaking()) return;
    }
}
