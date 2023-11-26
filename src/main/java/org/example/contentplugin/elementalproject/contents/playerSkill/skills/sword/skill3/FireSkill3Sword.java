package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill3;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.LNS;
import org.example.contentplugin.elementalproject.contents.playerSkill.items.SkillEffectFireSword;

import java.util.Set;
import java.util.UUID;

public class FireSkill3Sword implements LNS {
    SkillEffectFireSword effect = new SkillEffectFireSword();
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(!player.isSneaking()) return;
        effect.fireSkill3Sword(player, entitySet);
    }
}
