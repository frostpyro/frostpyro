package org.example.contentplugin.elementalproject.contents.playerSkill.skills;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.SQLDB.DataBase;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.LNS;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.RNS;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.Right;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.baseAttack.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill1.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill2.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill3.AirSkill3Sword;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill3.EarthSkill3Sword;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill3.FireSkill3Sword;

import java.sql.SQLException;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class Skills {
    private final DataBase dataBase = new DataBase();

    private final BaseAttack airBaseSword = new AirBaseSword();
    private final BaseAttack electronicBaseSword = new ElectronicBaseSword();
    private final BaseAttack fireBaseSword = new FireBaseSword();
    private final BaseAttack iceBaseSword = new IceBaseSword();
    private final BaseAttack lightBaseSword = new LightBaseSword();
    private final BaseAttack earthBaseSword = new EarthBaseSword();
    private final Right airSkill1Sword = new AirSkill1Sword();
    private final Right electronicSkill1Sword = new ElectronicSkill1Sword();
    private final Right earthSkill1Sword = new EarthSkill1Sword();
    private final Right iceSkill1Sword = new IceSkill1Sword();
    private final Right fireSkill1Sword = new FireSkill1Sword();
    private final Right lightSkill1Sword = new LightSkill1Sword();
    private final RNS iceSkill2Sword = new IceSkill2Sword();
    private final RNS fireSKill2Sword = new FireSkill2Sword();
    private final RNS earthSkill2Sword = new EarthSkill2Sword();
    private final RNS electronicSkill2Sword = new ElectronicSkill2Sword();
    private final RNS lightSkill2Sword = new LightSkill2Sword();
    private final RNS airSkill2Sword = new AirSkill2Sword();
    private final LNS airSkill3Sword = new AirSkill3Sword();
    private final LNS earthSkill3Sword = new EarthSkill3Sword();
    private final LNS fireSkill3Sword = new FireSkill3Sword();
    private PlayerStat getPlayerStat(Player p) throws SQLException {
        PlayerStat playerStat = dataBase.findUUID(p.getUniqueId().toString());
        if(playerStat == null){
            playerStat = new PlayerStat(p.getUniqueId().toString(), 0, 0, 0, 0, 0, 0, 0,new Date(), new Date());
            dataBase.createData(playerStat);
        }
        return playerStat;
    }
    public void baseAttackSkill(Player p, Set<UUID> entitySet){
        try{
            PlayerStat playerStat = getPlayerStat(p);
            int skillClass = playerStat.getSkillClass();
            switch(skillClass){
                case 1 ->{
                    airBaseSword.attacking(p, entitySet);
                    earthBaseSword.attacking(p, entitySet);
                    fireBaseSword.attacking(p, entitySet);
                    iceBaseSword.attacking(p, entitySet);
                    lightBaseSword.attacking(p, entitySet);
                    electronicBaseSword.attacking(p, entitySet);
                }
                case 2 ->{

                }
                case 3 ->{

                }
                case 4 ->{

                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void skill1(Player p, Set<UUID> entitySet){
        try{
            PlayerStat playerStat = getPlayerStat(p);
            int skillClass = playerStat.getSkillClass();
            switch(skillClass){
                case 1 ->{
                    airSkill1Sword.attacking(p, entitySet);
                    fireSkill1Sword.attacking(p, entitySet);
                    iceSkill1Sword.attacking(p, entitySet);
                    earthSkill1Sword.attacking(p, entitySet);
                    lightSkill1Sword.attacking(p, entitySet);
                    electronicSkill1Sword.attacking(p, entitySet);
                }
                case 2 ->{

                }
                case 3 ->{

                }
                case 4 ->{

                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void skill2(Player p, Set<UUID> entitySet){
        try{
            PlayerStat playerStat = getPlayerStat(p);
            int skillClass = playerStat.getSkillClass();
            switch(skillClass){
                case 1 ->{
                    airSkill2Sword.attacking(p, entitySet);
                    electronicSkill2Sword.attacking(p, entitySet);
                    iceSkill2Sword.attacking(p, entitySet);
                    fireSKill2Sword.attacking(p, entitySet);
                    earthSkill2Sword.attacking(p, entitySet);
                    lightSkill2Sword.attacking(p, entitySet);
                }
                case 2 ->{

                }
                case 3 ->{

                }
                case 4 ->{

                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void skill3(Player p, Set<UUID> entitySet){
        try{
            PlayerStat playerStat = getPlayerStat(p);
            int skillClass = playerStat.getSkillClass();
            switch(skillClass){
                case 1 ->{

                }
                case 2 ->{

                }
                case 3 ->{

                }
                case 4 ->{

                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void ultimateSkill(Player p, Set<UUID> entitySet){
        try{
            PlayerStat playerStat = getPlayerStat(p);
            int skillClass = playerStat.getSkillClass();
            switch(skillClass){
                case 1 ->{

                }
                case 2 ->{

                }
                case 3 ->{

                }
                case 4 ->{

                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
