package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill3;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.LNS;

import java.util.Set;
import java.util.UUID;

public class IceSkill3Sword implements LNS {
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(!player.isSneaking()) return;
    }
}
