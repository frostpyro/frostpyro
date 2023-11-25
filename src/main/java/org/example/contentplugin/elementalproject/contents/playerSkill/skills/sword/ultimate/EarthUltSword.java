package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.ultimate;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.SNS;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.StatusModifier;

import java.util.Set;
import java.util.UUID;

public class EarthUltSword implements SNS {
    private final int sec;



    public EarthUltSword(int sec){
        this.sec = sec;
    }
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        StatusModifier.activeEnhance(player);
        new BukkitRunnable(){
            @Override
            public void run() {
                StatusModifier.deactivateEnhance(player);
            }
        }.runTaskLater(ElementalProject.getPlugin(), sec * 20L);
    }
}
