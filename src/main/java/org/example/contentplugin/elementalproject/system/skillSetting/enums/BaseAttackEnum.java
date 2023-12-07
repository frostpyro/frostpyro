package org.example.contentplugin.elementalproject.system.skillSetting.enums;

import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.archer.baseAttack.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.archer.enhance.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.mage.baseAttack.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.mage.enhance.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.baseAttack.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.enhance.*;
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
    LES(LightEnhanceSword.class),
    ABA(AirBaseArcher.class),
    AEA(AirEnhanceArcher.class),
    EBA(EarthBaseArcher.class),
    EEA(EarthEnhanceArcher.class),
    ElBA(ElectBaseArcher.class),
    ElEA(ElectEnhanceArcher.class),
    FBA(FireBaseArcher.class),
    FEA(FireEnhanceArcher.class),
    IBA(IceBaseArcher.class),
    IEA(IceEnhanceArcher.class),
    LBA(LightBaseArcher.class),
    LEA(LightEnhanceArcher.class),
    ABMa(AirBaseMar.class),
    AEMa(AirEnhanceMar.class),
    EBMa(EarthBaseMar.class),
    EEMa(EarthEnhanceMar.class),
    ElBMa(ElectBaseMar.class),
    ElEMa(ElectEnhanceMar.class),
    FBMa(FireBaseMar.class),
    FEMa(FireEnhanceMar.class),
    IBMa(IceBaseMar.class),
    IEMa(IceEnhanceMar.class),
    LBMa(LightBaseMar.class),
    LEMa(LightEnhanceMar.class),
    ABM(AirBaseMage.class),
    AEM(AirEnhanceMage.class),
    EBM(EarthBaseMage.class),
    EEM(EarthEnhanceMage.class),
    ElBM(EarthBaseMage.class),
    ElEM(EarthEnhanceMar.class),
    FBM(FireBaseMage.class),
    FEM(FireEnhanceMage.class),
    IBM(IceBaseMage.class),
    IEM(IceEnhanceMage.class),
    LBM(LightBaseMage.class),
    LEM(LightEnhanceMage.class);

    private Class<? extends BaseAttack> list;

    BaseAttackEnum(Class<? extends BaseAttack> list){
        this.list = list;
    }

    public static Class<? extends BaseAttack> get(BaseAttackEnum td){
        return td.list;
    }
}
