package org.example.contentplugin.elementalproject.system.skillSetting.enums;

import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.Right;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.archer.skill1.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.mage.skill1.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.skill1.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill1.*;

public enum Skill1Enum {
    AS(AirSkill1Sword.class),
    ES(EarthSkill1Sword.class),
    ElS(ElectSkill1Sword.class),
    FS(FireSkill1Sword.class),
    IS(IceSkill1Sword.class),
    LS(LightSkill1Sword.class),
    AA(AirSkill1Archer.class),
    EA(EarthSkill1Archer.class),
    ElA(ElectSkill1Archer.class),
    FA(FireSkill1Archer.class),
    IA(IceSkill1Archer.class),
    LA(LightSkill1Archer.class),
    AMa(AirSkill1Mar.class),
    EMa(EarthSkill1Mar.class),
    ElMa(ElectSkill1Mar.class),
    FMa(FireSkill1Mar.class),
    IMa(IceSkill1Mar.class),
    LMa(LightSkill1Mar.class),
    AM(AirSkill1Mage.class),
    EM(EarthSkill1Mage.class),
    ElM(ElectSkill1Mage.class),
    FM(FireSkill1Mage.class),
    IM(IceSkill1Mage.class),
    LM(LightSkill1Mage.class);

    private final Class<? extends Right> skill1;

    Skill1Enum(Class<? extends Right> skill1){
        this.skill1 = skill1;
    }

    public static Class<? extends Right> get(Skill1Enum td){
        return td.skill1;
    }
}
