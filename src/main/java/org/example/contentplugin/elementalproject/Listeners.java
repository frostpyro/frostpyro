package org.example.contentplugin.elementalproject;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataType;
import org.example.contentplugin.elementalproject.SQLDB.DataBase;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.contents.dailyQuest.DailyQuestGet;
import org.example.contentplugin.elementalproject.contents.leveling.LevelPoint;
import org.example.contentplugin.elementalproject.control.Clicking;
import org.example.contentplugin.elementalproject.control.leftClick.LeftClick;
import org.example.contentplugin.elementalproject.control.rightLeftLeft.RLL;
import org.example.contentplugin.elementalproject.control.rightLeftRight.RLR;
import org.example.contentplugin.elementalproject.control.rightRightLeft.RRL;
import org.example.contentplugin.elementalproject.control.rightRightRight.RRR;

import java.sql.SQLException;
import java.util.Date;


public class Listeners implements Listener {

    DailyQuestGet dailyQuest = new DailyQuestGet("differentDay");
    LevelPoint levelPoint = new LevelPoint();


    DataBase dataBase = new DataBase();
    Clicking left = new LeftClick();
    Clicking rll = new RLL();
    Clicking rlr = new RLR();
    Clicking rrr = new RRR();
    Clicking rrl = new RRL();
    public Listeners(ElementalProject plugin){
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
    private void listener(PlayerInteractEvent event){
        Player p = event.getPlayer();
        try{
            PlayerStat playerStat = getPlayerStat(p);
            left.clicking(event, playerStat);
            rll.clicking(event, playerStat);
            rlr.clicking(event, playerStat);
            rrl.clicking(event, playerStat);
            rrr.clicking(event, playerStat);
        }catch (SQLException e){
            e.printStackTrace();
        }
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

    @EventHandler
    private void entityKill(EntityDeathEvent event){
        Entity entity = event.getEntity();
        Player p = ((LivingEntity) entity).getKiller();
        if(p == null) return;
        dailyQuest.killMob(p, entity);
        dailyQuest.killBoss(p, entity);
        levelPoint.expModifyByMob(p, entity);
        levelPoint.levelModifier(p);
    }

    @EventHandler
    private void entityInteract(PlayerInteractAtEntityEvent event){
        Player p = event.getPlayer();
        Entity entity = event.getRightClicked();

    }

    @EventHandler
    private void entitySpawn(EntitySpawnEvent event){
        Entity entity = event.getEntity();
        levelPoint.levelSetting(entity);
        levelPoint.maxHealthMod(entity);
    }

    @EventHandler
    private void entityDamage(EntityDamageByEntityEvent event){


        //TODO: modify damage by stat and player's build

    }
}
