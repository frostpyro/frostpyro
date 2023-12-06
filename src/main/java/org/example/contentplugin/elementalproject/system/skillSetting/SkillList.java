package org.example.contentplugin.elementalproject.system.skillSetting;

import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.system.skillSetting.enums.*;

import java.util.ArrayList;
import java.util.List;

public class SkillList {
    private final List<BaseAttackEnum> baseEnum = new ArrayList<>();
    private final List<Skill1Enum> skill1Enum = new ArrayList<>();
    private final List<Skill2Enum> skill2Enum = new ArrayList<>();
    private final List<Skill3Enum> skill3Enum = new ArrayList<>();
    private final List<UltEnum> ultEnum = new ArrayList<>();

    public Class<? extends BaseAttack> baseAttack(boolean enhance, int cType, int element){
        baseEnum.set(0, null);


        return BaseAttackEnum.t(baseEnum.get(element));
    }
}
