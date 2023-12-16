package org.example.contentplugin.elementalproject.system.skillSetting.enums;

import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.RNS;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.archer.skill1.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.archer.skill2.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.mage.skill1.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.mage.skill2.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.skill1.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.skill2.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill1.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill2.*;

public enum Skill2Enum {
    AS(AirSkill2Sword.class),
    ES(EarthSkill2Sword.class),
    ElS(ElectSkill2Sword.class),
    FS(FireSkill2Sword.class),
    IS(IceSkill2Sword.class),
    LS(LightSkill2Sword.class),
    AA(AirSkill2Archer.class),
    EA(EarthSkill2Archer.class),
    ElA(ElectSkill2Archer.class),
    FA(FireSkill2Archer.class),
    IA(IceSkill2Archer.class),
    LA(LightSkill2Archer.class),
    AMa(AirSkill2Mar.class),
    EMa(EarthSkill2Mar.class),
    ElMa(ElectSkill2Mar.class),
    FMa(FireSkill2Mar.class),
    IMa(IceSkill2Mar.class),
    LMa(LightSkill2Mar.class),
    AM(AirSkill2Mage.class),
    EM(EarthSkill2Mage.class),
    ElM(ElectSkill2Mage.class),
    FM(FireSkill2Mage.class),
    IM(IceSkill2Mage.class),
    LM(LightSkill2Mage.class);
    private Class<? extends RNS> skill2;

    Skill2Enum(Class<? extends RNS> skill2){
        this.skill2 = skill2;
    }

    public static Class<? extends RNS> get(Skill2Enum td){
        return td.skill2;
    }
}
