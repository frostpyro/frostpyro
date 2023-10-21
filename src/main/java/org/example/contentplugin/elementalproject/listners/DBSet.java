package org.example.contentplugin.elementalproject.listners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.SQLDB.DataBase;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.contents.dailyQuest.DailyQuestGet;

import java.sql.SQLException;
import java.util.Date;



public class DBSet implements Listener {

    DailyQuestGet dailyQuest = new DailyQuestGet("differentDay");
    DataBase dataBase = new DataBase();

    public DBSet(ElementalProject plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    private PlayerStat getPlayerStat(Player p) throws SQLException {
        PlayerStat playerStat = dataBase.findUUID(p.getUniqueId().toString());
        if(playerStat == null){
            playerStat = new PlayerStat(p.getUniqueId().toString(), 0, 0, 0, 0, 0, 0, 0,new Date(), new Date());
            dataBase.createData(playerStat);
        }
        return playerStat;
    }


    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        try{
            PlayerStat playerStat = getPlayerStat(p);
            playerStat.setLastLogin(new Date());
            dailyQuest.countReset(p);
            dataBase.updateData(playerStat);

        }catch (SQLException e){
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "error occur while updating player's last login");
        }

    }

    @EventHandler
    private void onLeft(PlayerQuitEvent event){
        Player p = event.getPlayer();
        try{
            PlayerStat playerStat = getPlayerStat(p);

            playerStat.setLastLogout(new Date());
            dataBase.updateData(playerStat);
        }catch (SQLException e){
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "error occur while updating player's last logout");
        }
    }

}
