package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill1;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.Right;

import java.util.Set;
import java.util.UUID;

public class ElectronicSkill1Sword implements Right {
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(player.isSneaking()) return;
    }
}
