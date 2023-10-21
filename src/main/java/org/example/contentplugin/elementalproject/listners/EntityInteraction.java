package org.example.contentplugin.elementalproject.listners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.SQLDB.DataBase;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.contents.dailyQuest.DailyQuestGet;
import org.example.contentplugin.elementalproject.contents.leveling.LevelPoint;
import org.example.contentplugin.elementalproject.interaction.click.Clicking;
import org.example.contentplugin.elementalproject.interaction.interacting.Summoning;


import java.net.http.WebSocket;
import java.sql.SQLException;
import java.util.Date;

public class EntityInteraction implements Listener {
    LevelPoint levelPoint = new LevelPoint();
    DailyQuestGet dailyQuest = new DailyQuestGet();

    DataBase dataBase = new DataBase();

    Clicking clicking = new Clicking();

    Summoning summoning = new Summoning();

    private PlayerStat getPlayerStat(Player p) throws SQLException {
        PlayerStat playerStat = dataBase.findUUID(p.getUniqueId().toString());
        if(playerStat == null){
            playerStat = new PlayerStat(p.getUniqueId().toString(), 0, 0, 0, 0, 0, 0, 0,new Date(), new Date());
            dataBase.createData(playerStat);
        }
        return playerStat;
    }

    public EntityInteraction(ElementalProject plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    private void entityDeath(EntityDeathEvent event){
        Entity entity = event.getEntity();
        if(!(entity instanceof LivingEntity)) return;


    }

    @EventHandler
    private void swapping(PlayerItemHeldEvent event){
        summoning.playerItemChange(event);
    }

    @EventHandler
    private void entityInteract(PlayerInteractAtEntityEvent event){
        clicking.rightClick(event);
    }

    @EventHandler
    private void entityDamage(EntityDamageByEntityEvent event){
        Entity entity = event.getEntity();
        if(!(event.getDamager() instanceof Player)) return;

        clicking.leftClick(event);


    }

    @EventHandler
    private void entityKill(EntityDeathEvent event){
        LivingEntity entity = event.getEntity();
        Player p = entity.getKiller();
        if(p == null) return;
        dailyQuest.killMob(p, entity);
        dailyQuest.killBoss(p, entity);
        levelPoint.expModifyByMob(p, entity);
        levelPoint.levelModifier(p);
    }

    @EventHandler
    private void entitySpawn(EntitySpawnEvent event){
        Entity entity = event.getEntity();
        levelPoint.levelSetting(entity);
        levelPoint.maxHealthMod(entity);
    }
}
