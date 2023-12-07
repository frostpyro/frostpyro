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
            if(!baseEnum.isEmpty()) return null;
            baseEnum.add(null);
        }

        if(enhance){
            switch(cType){
                case 1 ->{
                    baseEnum.set(1, BaseAttackEnum.ABS);
                    baseEnum.set(2, BaseAttackEnum.EBS);
                    baseEnum.set(3, BaseAttackEnum.ElBS);
                    baseEnum.set(4, BaseAttackEnum.FBS);
                    baseEnum.set(5, BaseAttackEnum.IBS);
                    baseEnum.set(6, BaseAttackEnum.LBS);
                }
                case 2 ->{
                    baseEnum.set(1, BaseAttackEnum.ABA);
                    baseEnum.set(2, BaseAttackEnum.EBA);
                    baseEnum.set(3, BaseAttackEnum.ElBA);
                    baseEnum.set(4, BaseAttackEnum.FBA);
                    baseEnum.set(5, BaseAttackEnum.IBA);
                    baseEnum.set(6, BaseAttackEnum.LBA);
                }
                case 3 -> {
                    baseEnum.set(1, BaseAttackEnum.ABMa);
                    baseEnum.set(2, BaseAttackEnum.EBMa);
                    baseEnum.set(3, BaseAttackEnum.ElBMa);
                    baseEnum.set(4, BaseAttackEnum.FBMa);
                    baseEnum.set(5, BaseAttackEnum.IBMa);
                    baseEnum.set(6, BaseAttackEnum.LBMa);
                }
                case 4 ->{
                    baseEnum.set(1, BaseAttackEnum.ABM);
                    baseEnum.set(2, BaseAttackEnum.EBM);
                    baseEnum.set(3, BaseAttackEnum.ElBM);
                    baseEnum.set(4, BaseAttackEnum.FBM);
                    baseEnum.set(5, BaseAttackEnum.IBM);
                    baseEnum.set(6, BaseAttackEnum.LBM);
                }
            }

            return BaseAttackEnum.get(baseEnum.get(element));
        }


        return BaseAttackEnum.get(baseEnum.get(element));
    }
}
