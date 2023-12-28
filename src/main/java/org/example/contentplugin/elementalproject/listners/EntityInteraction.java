package org.example.contentplugin.elementalproject.listners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.system.DBset.DataBase;
import org.example.contentplugin.elementalproject.system.drop.Builds;
import org.example.contentplugin.elementalproject.system.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.contents.dailyQuest.DailyQuestGet;
import org.example.contentplugin.elementalproject.contents.entitySkill.EntitySkill;
import org.example.contentplugin.elementalproject.contents.leveling.LevelPoint;


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;

public class EntityInteraction implements Listener {
    LevelPoint levelPoint = new LevelPoint();
    DailyQuestGet dailyQuest = new DailyQuestGet();

    EntitySkill entitySkill = new EntitySkill();
    DataBase dataBase = new DataBase();

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

    private Builds builds(ItemStack item, int dropRate, int model, int id, FileConfiguration configuration) {
        Builds build;
        Class<Builds> init = Builds.class;
        try{
            build = init.getConstructor(ItemStack.class, int.class, int.class, int.class, FileConfiguration.class).newInstance(item, dropRate, model, id, configuration);
        }
        catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
            build = null;
        }
        return build;
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

        if(entity instanceof PigZombie){
            PersistentDataContainer data = entity.getPersistentDataContainer();
            if(!data.has(ElementalProject.level(), PersistentDataType.INTEGER)) return;

            if(data.get(ElementalProject.level(), PersistentDataType.INTEGER) < 40) return;
            ((PigZombie)entity).setAngry(true);
            ((PigZombie)entity).setAnger(10);
        }
    }

    @EventHandler
    private void entityTarget(EntityTargetEvent event){
        Entity entity = event.getEntity();
        if(!(event.getTarget() instanceof Player)) return;
        Entity target = event.getTarget();
        entitySkill.entitySkill(entity, target);
    }

    @EventHandler
    private void entityDeath(EntityDeathEvent event){

    }
}
