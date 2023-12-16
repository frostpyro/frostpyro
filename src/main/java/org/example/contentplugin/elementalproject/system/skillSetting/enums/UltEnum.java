package org.example.contentplugin.elementalproject.system.skillSetting.enums;

import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.SNS;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.archer.ultimate.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.mage.ultimate.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.ultimate.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.ultimate.*;

public enum UltEnum {
    AS(AirUltSword.class),
    ES(EarthUltSword.class),
    ElS(ElectUltSword.class),
    FS(FireUltSword.class),
    IS(IceUltSword.class),
    LS(LightUltSword.class),
    AA(AirUltArcher.class),
    EA(EarthUltArcher.class),
    ElA(ElectUltArcher.class),
    FA(FireUltArcher.class),
    IA(IceUltArcher.class),
    LA(LightUltArcher.class),
    AMa(AirUltMar.class),
    EMa(EarthUltMar.class),
    ElMa(ElectUltMar.class),
    FMa(FireUltMar.class),
    IMa(IceUltMar.class),
    LMa(LightUltMar.class),
    AM(AirUltMage.class),
    EM(EarthUltMage.class),
    ElM(ElectUltMage.class),
    FM(FireUltMage.class),
    IM(IceUltMage.class),
    LM(LightUltMage.class);
    private Class<? extends SNS> ult;
    UltEnum(Class<? extends SNS> ult){
        this.ult = ult;
    }
    public static Class<? extends SNS> get(UltEnum td){
        return td.ult;
    }
}
