package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.baseAttack;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.items.sword.SkillEffectFireSword;

import java.util.Set;
import java.util.UUID;

public class FireBaseSword implements BaseAttack {
    SkillEffectFireSword effect = new SkillEffectFireSword();

    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(player.isSneaking()) return;
        effect.fireBaseSword(player, entitySet);
    }
}
