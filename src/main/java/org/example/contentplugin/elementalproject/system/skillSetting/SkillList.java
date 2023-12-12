package org.example.contentplugin.elementalproject.system.skillSetting;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.*;
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
            baseEnum.add(i, null);
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
        switch(cType){
            case 1 ->{
                baseEnum.set(1, BaseAttackEnum.AES);
                baseEnum.set(2, BaseAttackEnum.EES);
                baseEnum.set(3, BaseAttackEnum.ElES);
                baseEnum.set(4, BaseAttackEnum.FES);
                baseEnum.set(5, BaseAttackEnum.IES);
                baseEnum.set(6, BaseAttackEnum.LES);
            }
            case 2 ->{
                baseEnum.set(1, BaseAttackEnum.AEA);
                baseEnum.set(2, BaseAttackEnum.EEA);
                baseEnum.set(3, BaseAttackEnum.ElEA);
                baseEnum.set(4, BaseAttackEnum.FEA);
                baseEnum.set(5, BaseAttackEnum.IEA);
                baseEnum.set(6, BaseAttackEnum.LEA);
            }
            case 3 -> {
                baseEnum.set(1, BaseAttackEnum.AEMa);
                baseEnum.set(2, BaseAttackEnum.EEMa);
                baseEnum.set(3, BaseAttackEnum.ElEMa);
                baseEnum.set(4, BaseAttackEnum.FEMa);
                baseEnum.set(5, BaseAttackEnum.IEMa);
                baseEnum.set(6, BaseAttackEnum.LEMa);
            }
            case 4 ->{
                baseEnum.set(1, BaseAttackEnum.AEM);
                baseEnum.set(2, BaseAttackEnum.EEM);
                baseEnum.set(3, BaseAttackEnum.ElEM);
                baseEnum.set(4, BaseAttackEnum.FEM);
                baseEnum.set(5, BaseAttackEnum.IEM);
                baseEnum.set(6, BaseAttackEnum.LEM);
            }
        }

        return BaseAttackEnum.get(baseEnum.get(element));
    }

    public Class<? extends Right> skill1(int cType, int element){
        for(int i = 0; i < 7; i++){
            skill1Enum.add(i, null);
        }
        switch(cType){
            case 1->{
                skill1Enum.set(1, Skill1Enum.AS);
                skill1Enum.set(2, Skill1Enum.ES);
            }
            case 2 ->{
                skill1Enum.set(1, Skill1Enum.AA);
                skill1Enum.set(2, Skill1Enum.EA);
            }
            case 3 ->{
                skill1Enum.set(1, Skill1Enum.AMa);
                skill1Enum.set(2, Skill1Enum.EMa);
            }
            case 4 ->{}
        }

        return Skill1Enum.get(skill1Enum.get(element));
    }

    public Class<? extends RNS> skill2(int cType, int element){
        for(int i = 0; i < 7; i++){
            skill2Enum.add(i, null);
        }
        return Skill2Enum.get(skill2Enum.get(element));
    }

    public Class<? extends LNS> skill3(int cType, int element){
        for(int i = 0; i < 7; i++){
            skill3Enum.add(i, null);
        }
        return Skill3Enum.get(skill3Enum.get(element));
    }

    public Class<? extends SNS> ult(int cType, int element){
        for(int i = 0; i < 7; i++){
            ultEnum.add(i, null);
        }
        return UltEnum.get(ultEnum.get(element));
    }
}
