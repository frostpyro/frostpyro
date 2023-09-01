package org.example.contentplugin.elementalproject.SQLDB.DBset;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;

import java.sql.*;

public class SQLBase {
    private Connection connection;
    public Connection getConnection() throws SQLException{

        if(connection != null){
            return connection;
        }

        String url = "jdbc:mysql://localhost/player_stat";

        String name = "root";

        String password = "";

        Connection connection = DriverManager.getConnection(url, name, password);

        this.connection = connection;

        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Connected to database");

        return connection;
    }
    public void initialize() throws SQLException{
        Statement statement = getConnection().createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS player_data (uuid varchar(36) primary key, level int, killCount int, exp double, balance double, mana int, point int, dailyQuest int, lastLogin DATE, lastLogout DATE)";

        statement.execute(sql);

        statement.close();
    }

    public PlayerStat findUUID(String uuid) throws SQLException{
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM player_data WHERE uuid = ?");
        statement.setString(1, uuid);

        ResultSet resultSet = statement.executeQuery();

        PlayerStat playerStat;

        if(resultSet.next()){
            playerStat = new PlayerStat(resultSet.getString("uuid"), resultSet.getInt("level"), resultSet.getInt("killCount"), resultSet.getDouble("exp"), resultSet.getDouble("balance"), resultSet.getInt("mana"), resultSet.getInt("point"), resultSet.getInt("dailyQuest"), resultSet.getDate("lastLogin"), resultSet.getDate("lastLogout"));
            statement.close();

            return playerStat;
        }

        statement.close();
        return null;
    }

    public void createData(PlayerStat playerStat) throws SQLException{
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO player_data(uuid, level, killCount, exp, balance, mana, point, dailyQuest, lastLogin, lastLogout) VALUES(?,?,?,?,?,?,?,?,?,?)");

        statement.setString(1, playerStat.getPlayerUUID());
        statement.setInt(2, playerStat.getLevel());
        statement.setInt(3, playerStat.getKillCount());
        statement.setDouble(4, playerStat.getExp());
        statement.setDouble(5, playerStat.getBalance());
        statement.setInt(6,playerStat.getMana());
        statement.setInt(7, playerStat.getPoint());
        statement.setInt(8, playerStat.getDailyQuest());
        statement.setDate(9, new Date(playerStat.getLastLogin().getTime()));
        statement.setDate(10, new Date(playerStat.getLastLogout().getTime()));

        statement.executeUpdate();

        statement.close();
    }

    public void updateData(PlayerStat playerStat) throws SQLException{

        PreparedStatement statement = getConnection().prepareStatement("UPDATE player_data SET level = ?, killCount = ?, exp = ?, balance = ?, mana = ?, point = ?, dailyQuest = ?, lastLogin = ?, lastLogout = ? WHERE uuid = ?");

        statement.setInt(1, playerStat.getLevel());
        statement.setInt(2, playerStat.getKillCount());
        statement.setDouble(3, playerStat.getExp());
        statement.setDouble(4, playerStat.getBalance());
        statement.setInt(5,playerStat.getMana());
        statement.setInt(6, playerStat.getPoint());
        statement.setInt(7, playerStat.getDailyQuest());
        statement.setDate(8, new Date(playerStat.getLastLogin().getTime()));
        statement.setDate(9, new Date(playerStat.getLastLogout().getTime()));
        statement.setString(10, playerStat.getPlayerUUID());

        statement.executeUpdate();

        statement.close();
    }
    public void removeData(PlayerStat playerStat) throws SQLException{
        PreparedStatement statement = getConnection().prepareStatement("DELETE FROM player_data WHERE uuid = ?");

        statement.setString(1, playerStat.getPlayerUUID());

        statement.executeUpdate();

        statement.close();
    }
}
