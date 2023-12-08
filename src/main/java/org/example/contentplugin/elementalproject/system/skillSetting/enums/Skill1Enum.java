package org.example.contentplugin.elementalproject.system.skillSetting.enums;

import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.Right;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill1.AirSkill1Sword;

public enum Skill1Enum {
    AS(AirSkill1Sword.class);

    private final Class<? extends Right> skill1;

    Skill1Enum(Class<? extends Right> skill1){
        this.skill1 = skill1;
    }

    public static Class<? extends Right> get(Skill1Enum td){
        return td.skill1;
    }
}
