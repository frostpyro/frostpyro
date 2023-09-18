package org.example.contentplugin.elementalproject.SQLDB;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.SQLDB.DBset.SQLBase;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;

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

    public void configFunc(){
        ElementalProject plugin = ElementalProject.getPlugin();
        plugin.saveConfig();
        File configFile = new File(plugin.getDataFolder(),  "config.yml");
        if(configFile.length() != 0) return;
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
    }
}

