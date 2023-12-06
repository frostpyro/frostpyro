package org.example.contentplugin.elementalproject.system.skillSetting.enums;

import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.baseAttack.FireBaseSword;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.enhance.AirEnhanceSword;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.enhance.FireEnhanceSword;

public enum BaseAttackEnum {
    FIREBASESWORD(FireBaseSword.class),
    FIREENHANCE(FireEnhanceSword.class),
    AIRENHANCESWORD(AirEnhanceSword.class);
    private Class<? extends BaseAttack> list;

    BaseAttackEnum(Class<? extends BaseAttack> list){
        this.list = list;
    }

    public Class<? extends BaseAttack> t(BaseAttackEnum td){
        return td.list;
    }
}
