package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.baseAttack;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.items.SkillEffectLightSword;

import java.util.Set;
import java.util.UUID;

public class LightBaseSword implements BaseAttack {
    SkillEffectLightSword effect = new SkillEffectLightSword();
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(player.isSneaking()) return;
        effect.lightBaseSword(player, entitySet);
    }
}
