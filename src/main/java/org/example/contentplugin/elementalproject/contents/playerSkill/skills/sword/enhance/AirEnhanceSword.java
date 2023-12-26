package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.enhance;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.items.sword.SkillEffectAirSword;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.StatusModifier;

import java.util.Set;
import java.util.UUID;

public class AirEnhanceSword implements BaseAttack {

    StatusModifier modifier = new StatusModifier();
    SkillEffectAirSword effect = new SkillEffectAirSword();

    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(player.isSneaking()) return;
        if(modifier.getInt(player) != 1) return;
        effect.airEnhanceSword(player, entitySet);
    }
}
