package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill1;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.Right;
import org.example.contentplugin.elementalproject.contents.playerSkill.items.SkillEffect;

import java.util.Set;
import java.util.UUID;

public class FireSkill1Sword implements Right {
    SkillEffect effect = new SkillEffect();
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(player.isSneaking()) return;
        effect.fireSkill1(player, entitySet);
    }
}
