package org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.ultimate;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.SNS;
import org.example.contentplugin.elementalproject.contents.playerSkill.items.martial.SkillEffectEarthMar;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.StatusModifier;

import java.util.Set;
import java.util.UUID;

public class EarthUltMar implements SNS {
    private final int sec;
    public EarthUltMar(int sec){
        this.sec = sec;
    }
    SkillEffectEarthMar effect = new SkillEffectEarthMar();
    @Override
    public void attacking(Player player, Set<UUID> entitySet) {
        StatusModifier.activeEnhance(player);
        StatusModifier.cancelMove(player);
        new BukkitRunnable(){
            @Override
            public void run() {
                effect.earthMarUlt(player, entitySet);
                StatusModifier.deactivateEnhance(player);
                StatusModifier.activeMove(player);
            }
        }.runTaskLater(ElementalProject.getPlugin(), sec*20L);
    }
}
