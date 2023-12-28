package org.example.contentplugin.elementalproject.system.statModify;

import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.system.DBset.DataBase;
import org.example.contentplugin.elementalproject.system.playerData.PlayerStat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void statAdd(Player p, String type){
        try{
            PlayerStat pS = getPlayerStat(p);
            if(pS.getPoint() < 1) return;
            pS.setExp(pS.getExp() - 1);
            dataBase.updateData(pS);
            PreparedStatement statement = dataBase.getConnection().prepareStatement("UPDATE player_data SET = ? = ? WHERE uuid = ?");
            PreparedStatement statement2 = dataBase.getConnection().prepareStatement("SELECT * FROM player_data WHERE uuid = ?");
            statement2.setString(1, pS.getPlayerUUID());
            ResultSet resultSet = statement2.getResultSet();
            statement2.close();
            statement.setString(1, type);
            statement.setInt(2, resultSet.getInt(type) + 1);
            statement.close();
        }catch (SQLException e){

        }
    }

    public int getAttackStat(Player p){
        try{
            PreparedStatement statement = dataBase.getConnection().prepareStatement("SELECT * FROM player_data WHERE uuid = ?");
            statement.setString(1, p.getUniqueId().toString());
            ResultSet resultSet = statement.getResultSet();
            statement.close();
            return resultSet.getInt("attack");
        }catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    public int getDefence(Player p){

        return 0;
    }

    public int getHealth(Player p){
        return 0;
    }

    public int getCoolDown(Player p){
        return 0;
    }
}
