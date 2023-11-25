package org.example.contentplugin.elementalproject.contents.playerSkill.skills.archer.ultimate;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.SNS;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.mage.ultimate.ElectUltMage;

import java.util.Set;
import java.util.UUID;

public class ElectUltArcher implements SNS {
    private final int sec;
    public ElectUltArcher(int sec){
        this.sec = sec;
    }
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {

    }
}
