package org.example.contentplugin.elementalproject.system.skillSetting.enums;

import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.LNS;

public enum Skill3Enum {
    ;
    private Class<? extends LNS> skill3;
    Skill3Enum(Class<? extends LNS> skill3){
        this.skill3 = skill3;
    }
}
