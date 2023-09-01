package org.example.contentplugin.elementalproject.contents.dailyQuest;




import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.SQLDB.DataBase;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;


import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;


public class DailyQuest {

    DataBase dataBase = new DataBase();

    Random random = new Random();

    private PlayerStat getPlayerStat(Player p) throws SQLException {
        PlayerStat playerStat = dataBase.findUUID(p.getUniqueId().toString());
        if(playerStat == null){
            playerStat = new PlayerStat(p.getUniqueId().toString(), 0, 0, 0, 0, 0, 0, 0,new Date(), new Date());
            dataBase.createData(playerStat);
        }
        return playerStat;
    }

    public void questClear(Player p){
        try{
            PlayerStat playerStat = getPlayerStat(p);
            if(playerStat.getDailyQuest() >= 4) return;
            playerStat.setDailyQuest(playerStat.getDailyQuest() + 1);
            dataBase.updateData(playerStat);
        }
        catch (SQLException e){
            e.printStackTrace();

        }
    }
    public int questInt(){
        int randInt = random.nextInt(3);
        return randInt + 1;
    }

    public void countReset(Player p, String str){
        LocalTime time = LocalTime.now();
        PersistentDataContainer data = p.getPersistentDataContainer();
        if(!data.has(ElementalProject.dailyList(), PersistentDataType.INTEGER_ARRAY)){
            data.set(ElementalProject.dailyList(), PersistentDataType.INTEGER_ARRAY, new int[4]);
        }

        int[] slot = data.get(ElementalProject.dailyList(), PersistentDataType.INTEGER_ARRAY);
        if(slot == null) return;
        try{

            PlayerStat playerStat = getPlayerStat(p);
            switch (str){
                case "differentDay" ->{
                    if(playerStat.getLastLogin() == playerStat.getLastLogout()) return;
                    playerStat.setDailyQuest(0);
                    dataBase.updateData(playerStat);
                    for(int i = 0; i < 4; i ++){
                        slot[i] = questInt();
                    }
                }

                case "midnight" ->{
                    if(!(time.getHour() == 0 && time.getMinute() == 0 && time.getSecond() == 0)) return;
                    playerStat.setDailyQuest(0);
                    dataBase.updateData(playerStat);
                    for(int i = 0; i < 4; i ++){
                        slot[i] = questInt();
                    }
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
