package org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.ultimate;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.SNS;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.BaseAttackEnhancer;

import java.util.Set;
import java.util.UUID;

public class AirUltSword implements SNS {
    private int sec;

    public AirUltSword(int sec){
        this.sec = sec;
    }
    BaseAttackEnhancer enhancer = new BaseAttackEnhancer();
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        if(!enhancer.enhancer(player)) return;
        enhancer.setEnhance(player, 1);
        player.sendMessage("ult");
        new BukkitRunnable(){
            @Override
            public void run() {
                enhancer.setEnhance(player, 0);
            }
        }.runTaskLater(ElementalProject.getPlugin(), sec* 20L);
    }
}
