package org.example.contentplugin.elementalproject.contents.playerSkill.skills;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.example.contentplugin.elementalproject.SQLDB.DataBase;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.baseAttack.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.enhance.AirEnhanceSword;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill1.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill2.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill3.AirSkill3Sword;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill3.EarthSkill3Sword;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill3.FireSkill3Sword;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.ultimate.AirUltSword;

import java.sql.SQLException;
import java.util.*;

public class Skills {
    private Map<UUID, long[]> cooldowns = new HashMap<>();

    private final DataBase dataBase = new DataBase();

    private final BaseAttack airBaseSword = new AirBaseSword();
    private final BaseAttack electronicBaseSword = new ElectBaseSword();
    private final BaseAttack fireBaseSword = new FireBaseSword();
    private final BaseAttack iceBaseSword = new IceBaseSword();
    private final BaseAttack lightBaseSword = new LightBaseSword();
    private final BaseAttack earthBaseSword = new EarthBaseSword();

    private final BaseAttack airEnhanceSword = new AirEnhanceSword();
    private final Right airSkill1Sword = new AirSkill1Sword();
    private final Right electronicSkill1Sword = new ElectSkill1Sword();
    private final Right earthSkill1Sword = new EarthSkill1Sword();
    private final Right iceSkill1Sword = new IceSkill1Sword();
    private final Right fireSkill1Sword = new FireSkill1Sword();
    private final Right lightSkill1Sword = new LightSkill1Sword();
    private final RNS iceSkill2Sword = new IceSkill2Sword();
    private final RNS fireSKill2Sword = new FireSkill2Sword();
    private final RNS earthSkill2Sword = new EarthSkill2Sword();
    private final RNS electronicSkill2Sword = new ElectSkill2Sword();
    private final RNS lightSkill2Sword = new LightSkill2Sword();
    private final RNS airSkill2Sword = new AirSkill2Sword();
    private final LNS airSkill3Sword = new AirSkill3Sword();
    private final LNS earthSkill3Sword = new EarthSkill3Sword();
    private final LNS fireSkill3Sword = new FireSkill3Sword();


    private final SNS airUltSword = new AirUltSword(3);

    private PlayerStat getPlayerStat(Player p) throws SQLException {
        PlayerStat playerStat = dataBase.findUUID(p.getUniqueId().toString());
        if(playerStat == null){
            playerStat = new PlayerStat(p.getUniqueId().toString(), 0, 0, 0, 0, 0, 0, 0,new Date(), new Date());
            dataBase.createData(playerStat);
        }
        return playerStat;
    }

    private void nullCheck(Player player){
        cooldowns.computeIfAbsent(player.getUniqueId(), k -> new long[5]);
    }
    public void baseAttackSkill(Player p, Set<UUID> entitySet, double sec){
        nullCheck(p);
        if(p.isSneaking()) return;
        try{
            PlayerStat playerStat = getPlayerStat(p);
            int skillClass = playerStat.getSkillClass();
            if(cooldowns.get(p.getUniqueId())[0] != 0){
                if(cooldowns.get(p.getUniqueId())[0] >= System.currentTimeMillis()) return;
                cooldowns.get(p.getUniqueId())[0] = 0L;
            }
            if(BaseAttackEnhancer.deactivated(p)){
                switch(skillClass){
                    case 1 ->{
                        if(!getItem(p, Material.NETHERITE_SWORD, 1)) return;
                        airBaseSword.attacking(p, entitySet);
                        earthBaseSword.attacking(p, entitySet);
                        fireBaseSword.attacking(p, entitySet);
                        iceBaseSword.attacking(p, entitySet);
                        lightBaseSword.attacking(p, entitySet);
                        electronicBaseSword.attacking(p, entitySet);
                    }
                    case 2 ->{
                        if(!getItem(p, Material.WOODEN_SWORD, 1)) return;
                    }
                    case 3 ->{
                        if(!getItem(p, Material.DIAMOND_SWORD, 1)) return;
                    }
                    case 4 ->{
                        if(!getItem(p, Material.WOODEN_HOE, 1)) return;
                    }
                }
            }
            else if(BaseAttackEnhancer.activated(p)){
                switch (skillClass){
                    case 1 ->{
                        if(!getItem(p, Material.NETHERITE_SWORD, 1)) return;
                        airEnhanceSword.attacking(p, entitySet);
                    }
                    case 2 ->{
                        if(!getItem(p, Material.WOODEN_SWORD, 1)) return;
                    }
                    case 3 ->{
                        if(!getItem(p, Material.DIAMOND_SWORD, 1)) return;
                    }
                    case 4 ->{
                        if(!getItem(p, Material.WOODEN_HOE, 1)) return;
                    }
                }
            }
            cooldowns.get(p.getUniqueId())[0] = System.currentTimeMillis() + (long)(sec*1000L);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void skill1(Player p, Set<UUID> entitySet, long sec){
        nullCheck(p);
        if(p.isSneaking()) return;
        try{
            PlayerStat playerStat = getPlayerStat(p);
            int skillClass = playerStat.getSkillClass();
            if(cooldowns.get(p.getUniqueId())[1] != 0){
                if(cooldowns.get(p.getUniqueId())[1] >= System.currentTimeMillis()) return;
                cooldowns.get(p.getUniqueId())[1] = 0L;
            }
            switch(skillClass){
                case 1 ->{
                    if(!getItem(p, Material.NETHERITE_SWORD, 1)) return;
                    airSkill1Sword.attacking(p, entitySet);
                    fireSkill1Sword.attacking(p, entitySet);
                    iceSkill1Sword.attacking(p, entitySet);
                    earthSkill1Sword.attacking(p, entitySet);
                    lightSkill1Sword.attacking(p, entitySet);
                    electronicSkill1Sword.attacking(p, entitySet);
                }
                case 2 ->{
                    if(!getItem(p, Material.WOODEN_SWORD, 1)) return;
                }
                case 3 ->{
                    if(!getItem(p, Material.DIAMOND_SWORD, 1)) return;
                }
                case 4 ->{
                    if(!getItem(p, Material.WOODEN_HOE, 1)) return;
                }
            }
            cooldowns.get(p.getUniqueId())[1] = System.currentTimeMillis() + (sec * 1000);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void skill2(Player p, Set<UUID> entitySet, long sec){
        if(!p.isSneaking()) return;
        nullCheck(p);
        try{
            PlayerStat playerStat = getPlayerStat(p);
            int skillClass = playerStat.getSkillClass();
            if(cooldowns.get(p.getUniqueId())[2] != 0){
                if(cooldowns.get(p.getUniqueId())[2] >= System.currentTimeMillis()) return;
                cooldowns.get(p.getUniqueId())[2] = 0L;
            }
            switch(skillClass){
                case 1 ->{
                    if(!getItem(p, Material.NETHERITE_SWORD, 1)) return;
                    airSkill2Sword.attacking(p, entitySet);
                    electronicSkill2Sword.attacking(p, entitySet);
                    iceSkill2Sword.attacking(p, entitySet);
                    fireSKill2Sword.attacking(p, entitySet);
                    earthSkill2Sword.attacking(p, entitySet);
                    lightSkill2Sword.attacking(p, entitySet);
                }
                case 2 ->{
                    if(!getItem(p, Material.WOODEN_SWORD, 1)) return;
                }
                case 3 ->{
                    if(!getItem(p, Material.DIAMOND_SWORD, 1)) return;
                }
                case 4 ->{
                    if(!getItem(p, Material.WOODEN_HOE, 1)) return;
                }
            }
            cooldowns.get(p.getUniqueId())[2] = System.currentTimeMillis() + (sec * 1000);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void skill3(Player p, Set<UUID> entitySet, long sec){
        if(!p.isSneaking()) return;
        nullCheck(p);
        try{
            PlayerStat playerStat = getPlayerStat(p);
            if(cooldowns.get(p.getUniqueId())[3] != 0){
                if(cooldowns.get(p.getUniqueId())[3] >= System.currentTimeMillis()) return;
                cooldowns.get(p.getUniqueId())[3] = 0L;
            }
            int skillClass = playerStat.getSkillClass();
            switch(skillClass){
                case 1 ->{
                    if(!getItem(p, Material.NETHERITE_SWORD, 1)) return;
                    airSkill3Sword.attacking(p, entitySet);
                }
                case 2 ->{
                    if(!getItem(p, Material.WOODEN_SWORD, 1)) return;
                }
                case 3 ->{
                    if(!getItem(p, Material.DIAMOND_SWORD, 1)) return;
                }
                case 4 ->{
                    if(!getItem(p, Material.WOODEN_HOE, 1)) return;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        cooldowns.get(p.getUniqueId())[3] = System.currentTimeMillis() + (sec * 1000);
    }

    public void ultimateSkill(Player p, Set<UUID> entitySet, long sec){
        nullCheck(p);
        try{
            PlayerStat playerStat = getPlayerStat(p);
            int skillClass = playerStat.getSkillClass();
            if(cooldowns.get(p.getUniqueId())[4] != 0){
                if(cooldowns.get(p.getUniqueId())[4] >= System.currentTimeMillis()) return;
                cooldowns.get(p.getUniqueId())[4] = 0L;
            }
            switch(skillClass){
                case 1 ->{
                    if(!getItem(p, Material.NETHERITE_SWORD, 1)) return;
                    airUltSword.attacking(p, entitySet);
                }
                case 2 ->{
                    if(!getItem(p, Material.WOODEN_SWORD, 1)) return;
                }
                case 3 ->{
                    if(!getItem(p, Material.DIAMOND_SWORD, 1)) return;
                }
                case 4 ->{
                    if(!getItem(p, Material.WOODEN_HOE, 1)) return;
                }
            }
            cooldowns.get(p.getUniqueId())[4] = System.currentTimeMillis() + (sec * 1000);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("all")
    private boolean getItem(Player p, Material material, int model){
        ItemStack itemStack = p.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(!itemStack.hasItemMeta()) return false;
        if(!itemStack.getItemMeta().hasCustomModelData()) return false;

        return itemStack.getType().equals(material) && itemMeta.getCustomModelData() == model;
    }
}
