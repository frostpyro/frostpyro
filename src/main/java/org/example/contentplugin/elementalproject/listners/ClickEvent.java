package org.example.contentplugin.elementalproject.listners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.SQLDB.DataBase;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.interaction.click.Clicking;

import java.sql.SQLException;
import java.util.Date;

public class ClickEvent implements Listener {
    DataBase dataBase = new DataBase();

    Clicking clicking = new Clicking();
    private PlayerStat getPlayerStat(Player p) throws SQLException {
        PlayerStat playerStat = dataBase.findUUID(p.getUniqueId().toString());
        if(playerStat == null){
            playerStat = new PlayerStat(p.getUniqueId().toString(), 0, 0, 0, 0, 0, 0, 0,new Date(), new Date());
            dataBase.createData(playerStat);
        }
        return playerStat;
    }
    public ClickEvent(ElementalProject plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void entityDamageClick(EntityDamageByEntityEvent event){
        clicking.clickEntityLeft(event);
    }

    @EventHandler
    private void leftClick(PlayerInteractEvent event){
        clicking.clickAirLeft(event);
    }

    @EventHandler
    private void entityRightClick(PlayerInteractAtEntityEvent event){
        clicking.clickEntityRight(event);
    }

    @EventHandler
    private void rightClick(PlayerInteractEvent event){
        clicking.clickAirRight(event);
    }

    @EventHandler
    private void shiftClick(PlayerToggleSneakEvent event){
        clicking.shiftToggle(event);
    }

}
