package org.example.contentplugin.elementalproject.contents.dailyQuest.npc;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.*;

public class Boss implements Mob {

    @Override
    public Sound getAmbientSound() {
        return null;
    }

    @Override
    public LivingEntity getTarget() {
        return null;
    }

    @Override
    public boolean isAware() {
        return false;
    }

    @Override
    public void setAware(boolean b) {

    }

    @Override
    public void setTarget(LivingEntity livingEntity) {

    }
}
