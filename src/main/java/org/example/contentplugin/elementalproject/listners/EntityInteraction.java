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
import org.bukkit.event.player.PlayerInteractEvent;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.SQLDB.DataBase;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.contents.dailyQuest.DailyQuestGet;
import org.example.contentplugin.elementalproject.contents.leveling.LevelPoint;
import org.example.contentplugin.elementalproject.control.Clicking;
import org.example.contentplugin.elementalproject.control.api.system.ExpAddSkill;
import org.example.contentplugin.elementalproject.control.leftClick.LeftClick;
import org.example.contentplugin.elementalproject.control.rightLeftLeft.RLL;
import org.example.contentplugin.elementalproject.control.rightLeftRight.RLR;
import org.example.contentplugin.elementalproject.control.rightRightLeft.RRL;
import org.example.contentplugin.elementalproject.control.rightRightRight.RRR;

import java.net.http.WebSocket;
import java.sql.SQLException;
import java.util.Date;

public class EntityInteraction implements Listener {
    LevelPoint levelPoint = new LevelPoint();
    DailyQuestGet dailyQuest = new DailyQuestGet();
    ExpAddSkill exp = new ExpAddSkill();
    DataBase dataBase = new DataBase();
    Clicking left = new LeftClick();
    Clicking rll = new RLL();
    Clicking rlr = new RLR();
    Clicking rrr = new RRR();
    Clicking rrl = new RRL();
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
    private void entityDeath(EntityDeathEvent event){
        Entity entity = event.getEntity();
        if(!(entity instanceof LivingEntity)) return;

        if(exp.getKiller(entity) == null) return;

        exp.addExp(entity, Bukkit.getPlayer(exp.getKiller(entity)));
    }

    @EventHandler
    private void entityDamage(EntityDamageByEntityEvent event){
        Entity entity = event.getEntity();
        if(event.getDamager() instanceof Player){
            exp.setKiller(entity, (Player) event.getDamager());
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
    private void entitySpawn(EntitySpawnEvent event){
        Entity entity = event.getEntity();
        levelPoint.levelSetting(entity);
        levelPoint.maxHealthMod(entity);
    }
}
