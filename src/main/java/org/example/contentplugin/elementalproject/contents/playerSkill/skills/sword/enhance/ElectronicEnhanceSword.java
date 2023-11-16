package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.enhance;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.BaseAttackEnhancer;

import java.util.Set;
import java.util.UUID;

public class ElectronicEnhanceSword implements BaseAttack {
    BaseAttackEnhancer enhancer = new BaseAttackEnhancer();
    private int sec;
    ElectronicEnhanceSword(int sec){
        this.sec = sec;
    }
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(player.isSneaking()) return;
    }
}
