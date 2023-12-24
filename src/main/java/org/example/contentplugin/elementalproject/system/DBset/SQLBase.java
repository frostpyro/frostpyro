package org.example.contentplugin.elementalproject.system.DBset;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.system.playerData.PlayerStat;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputFilter;
import java.sql.*;
import java.util.Objects;

public class SQLBase {
    private Connection connection;

    private File file;
    private FileConfiguration config;


    public Connection getConnection() throws SQLException{

        if(connection != null){
            return connection;
        }

        file = new File(ElementalProject.getPlugin().getDataFolder(), "dataBase.yml");
        if(!file.exists()) return null;
        if(config == null){
            config = new YamlConfiguration();
        }
        try{
            config.load(file);
        }
        catch (IOException| InvalidConfigurationException e){
            return null;
        }
        ConfigurationSection section = config.getConfigurationSection("MySQL");
        if(section == null) return null;
        String url = section.getString("URL");

        String name = section.getString("name");

        String password = section.getString("password");

        if(url == null||name == null||password == null){
            return null;
        }

        Connection connection = DriverManager.getConnection(url, name, password);

        this.connection = connection;

        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Connected to database");

        return connection;
    }
    public void initialize() throws SQLException{
        if(getConnection() == null) return;
        ConfigurationSection section = config.getConfigurationSection("MySQL");
        if(section == null) return;
        Statement statement = getConnection().createStatement();

        String sql = section.getString("quarry");

        statement.execute(sql);

        statement.close();
    }

    public PlayerStat findUUID(String uuid) throws SQLException{
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM player_data WHERE uuid = ?");
        statement.setString(1, uuid);

        ResultSet resultSet = statement.executeQuery();

        PlayerStat playerStat;

        if(resultSet.next()){
            playerStat = new PlayerStat(resultSet.getString("uuid"), resultSet.getInt("level"), resultSet.getInt("killCount"), resultSet.getDouble("exp"), resultSet.getDouble("balance"), resultSet.getInt("skillClass"), resultSet.getInt("point"), resultSet.getInt("dailyQuest"), resultSet.getDate("lastLogin"), resultSet.getDate("lastLogout"));
            statement.close();

            return playerStat;
        }

        statement.close();
        return null;
    }

    public void createData(PlayerStat playerStat) throws SQLException{
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO player_data(uuid, level, killCount, exp, balance, skillClass, point, dailyQuest, lastLogin, lastLogout) VALUES(?,?,?,?,?,?,?,?,?,?)");

        statement.setString(1, playerStat.getPlayerUUID());
        statement.setInt(2, playerStat.getLevel());
        statement.setInt(3, playerStat.getKillCount());
        statement.setDouble(4, playerStat.getExp());
        statement.setDouble(5, playerStat.getBalance());
        statement.setInt(6,playerStat.getSkillClass());
        statement.setInt(7, playerStat.getPoint());
        statement.setInt(8, playerStat.getDailyQuest());
        statement.setDate(9, new Date(playerStat.getLastLogin().getTime()));
        statement.setDate(10, new Date(playerStat.getLastLogout().getTime()));

        statement.executeUpdate();

        statement.close();
    }

    public void updateData(PlayerStat playerStat) throws SQLException{

        PreparedStatement statement = getConnection().prepareStatement("UPDATE player_data SET level = ?, killCount = ?, exp = ?, balance = ?, skillClass = ?, point = ?, dailyQuest = ?, lastLogin = ?, lastLogout = ? WHERE uuid = ?");

        statement.setInt(1, playerStat.getLevel());
        statement.setInt(2, playerStat.getKillCount());
        statement.setDouble(3, playerStat.getExp());
        statement.setDouble(4, playerStat.getBalance());
        statement.setInt(5,playerStat.getSkillClass());
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
    public void sqlUpdate() throws SQLException{
        file = new File(ElementalProject.getPlugin().getDataFolder(), "dataBase.yml");
        if(!file.exists()) return;
        if(config == null){
            config = new YamlConfiguration();
        }
        try{
            config.load(file);
        }
        catch (IOException| InvalidConfigurationException e){
            e.printStackTrace();
            return;
        }
        ConfigurationSection section = config.getConfigurationSection("MySQL");
        if(section == null){
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "section:MySQL is NULL!");
            return;
        }
        String quarry = section.getString("modQuarry");
        if(Objects.equals(quarry, "")){
            return;
        }
        PreparedStatement statement = getConnection().prepareStatement(quarry);
        section.set("modQuarry", "");

        statement.close();
    }
}
