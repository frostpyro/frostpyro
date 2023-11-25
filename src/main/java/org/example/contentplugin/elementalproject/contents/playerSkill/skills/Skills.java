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
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.baseAttack.*;
import org.example.contentplugin.elementalproject.contents.playerSkill.skills.sword.enhance.*;
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
        int enhanceIndex = container.get(ElementalProject.skill(), PersistentDataType.INTEGER_ARRAY)[4];
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
                        switch(getSkill){
                            case 1 -> baseAttack = new AirBaseArcher();
                            case 2 -> baseAttack = new EarthBaseArcher();
                            case 3 -> baseAttack = new ElectBaseArcher();
                            case 4 -> baseAttack = new FireBaseArcher();
                            case 5 -> baseAttack = new IceBaseArcher();
                            case 6 -> baseAttack = new LightBaseArcher();
                        }
                    }
                    case 3 ->{
                        if(!getItem(p, Material.DIAMOND_SWORD, 1)) return;
                        switch(getSkill){
                            case 1 -> baseAttack = new AirBaseMar();
                            case 2 -> baseAttack = new EarthBaseMar();
                            case 3 -> baseAttack = new ElectBaseMar();
                            case 4 -> baseAttack = new FireBaseMar();
                            case 5 -> baseAttack = new IceBaseMar();
                            case 6 -> baseAttack = new LightBaseMar();
                        }
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
                        switch(enhanceIndex){
                            case 1 -> baseAttack = new AirEnhanceSword();
                            case 2 -> baseAttack = new EarthEnhanceSword();
                            case 3 -> baseAttack = new ElectEnhanceSword();
                            case 4 -> baseAttack = new FireEnhanceSword();
                            case 5 -> baseAttack = new IceEnhanceSword();
                            case 6 -> baseAttack = new LightEnhanceSword();
                        }
                    }
                    case 2 ->{
                        if(!getItem(p, Material.WOODEN_SWORD, 1)) return;
                        switch(enhanceIndex){
                            case 1 -> baseAttack = new AirEnhanceArcher();
                            case 2 -> baseAttack = new EarthEnhanceArcher();
                            case 3 -> baseAttack = new ElectEnhanceArcher();
                            case 4 -> baseAttack = new FireEnhanceArcher();
                            case 5 -> baseAttack = new IceEnhanceArcher();
                            case 6 -> baseAttack = new LightEnhanceArcher();
                        }
                    }
                    case 3 ->{
                        if(!getItem(p, Material.DIAMOND_SWORD, 1)) return;
                        switch (enhanceIndex){
                            case 1 -> baseAttack = new AirEnhanceMar();
                            case 2 -> baseAttack = new EarthEnhanceMar();
                            case 3 -> baseAttack = new ElectEnhanceMar();
                            case 4 -> baseAttack = new FireEnhanceMar();
                            case 5 -> baseAttack = new IceEnhanceMar();
                            case 6 -> baseAttack = new LightEnhanceMar();
                        }
                    }
                    case 4 ->{
                        if(!getItem(p, Material.WOODEN_HOE, 1)) return;
                        switch (enhanceIndex){
                            case 1 -> baseAttack = new AirEnhanceMage();
                            case 2 -> baseAttack = new EarthEnhanceMage();
                            case 3 -> baseAttack = new ElectEnhanceMage();
                            case 4 -> baseAttack = new FireEnhanceMage();
                            case 5 -> baseAttack = new IceEnhanceMage();
                            case 6 -> baseAttack = new LightEnhanceMage();
                        }
                    }
                }
            }
            if(baseAttack == null) return;
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
            if(BaseAttackEnhancer.activated(p)) return;
            PlayerStat playerStat = getPlayerStat(p);
            int skillClass = playerStat.getSkillClass();
            if(cooldowns.get(p.getUniqueId())[1] != 0){
                if(cooldowns.get(p.getUniqueId())[1] >= System.currentTimeMillis()) return;
                cooldowns.get(p.getUniqueId())[1] = 0L;
            }
            switch(skillClass){
                case 1 ->{
                    if(!getItem(p, Material.NETHERITE_SWORD, 1)) return;
                    switch(getSkill){
                        case 1 -> right = new AirSkill1Sword();
                        case 2 -> right = new EarthSkill1Sword();
                        case 3 -> right = new ElectSkill1Sword();
                        case 4 -> right = new FireSkill1Sword();
                        case 5 -> right = new IceSkill1Sword();
                        case 6 -> right = new LightSkill1Sword();
                    }
                }
                case 2 ->{
                    if(!getItem(p, Material.WOODEN_SWORD, 1)) return;
                    switch(getSkill){
                        case 1 -> right = new AirSkill1Archer();
                        case 2 -> right = new EarthSkill1Archer();
                        case 3 -> right = new ElectSkill1Archer();
                        case 4 -> right = new FireSkill1Archer();
                        case 5 -> right = new IceSkill1Archer();
                        case 6 -> right = new LightSkill1Archer();
                    }
                }
                case 3 ->{
                    if(!getItem(p, Material.DIAMOND_SWORD, 1)) return;
                    switch(getSkill){
                        case 1 -> right = new AirSkill1Mar();
                        case 2 -> right = new EarthSkill1Mar();
                        case 3 -> right = new ElectSkill1Mar();
                        case 4 -> right = new FireSkill1Mar();
                        case 5 -> right = new IceSkill1Mar();
                        case 6 -> right = new LightSkill1Mar();
                    }
                }
                case 4 ->{
                    if(!getItem(p, Material.WOODEN_HOE, 1)) return;
                    switch(getSkill){
                        case 1 -> right = new AirSkill1Mage();
                        case 2 -> right = new EarthSkill1Mage();
                        case 3 -> right = new ElectSkill1Mage();
                        case 4 -> right = new FireSkill1Mage();
                        case 5 -> right = new IceSkill1Mage();
                        case 6 -> right = new LightSkill1Mage();
                    }
                }
            }
            if(right == null) return;
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
            if(BaseAttackEnhancer.activated(p)) return;
            PlayerStat playerStat = getPlayerStat(p);
            int skillClass = playerStat.getSkillClass();
            if(cooldowns.get(p.getUniqueId())[2] != 0){
                if(cooldowns.get(p.getUniqueId())[2] >= System.currentTimeMillis()) return;
                cooldowns.get(p.getUniqueId())[2] = 0L;
            }
            switch(skillClass){
                case 1 ->{
                    if(!getItem(p, Material.NETHERITE_SWORD, 1)) return;
                    switch (getSkill){
                        case 1 -> rns = new AirSkill2Sword();
                        case 2 -> rns = new EarthSkill2Sword();
                        case 3 -> rns = new ElectSkill2Sword();
                        case 4 -> rns = new FireSkill2Sword();
                        case 5 -> rns = new IceSkill2Sword();
                        case 6 -> rns = new LightSkill2Sword();
                    }
                }
                case 2 ->{
                    if(!getItem(p, Material.WOODEN_SWORD, 1)) return;
                    switch (getSkill){
                        case 1 -> rns = new AirSkill2Archer();
                        case 2 -> rns = new EarthSkill2Archer();
                        case 3 -> rns = new ElectSkill2Archer();
                        case 4 -> rns = new FireSkill2Archer();
                        case 5 -> rns = new IceSkill2Archer();
                        case 6 -> rns = new LightSkill2Archer();
                    }
                }
                case 3 ->{
                    if(!getItem(p, Material.DIAMOND_SWORD, 1)) return;
                    switch (getSkill){
                        case 1 -> rns = new AirSkill2Mar();
                        case 2 -> rns = new EarthSkill2Mar();
                        case 3 -> rns = new ElectSkill2Mar();
                        case 4 -> rns = new FireSkill2Mar();
                        case 5 -> rns = new IceSkill2Mar();
                        case 6 -> rns = new LightSkill2Mar();
                    }
                }
                case 4 ->{
                    if(!getItem(p, Material.WOODEN_HOE, 1)) return;
                    switch (getSkill){
                        case 1 -> rns = new AirSkill2Mage();
                        case 2 -> rns = new EarthSkill2Mage();
                        case 3 -> rns = new ElectSkill2Mage();
                        case 4 -> rns = new FireSkill2Mage();
                        case 5 -> rns = new IceSkill2Mage();
                        case 6 -> rns = new LightSkill2Mage();
                    }
                }
            }
            if(rns == null)return;
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
            if(BaseAttackEnhancer.activated(p)) return;
            PlayerStat playerStat = getPlayerStat(p);
            if(cooldowns.get(p.getUniqueId())[3] != 0){
                if(cooldowns.get(p.getUniqueId())[3] >= System.currentTimeMillis()) return;
                cooldowns.get(p.getUniqueId())[3] = 0L;
            }
            int skillClass = playerStat.getSkillClass();
            switch(skillClass){
                case 1 ->{
                    if(!getItem(p, Material.NETHERITE_SWORD, 1)) return;
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
}
