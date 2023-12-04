package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.ultimate;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.SNS;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.StatusModifier;

import java.util.Set;
import java.util.UUID;

public class AirUltSword implements SNS {
    private final int sec;



    public AirUltSword(int sec){
        this.sec = sec;
    }
    StatusModifier modifier = new StatusModifier();
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        modifier.activeEnhance(player);
        new BukkitRunnable(){
            @Override
            public void run() {
                modifier.deactivateEnhance(player);
            }
        }.runTaskLater(ElementalProject.getPlugin(), sec * 20L);
    }
}
