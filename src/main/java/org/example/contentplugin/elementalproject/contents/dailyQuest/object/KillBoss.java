package org.example.contentplugin.elementalproject.contents.dailyQuest.object;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.system.DBset.DataBase;
import org.example.contentplugin.elementalproject.system.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.contents.dailyQuest.DailyQuest;

import java.sql.SQLException;
import java.util.Date;

public class KillBoss {
    DataBase dataBase = new DataBase();
    DailyQuest dailyQuest = new DailyQuest();
    private PlayerStat getPlayerStat(Player p) throws SQLException {
        PlayerStat playerStat = dataBase.findUUID(p.getUniqueId().toString());
        if(playerStat == null){
            playerStat = new PlayerStat(p.getUniqueId().toString(),0,0,0,0,0,0, 0,new Date(), new Date());
            return playerStat;
        }
        return playerStat;
    }
    public void killBoss(Player p, Entity entity){
        PersistentDataContainer data = p.getPersistentDataContainer();
        PersistentDataContainer entityData = entity.getPersistentDataContainer();
        if(!(entity instanceof LivingEntity)) return;
        int[] slot = data.get(ElementalProject.dailyList(), PersistentDataType.INTEGER_ARRAY);
        if(slot ==  null || slot[3] == 0) return;

        try{
            PlayerStat playerStat = getPlayerStat(p);



            switch (slot[3]){
                case 1->{
                    if(entity.getCustomName().equals("boss")){
                        dailyQuest.questClear(p);
                        slot[3] = 0;
                    }
                }
                case 2 ->{

                }
                case 3 ->{

                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
