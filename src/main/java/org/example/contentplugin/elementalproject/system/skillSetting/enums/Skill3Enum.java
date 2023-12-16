package org.example.contentplugin.elementalproject.system.skillSetting.enums;

import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.LNS;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.archer.skill2.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.archer.skill3.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.mage.skill2.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.mage.skill3.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.skill2.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.skill3.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill2.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill3.*;

public enum Skill3Enum {
    AS(AirSkill3Sword.class),
    ES(EarthSkill3Sword.class),
    ElS(ElectSkill3Sword.class),
    FS(FireSkill3Sword.class),
    IS(IceSkill3Sword.class),
    LS(LightSkill3Sword.class),
    AA(AirSkill3Archer.class),
    EA(EarthSkill3Archer.class),
    ElA(ElectSkill3Archer.class),
    FA(FireSkill3Archer.class),
    IA(IceSkill3Archer.class),
    LA(LightSkill3Archer.class),
    AMa(AirSkill3Mar.class),
    EMa(EarthSkill3Mar.class),
    ElMa(ElectSkill3Mar.class),
    FMa(FireSkill3Mar.class),
    IMa(IceSkill3Mar.class),
    LMa(LightSkill3Mar.class),
    AM(AirSkill3Mage.class),
    EM(EarthSkill3Mage.class),
    ElM(ElectSkill3Mage.class),
    FM(FireSkill3Mage.class),
    IM(IceSkill3Mage.class),
    LM(LightSkill3Mage.class);
    private Class<? extends LNS> skill3;
    Skill3Enum(Class<? extends LNS> skill3){
        this.skill3 = skill3;
    }

    public static Class<? extends LNS> get(Skill3Enum td){
        return td.skill3;
    }
}
