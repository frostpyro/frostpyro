package org.example.contentplugin.elementalproject.system.DBset;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.system.DBset.SQLBase;
import org.example.contentplugin.elementalproject.system.playerData.PlayerStat;

import java.io.File;
import java.sql.*;

public class DataBase extends SQLBase{
    @Override
    public void initialize() throws SQLException {
        super.initialize();
    }

    @Override
    public PlayerStat findUUID(String uuid) throws SQLException {
        return super.findUUID(uuid);
    }

    @Override
    public void createData(PlayerStat playerStat) throws SQLException {
        super.createData(playerStat);
    }

    @Override
    public void updateData(PlayerStat playerStat) throws SQLException {
        super.updateData(playerStat);
    }

    @Override
    public void removeData(PlayerStat playerStat) throws SQLException {
        super.removeData(playerStat);
    }

    public void setName(Player player) throws SQLException{
        PreparedStatement statement = getConnection().prepareStatement("UPDATE player_data SET name = ? WHERE uuid = ?");
        statement.setString(1, player.getName());
        statement.setString(2, player.getUniqueId().toString());
        statement.executeUpdate();
        statement.close();
    }
    public void configFunc(){

        File configFile = new File(ElementalProject.getPlugin().getDataFolder(),  "config.yml");
        if(configFile.length() != 0) return;
        ElementalProject.getPlugin().getConfig().options().copyDefaults(true);
        ElementalProject.getPlugin().saveConfig();
        Bukkit.getConsoleSender().sendMessage("config enabled!");
    }
}

