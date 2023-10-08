package org.example.contentplugin.elementalproject.control.api.system;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.SQLDB.DataBase;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.contents.leveling.LevelPoint;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExpAddSkill extends LevelPoint {
    private final Map<UUID, UUID> killer = new HashMap<>();
    DataBase dataBase = new DataBase();
    private PlayerStat getPlayerStat(Player p) throws SQLException {
        PlayerStat playerStat = dataBase.findUUID(p.getUniqueId().toString());
        if(playerStat == null){
            playerStat = new PlayerStat(p.getUniqueId().toString(), 0, 0, 0, 0, 0, 0, 0,new Date(), new Date());
            dataBase.createData(playerStat);
        }
        return playerStat;
    }
    public void setKiller(Entity entity, Player player){
        killer.put(entity.getUniqueId(), player.getUniqueId());
    }

    public UUID getKiller(Entity entity){
        return killer.get(entity.getUniqueId());
    }

    public void addExp(Entity entity, Player player){
        try{
            PlayerStat playerStat = getPlayerStat(player);
            if(getKiller(entity) == UUID.fromString(playerStat.getPlayerUUID())){
                expModifyByMob(player, entity);
                dataBase.updateData(playerStat);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
