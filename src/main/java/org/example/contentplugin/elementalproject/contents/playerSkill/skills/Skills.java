package org.example.contentplugin.elementalproject.contents.playerSkill.skills;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.SQLDB.DataBase;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.baseAttack.*;

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
