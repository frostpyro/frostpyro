package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill2;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.RNS;
import org.example.contentplugin.elementalproject.contents.playerSkill.items.SkillEffectFireSword;

import java.util.Set;
import java.util.UUID;

public class FireSkill2Sword implements RNS {
    SkillEffectFireSword effect = new SkillEffectFireSword();
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(!player.isSneaking()) return;
        effect.fireSkill2Sword(player, entitySet);
    }
}
