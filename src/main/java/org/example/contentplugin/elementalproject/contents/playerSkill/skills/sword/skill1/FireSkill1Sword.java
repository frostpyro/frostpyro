package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill1;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.Right;
import org.example.contentplugin.elementalproject.contents.playerSkill.items.sword.SkillEffectFireSword;

import java.util.Set;
import java.util.UUID;

public class FireSkill1Sword implements Right {
    SkillEffectFireSword effect = new SkillEffectFireSword();
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(player.isSneaking()) return;
        effect.fireSkill1Sword(player, entitySet);
    }
}
