package org.example.contentplugin.elementalproject.contents.dailyQuest.object;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.SQLDB.DataBase;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.contents.dailyQuest.DailyQuest;

import java.sql.SQLException;
import java.util.Date;

public class KillMob {

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


    public void dailyQuestKillMob(Player p, Entity entity){
        PersistentDataContainer entityData = entity.getPersistentDataContainer();

        PersistentDataContainer data = p.getPersistentDataContainer();
        int[] slot = data.get(ElementalProject.dailyList(), PersistentDataType.INTEGER_ARRAY);
        if(slot == null || slot[0] == 0) return;

        try{

            PlayerStat playerStat = getPlayerStat(p);
            switch (slot[0]){
                case 1 ->{
                    if(playerStat.getKillCount() <= 20){
                        playerStat.setKillCount(playerStat.getKillCount() + 1);
                        dataBase.updateData(playerStat);
                    }
                    else{
                        playerStat.setDailyQuest(playerStat.getDailyQuest() + 1);
                        playerStat.setKillCount(0);
                        playerStat.setBalance(playerStat.getBalance() + 100);
                        dataBase.updateData(playerStat);
                        dailyQuest.questClear(p);
                        slot[0] = 0;
                        p.sendMessage(ChatColor.YELLOW + "kill quest :" + ChatColor.GREEN + "Completed!");
                    }
                }
                case 2 ->{
                    if(playerStat.getLevel() <= 10) slot[0] = 1;
                    if(playerStat.getKillCount() <= 30){
                        if(entityData.get(ElementalProject.level(), PersistentDataType.INTEGER) <= 20) return;
                        playerStat.setKillCount(playerStat.getKillCount() + 1);
                        dataBase.updateData(playerStat);
                    }
                    else{
                        playerStat.setDailyQuest(playerStat.getDailyQuest() + 1);
                        playerStat.setKillCount(0);
                        playerStat.setBalance(playerStat.getBalance() + 200);
                        dataBase.updateData(playerStat);
                        dailyQuest.questClear(p);
                        slot[0] = 0;
                        p.sendMessage(ChatColor.YELLOW + "kill quest :" + ChatColor.GREEN + "Completed!");
                    }
                }
                case 3 ->{
                    if(playerStat.getLevel() <= 10) slot[0] = 1;
                    else if(playerStat.getLevel() <= 20) slot[0] = 2;
                    if(playerStat.getKillCount() <= 40){
                        if(entityData.get(ElementalProject.level(), PersistentDataType.INTEGER) <= 30) return;
                        playerStat.setKillCount(playerStat.getKillCount() + 1);
                        dataBase.updateData(playerStat);
                    }
                    else{
                        playerStat.setDailyQuest(playerStat.getDailyQuest() + 1);
                        playerStat.setKillCount(0);
                        playerStat.setBalance(playerStat.getBalance() + 300);
                        dataBase.updateData(playerStat);
                        dailyQuest.questClear(p);
                        slot[0] = 0;
                        p.sendMessage(ChatColor.YELLOW + "kill quest :" + ChatColor.GREEN + "Completed!");
                    }

                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
