package org.example.contentplugin.elementalproject.contents.playerSkill.skills;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.SQLDB.DataBase;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.baseAttack.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill1.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill2.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill3.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.ultimate.AirUltSword;

import java.sql.SQLException;
import java.util.*;

public class Skills {
    private Map<UUID, long[]> cooldowns = new HashMap<>();

    private final DataBase dataBase = new DataBase();

    private BaseAttack baseAttack;
    private Right right;
    private RNS rns;
    private LNS lns;
    private SNS sns;

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
        PersistentDataContainer container = p.getPersistentDataContainer();
        if(!container.has(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY)) container.set(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY, new int[5]);

        int getSkill = container.get(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY)[0];
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
                        switch(getSkill){
                            case 1 -> baseAttack = new AirBaseSword();
                            case 2 -> baseAttack = new EarthBaseSword();
                            case 3 -> baseAttack = new ElectBaseSword();
                            case 4 -> baseAttack = new FireBaseSword();
                            case 5 -> baseAttack = new IceBaseSword();
                            case 6 -> baseAttack = new LightBaseSword();
                        }
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
            baseAttack.attacking(p, entitySet);
            cooldowns.get(p.getUniqueId())[0] = System.currentTimeMillis() + (long)(sec*1000L);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void skill1(Player p, Set<UUID> entitySet, long sec){
        nullCheck(p);
        if(p.isSneaking()) return;
        PersistentDataContainer container = p.getPersistentDataContainer();
        if(!container.has(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY)) container.set(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY, new int[5]);

        int getSkill = container.get(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY)[1];
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
                    right = new AirSkill1Sword();
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
            right.attacking(p, entitySet);
            cooldowns.get(p.getUniqueId())[1] = System.currentTimeMillis() + (sec * 1000);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void skill2(Player p, Set<UUID> entitySet, long sec){
        if(!p.isSneaking()) return;
        nullCheck(p);
        PersistentDataContainer container = p.getPersistentDataContainer();
        if(!container.has(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY)) container.set(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY, new int[5]);

        int getSkill = container.get(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY)[2];
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
                    rns = new AirSkill2Sword();
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
            rns.attacking(p, entitySet);
            cooldowns.get(p.getUniqueId())[2] = System.currentTimeMillis() + (sec * 1000);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void skill3(Player p, Set<UUID> entitySet, long sec){
        if(!p.isSneaking()) return;
        nullCheck(p);
        PersistentDataContainer container = p.getPersistentDataContainer();
        if(!container.has(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY)) container.set(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY, new int[5]);

        int getSkill = container.get(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY)[3];
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
                    lns = new AirSkill3Sword();
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
            lns.attacking(p, entitySet);
            cooldowns.get(p.getUniqueId())[3] = System.currentTimeMillis() + (sec * 1000);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void ultimateSkill(Player p, Set<UUID> entitySet, long sec){
        nullCheck(p);
        PersistentDataContainer container = p.getPersistentDataContainer();
        if(!container.has(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY)) container.set(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY, new int[5]);

        int getSkill = container.get(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY)[4];
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
                    sns = new AirUltSword(3);
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
            sns.attacking(p, entitySet);
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
