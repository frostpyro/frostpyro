package org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.ultimate;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.SNS;

import java.util.Set;
import java.util.UUID;

public class FireUltMar implements SNS {

    private final int sec;
    public FireUltMar(int sec){
        this.sec = sec;
    }
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {

    }
}
