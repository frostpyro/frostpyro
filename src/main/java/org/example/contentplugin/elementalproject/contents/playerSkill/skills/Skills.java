package org.example.contentplugin.elementalproject.contents.playerSkill.skills;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.system.DataBase;
import org.example.contentplugin.elementalproject.system.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.archer.baseAttack.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.archer.enhance.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.archer.skill1.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.archer.skill2.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.archer.skill3.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.mage.enhance.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.mage.skill1.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.mage.skill2.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.mage.skill3.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.baseAttack.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.enhance.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.skill1.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.skill2.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.skill3.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.martial.ultimate.EarthUltMar;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.baseAttack.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.enhance.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill1.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill2.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.skill3.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.ultimate.FireUltSword;
import org.example.contentplugin.elementalproject.system.skillSetting.SkillList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    SkillList list = new SkillList();

    StatusModifier modifier = new StatusModifier();

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
        int init = 1;
        nullCheck(p);
        if(p.isSneaking()) return;
        PersistentDataContainer container = p.getPersistentDataContainer();
        if(!container.has(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY)) container.set(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY, new int[5]);

        int getSkill = container.get(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY)[0];
        int enhanceIndex = container.get(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY)[4];
        try{
            PlayerStat playerStat = getPlayerStat(p);
            int skillClass = playerStat.getSkillClass();
            if(cooldowns.get(p.getUniqueId())[0] != 0){
                if(cooldowns.get(p.getUniqueId())[0] >= System.currentTimeMillis()) return;
                cooldowns.get(p.getUniqueId())[0] = 0L;
            }
            if(skillClass == itemNum(p)) return;
            if(modifier.deactivated(p)){
                Class<? extends BaseAttack> skill = list.baseAttack(modifier.deactivated(p), skillClass, 4);
                if(skill == null){
                    return;
                }
                try{
                    baseAttack = skill.getDeclaredConstructor().newInstance();
                }catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e){
                    e.printStackTrace();
                    return;
                }
            }
            else if(modifier.activated(p)){
                Class<? extends BaseAttack> skill = list.baseAttack(!modifier.deactivated(p), skillClass, 4);
                if(skill == null){
                    return;
                }
                try{
                    baseAttack = skill.getDeclaredConstructor().newInstance();
                }catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e){
                    e.printStackTrace();
                    return;
                }
            }
            baseAttack.attacking(p, entitySet);
            cooldowns.get(p.getUniqueId())[0] = System.currentTimeMillis() + (long)((sec/modifier.attackSpeed(p))*1000L);
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
            if(modifier.activated(p)) return;
            PlayerStat playerStat = getPlayerStat(p);
            int skillClass = playerStat.getSkillClass();
            if(cooldowns.get(p.getUniqueId())[1] != 0){
                if(cooldowns.get(p.getUniqueId())[1] >= System.currentTimeMillis()) return;
                cooldowns.get(p.getUniqueId())[1] = 0L;
            }
            if(skillClass == itemNum(p)) return;
            Class<? extends Right> skill = list.skill1(skillClass, 4);
            if(skill == null){
                return;
            }
            try{
                right = skill.getDeclaredConstructor().newInstance();
            }catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
                return;
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
            if(modifier.activated(p)) return;
            PlayerStat playerStat = getPlayerStat(p);
            int skillClass = playerStat.getSkillClass();
            if(cooldowns.get(p.getUniqueId())[2] != 0){
                if(cooldowns.get(p.getUniqueId())[2] >= System.currentTimeMillis()) return;
                cooldowns.get(p.getUniqueId())[2] = 0L;
            }
            Class<? extends RNS> skill = list.skill2(skillClass, 4);
            if(skill == null){
                return;
            }
            try{
                rns = skill.getDeclaredConstructor().newInstance();
            }catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e){
                e.printStackTrace();
                return;
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
            if(modifier.activated(p)) return;
            PlayerStat playerStat = getPlayerStat(p);
            if(cooldowns.get(p.getUniqueId())[3] != 0){
                if(cooldowns.get(p.getUniqueId())[3] >= System.currentTimeMillis()) return;
                cooldowns.get(p.getUniqueId())[3] = 0L;
            }
            int skillClass = playerStat.getSkillClass();
            switch(skillClass){
                case 1 ->{
                    if(!getItem(p, Material.NETHERITE_SWORD, 1)) return;
                    lns = new FireSkill3Sword();
                    switch(getSkill) {
                        case 1 -> lns = new AirSkill3Sword();
                        case 2 -> lns = new EarthSkill3Sword();
                        case 3 -> lns = new ElectSkill3Sword();
                        case 4 -> lns = new FireSkill3Sword();
                        case 5 -> lns = new IceSkill3Sword();
                        case 6 -> lns = new LightSkill3Sword();
                    }
                }
                case 2 ->{
                    if(!getItem(p, Material.WOODEN_SWORD, 1)) return;
                    switch(getSkill) {
                        case 1 -> lns = new AirSkill3Archer();
                        case 2 -> lns = new EarthSkill3Archer();
                        case 3 -> lns = new ElectSkill3Archer();
                        case 4 -> lns = new FireSkill3Archer();
                        case 5 -> lns = new IceSkill3Archer();
                        case 6 -> lns = new LightSkill3Archer();
                    }
                }
                case 3 ->{
                    if(!getItem(p, Material.DIAMOND_SWORD, 1)) return;
                    switch(getSkill) {
                        case 1 -> lns = new AirSkill3Mar();
                        case 2 -> lns = new EarthSkill3Mar();
                        case 3 -> lns = new ElectSkill3Mar();
                        case 4 -> lns = new FireSkill3Mar();
                        case 5 -> lns = new IceSkill3Mar();
                        case 6 -> lns = new LightSkill3Mar();
                    }
                }
                case 4 ->{
                    if(!getItem(p, Material.WOODEN_HOE, 1)) return;
                    switch(getSkill) {
                        case 1 -> lns = new AirSkill3Mage();
                        case 2 -> lns = new EarthSkill3Mage();
                        case 3 -> lns = new ElectSkill3Mage();
                        case 4 -> lns = new FireSkill3Mage();
                        case 5 -> lns = new IceSkill3Mage();
                        case 6 -> lns = new LightSkill3Mage();
                    }
                }
            }
            if(lns == null) return;
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
                    sns = new FireUltSword(1);
                }
                case 2 ->{
                    if(!getItem(p, Material.WOODEN_SWORD, 1)) return;
                }
                case 3 ->{
                    if(!getItem(p, Material.DIAMOND_SWORD, 1)) return;
                    sns = new EarthUltMar(1);
                }
                case 4 ->{
                    if(!getItem(p, Material.WOODEN_HOE, 1)) return;
                }
            }
            if(sns == null) return;
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

    private int itemNum(Player p){
        ItemStack itemStack = p.getInventory().getItemInMainHand();
        switch (itemStack.getType()){
            case NETHERITE_SWORD -> {
                return 1;
            }
            case WOODEN_SWORD -> {
                return 2;
            }
            case DIAMOND_SWORD -> {
                return 3;
            }
            case WOODEN_HOE -> {
                return 4;
            }
        }
        return 0;
    }
}
