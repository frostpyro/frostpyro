package org.example.contentplugin.elementalproject.contents.playerSkill.skills.archer.ultimate;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.SNS;

import java.util.Set;
import java.util.UUID;

public class IceUltArcher implements SNS {
    private final int sec;
    public IceUltArcher(int sec){
        this.sec = sec;
    }
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {

    }
}
