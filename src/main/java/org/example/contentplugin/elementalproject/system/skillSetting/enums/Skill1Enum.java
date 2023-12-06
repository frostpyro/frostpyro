package org.example.contentplugin.elementalproject.system.skillSetting.enums;

import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.Right;

public enum Skill1Enum {
    ;

    private Class<? extends Right> skill1;

    Skill1Enum(Class<? extends Right> skill1){
        this.skill1 = skill1;
    }
}
