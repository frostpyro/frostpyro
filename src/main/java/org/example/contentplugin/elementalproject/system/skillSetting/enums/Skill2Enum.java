package org.example.contentplugin.elementalproject.system.skillSetting.enums;

import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.RNS;

public enum Skill2Enum {
    ;
    private Class<? extends RNS> skill2;

    Skill2Enum(Class<? extends RNS> skill2){
        this.skill2 = skill2;
    }
}
