package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.baseAttack;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.BaseAttackEnhancer;

import java.util.Set;
import java.util.UUID;

public class AirBaseSword implements BaseAttack {
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(player.isSneaking()) return;
        player.sendMessage("baseAttack");
    }
}
