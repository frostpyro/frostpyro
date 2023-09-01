package org.example.contentplugin.elementalproject.SQLDB;

import org.example.contentplugin.elementalproject.SQLDB.DBset.SQLBase;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;

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
}

