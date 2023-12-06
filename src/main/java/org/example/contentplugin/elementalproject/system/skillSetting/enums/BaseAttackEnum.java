package org.example.contentplugin.elementalproject.system.skillSetting.enums;

import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.mage.enhance.LightEnhanceMage;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.baseAttack.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.enhance.*;

public enum BaseAttackEnum {
    ABS(AirBaseSword.class),
    AES(AirEnhanceSword.class),
    EBS(EarthBaseSword.class),
    EES(EarthEnhanceSword.class),
    ElBS(ElectBaseSword.class),
    ElES(ElectEnhanceSword.class),
    FBS(FireBaseSword.class),
    FES(FireEnhanceSword.class),
    IBS(IceBaseSword.class),
    IES(IceEnhanceSword.class),
    LBS(LightBaseSword.class),
    LES(LightEnhanceSword.class);

    private Class<? extends BaseAttack> list;

    BaseAttackEnum(Class<? extends BaseAttack> list){
        this.list = list;
    }

    public static Class<? extends BaseAttack> get(BaseAttackEnum td){
        return td.list;
    }
}
