package org.example.contentplugin.elementalproject.system.statModify;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.system.DBset.DataBase;
import org.example.contentplugin.elementalproject.system.playerData.PlayerStat;

import java.sql.SQLException;
import java.util.Date;

public class StatModify {
    DataBase dataBase = new DataBase();

    private PlayerStat getPlayerStat(Player p) throws SQLException {
        PlayerStat playerStat = dataBase.findUUID(p.getUniqueId().toString());
        if(playerStat == null){
            playerStat = new PlayerStat(p.getUniqueId().toString(), 0, 0, 0, 0, 0, 0, 0,new Date(), new Date());
            dataBase.createData(playerStat);
        }
        return playerStat;
    }

    public void statAdd(Player p){
        try{
            PlayerStat pS = getPlayerStat(p);
            if(pS.getPoint() < 1) return;
            pS.setExp(pS.getExp() - 1);
            dataBase.updateData(pS);
        }catch (SQLException e){

        }
    }
}
