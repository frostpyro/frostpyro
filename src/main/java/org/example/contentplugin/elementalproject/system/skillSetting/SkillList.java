package org.example.contentplugin.elementalproject.system.skillSetting;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.*;
import org.example.contentplugin.elementalproject.system.skillSetting.enums.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkillList {
    private final List<BaseAttackEnum> baseEnum = new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null));
    private final List<Skill1Enum> skill1Enum = new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null));
    private final List<Skill2Enum> skill2Enum = new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null));
    private final List<Skill3Enum> skill3Enum = new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null));
    private final List<UltEnum> ultEnum = new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null));

    public Class<? extends BaseAttack> baseAttack(boolean enhance, int cType, int element){

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

        switch(cType){
            case 1->{
                skill1Enum.set(1, Skill1Enum.AS);
                skill1Enum.set(2, Skill1Enum.ES);
                skill1Enum.set(3, Skill1Enum.ElS);
                skill1Enum.set(4, Skill1Enum.FS);
                skill1Enum.set(5, Skill1Enum.IS);
                skill1Enum.set(6, Skill1Enum.LS);
            }
            case 2 ->{
                skill1Enum.set(1, Skill1Enum.AA);
                skill1Enum.set(2, Skill1Enum.EA);
                skill1Enum.set(3, Skill1Enum.ElA);
                skill1Enum.set(4, Skill1Enum.FA);
                skill1Enum.set(5, Skill1Enum.IA);
                skill1Enum.set(6, Skill1Enum.LA);
            }
            case 3 ->{
                skill1Enum.set(1, Skill1Enum.AMa);
                skill1Enum.set(2, Skill1Enum.EMa);
                skill1Enum.set(3, Skill1Enum.ElMa);
                skill1Enum.set(4, Skill1Enum.FMa);
                skill1Enum.set(5, Skill1Enum.IMa);
                skill1Enum.set(6, Skill1Enum.LMa);
            }
            case 4 ->{
                skill1Enum.set(1, Skill1Enum.AM);
                skill1Enum.set(2, Skill1Enum.EM);
                skill1Enum.set(3, Skill1Enum.ElM);
                skill1Enum.set(4, Skill1Enum.FM);
                skill1Enum.set(5, Skill1Enum.IM);
                skill1Enum.set(6, Skill1Enum.LM);
            }
        }

        return Skill1Enum.get(skill1Enum.get(element));
    }

    public Class<? extends RNS> skill2(int cType, int element){
        switch(cType){
            case 1 ->{
                skill2Enum.set(1, Skill2Enum.AS);
                skill2Enum.set(2, Skill2Enum.ES);
                skill2Enum.set(3, Skill2Enum.ElS);
                skill2Enum.set(4, Skill2Enum.FS);
                skill2Enum.set(5, Skill2Enum.IS);
                skill2Enum.set(6, Skill2Enum.LS);
            }
            case 2 ->{
                skill2Enum.set(1, Skill2Enum.AA);
                skill2Enum.set(2, Skill2Enum.EA);
                skill2Enum.set(3, Skill2Enum.ElA);
                skill2Enum.set(4, Skill2Enum.FA);
                skill2Enum.set(5, Skill2Enum.IA);
                skill2Enum.set(6, Skill2Enum.LA);
            }
            case 3 ->{
                skill2Enum.set(1, Skill2Enum.AMa);
                skill2Enum.set(2, Skill2Enum.EMa);
                skill2Enum.set(3, Skill2Enum.ElMa);
                skill2Enum.set(4, Skill2Enum.FMa);
                skill2Enum.set(5, Skill2Enum.IMa);
                skill2Enum.set(6, Skill2Enum.LMa);
            }
            case 4 ->{
                skill2Enum.set(1, Skill2Enum.AM);
                skill2Enum.set(2, Skill2Enum.EM);
                skill2Enum.set(3, Skill2Enum.ElM);
                skill2Enum.set(4, Skill2Enum.FM);
                skill2Enum.set(5, Skill2Enum.IM);
                skill2Enum.set(6, Skill2Enum.LM);
            }
        }
        return Skill2Enum.get(skill2Enum.get(element));
    }

    public Class<? extends LNS> skill3(int cType, int element){
        switch(cType){
            case 1 ->{
                skill3Enum.set(1, Skill3Enum.AS);
                skill3Enum.set(2, Skill3Enum.ES);
                skill3Enum.set(3, Skill3Enum.ElS);
                skill3Enum.set(4, Skill3Enum.FS);
                skill3Enum.set(5, Skill3Enum.IS);
                skill3Enum.set(6, Skill3Enum.LS);
            }
            case 2 ->{
                skill3Enum.set(1, Skill3Enum.AA);
                skill3Enum.set(2, Skill3Enum.EA);
                skill3Enum.set(3, Skill3Enum.ElA);
                skill3Enum.set(4, Skill3Enum.FA);
                skill3Enum.set(5, Skill3Enum.IA);
                skill3Enum.set(6, Skill3Enum.LA);
            }
            case 3 ->{
                skill3Enum.set(1, Skill3Enum.AMa);
                skill3Enum.set(2, Skill3Enum.EMa);
                skill3Enum.set(3, Skill3Enum.ElMa);
                skill3Enum.set(4, Skill3Enum.FMa);
                skill3Enum.set(5, Skill3Enum.IMa);
                skill3Enum.set(6, Skill3Enum.LMa);
            }
            case 4 ->{
                skill3Enum.set(1, Skill3Enum.AM);
                skill3Enum.set(2, Skill3Enum.EM);
                skill3Enum.set(3, Skill3Enum.ElM);
                skill3Enum.set(4, Skill3Enum.FM);
                skill3Enum.set(5, Skill3Enum.IM);
                skill3Enum.set(6, Skill3Enum.LM);
            }
        }
        return Skill3Enum.get(skill3Enum.get(element));
    }

    public Class<? extends SNS> ult(int cType, int element){
        switch(cType){
            case 1 ->{
                ultEnum.set(1, UltEnum.AS);
                ultEnum.set(2, UltEnum.ES);
                ultEnum.set(3, UltEnum.ElS);
                ultEnum.set(4, UltEnum.FS);
                ultEnum.set(5, UltEnum.IS);
                ultEnum.set(6, UltEnum.LS);
            }
            case 2 ->{
                ultEnum.set(1, UltEnum.AA);
                ultEnum.set(2, UltEnum.EA);
                ultEnum.set(3, UltEnum.ElA);
                ultEnum.set(4, UltEnum.FA);
                ultEnum.set(5, UltEnum.IA);
                ultEnum.set(6, UltEnum.LA);
            }
            case 3 ->{
                ultEnum.set(1, UltEnum.AMa);
                ultEnum.set(2, UltEnum.EMa);
                ultEnum.set(3, UltEnum.ElMa);
                ultEnum.set(4, UltEnum.FMa);
                ultEnum.set(5, UltEnum.IMa);
                ultEnum.set(6, UltEnum.LMa);
            }
            case 4 ->{
                ultEnum.set(1, UltEnum.AM);
                ultEnum.set(2, UltEnum.EM);
                ultEnum.set(3, UltEnum.ElM);
                ultEnum.set(4, UltEnum.FM);
                ultEnum.set(5, UltEnum.IM);
                ultEnum.set(6, UltEnum.LM);
            }
        }
        return UltEnum.get(ultEnum.get(element));
    }
}
