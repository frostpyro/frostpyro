package org.example.contentplugin.elementalproject.contents.dailyQuest.object;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.system.DBset.DataBase;
import org.example.contentplugin.elementalproject.system.playerData.PlayerStat;

import java.sql.SQLException;
import java.util.Date;

public class NPCInteract {
    DataBase dataBase = new DataBase();
    private PlayerStat getPlayerStat(Player p) throws SQLException {
        PlayerStat playerStat = dataBase.findUUID(p.getUniqueId().toString());
        if(playerStat == null){
            playerStat = new PlayerStat(p.getUniqueId().toString(),0,0,0,0,0,0, 0,new Date(), new Date());
            return playerStat;
        }
        return playerStat;
    }
    public void npcInteract(Player p, Entity entity){
        PersistentDataContainer data = p.getPersistentDataContainer();


        int[] slot = data.get(ElementalProject.dailyList(), PersistentDataType.INTEGER_ARRAY);
        if(slot ==  null||slot[2] == 0) return;

        try{
            PlayerStat playerStat = getPlayerStat(p);
            switch (slot[2]){

            }
        }
        catch (SQLException e){

        }
    }
}
