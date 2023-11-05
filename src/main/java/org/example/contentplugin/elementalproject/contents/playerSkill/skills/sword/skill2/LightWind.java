package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill2;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.RNS;

import java.util.Set;
import java.util.UUID;

public class LightWind implements RNS {
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(!player.isSneaking()) return;
    }
}
