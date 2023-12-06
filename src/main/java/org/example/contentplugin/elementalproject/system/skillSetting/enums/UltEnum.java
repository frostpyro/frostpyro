package org.example.contentplugin.elementalproject.system.skillSetting.enums;

import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.SNS;

public enum UltEnum {
    ;
    private Class<? extends SNS> ult;
    UltEnum(Class<? extends SNS> ult){
        this.ult = ult;
    }
}
