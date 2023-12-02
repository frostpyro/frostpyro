package org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.baseAttack;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.items.martial.SkillEffectEarthMar;

import java.util.Set;
import java.util.UUID;

public class EarthBaseMar implements BaseAttack {
    SkillEffectEarthMar effect = new SkillEffectEarthMar();
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(player.isSneaking()) return;
        effect.earthMarBase(player, entitySet);
    }
}
