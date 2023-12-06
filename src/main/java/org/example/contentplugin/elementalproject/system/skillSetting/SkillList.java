package org.example.contentplugin.elementalproject.system.skillSetting;

import org.bukkit.entity.Player;
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
        for(int i = 0; i < 7; i++){
            baseEnum.add(null);
        }

        if(enhance){
            switch(cType){
                case 1 ->{
                    baseEnum.add(1, BaseAttackEnum.ABS);
                    baseEnum.add(2, BaseAttackEnum.EBS);
                    baseEnum.set(3, BaseAttackEnum.ElBS);
                    baseEnum.set(4, BaseAttackEnum.FBS);
                    baseEnum.set(5, BaseAttackEnum.IBS);
                    baseEnum.set(6, BaseAttackEnum.LBS);
                }
            }

            return BaseAttackEnum.get(baseEnum.get(element));
        }


        return BaseAttackEnum.get(baseEnum.get(element));
    }
}
