package org.example.contentplugin.elementalproject.system.drop;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.example.contentplugin.elementalproject.ElementalProject;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Builds {
    private ItemStack itemStack;
    private int dropRate;
    private int id;
    private String name;
    private ChatColor color;
    private final List<String> common = new ArrayList<>(Arrays.asList(null, null, null));
    private final List<String> uncommon = new ArrayList<>(Arrays.asList(null, null, null));
    private final List<String> rare = new ArrayList<>(Arrays.asList(null, null, null));
    private final List<String> legendary = new ArrayList<>(Arrays.asList(null, null, null));

    private FileConfiguration configuration;

    public Builds(ItemStack itemStack, int dropRate, FileConfiguration configuration){
        //item stack = 구리
        this.itemStack = itemStack;
        this.dropRate = dropRate;
        this.configuration = configuration;
        initList();
    }

    public void dropItem(Entity entity){
        Random random = new Random();
        int rate = random.nextInt(100);
        if(rate >= dropRate) return;
        decide();
        spawnItem(entity);
    }

    private int random(int root){
        Random random = new Random();
        return  random.nextInt(root);
    }

    private void initList(){
        ConfigurationSection section1, section2, section3, section4;
        section1 = configuration.getConfigurationSection("common");
        section2 = configuration.getConfigurationSection("uncommon");
        section3 = configuration.getConfigurationSection("rare");
        section4 = configuration.getConfigurationSection("legendary");
        if(section1 == null||section2 == null||section3 == null||section4 == null){
            return;
        }
        int i = 0;
        for(String key : section1.getKeys(false)){
            common.set(i, key);
            i++;
        }
        i = 0;
        for(String key : section2.getKeys(false)){
            uncommon.set(i, key);
            i++;
        }
        for(String key : section3.getKeys(false)){
            rare.set(i, key);
            i++;
        }
        i = 0;
        for(String key : section4.getKeys(false)){
            legendary.set(i, key);
            i++;
        }
    }

    private void decide(){
        File file = new File(ElementalProject.getPlugin().getDataFolder(), "dropRate");
        FileConfiguration config = new YamlConfiguration();
        try{
            config.load(file);
        }
        catch (IOException | InvalidConfigurationException e){
            e.printStackTrace();
            return;
        }
        ConfigurationSection section = config.getConfigurationSection("builds");
        if(section == null) return;
        int rate;
        rate = random(100);


        if(rate < section.getInt("legendary")){
            ConfigurationSection configSec = configuration.getConfigurationSection("legendary");
            if(configSec == null) return;
            ConfigurationSection sec2 = configSec.getConfigurationSection(legendary.get(random(3)));
            if(sec2 == null) return;
            id = sec2.getInt("id");
            name = sec2.getString("name");
            color = ChatColor.RED;
        }
        else if(rate < section.getInt("legendary") + section.getInt("rare")){
            ConfigurationSection configSec = configuration.getConfigurationSection("rare");
            if(configSec == null) return;
            ConfigurationSection sec2 = configSec.getConfigurationSection(rare.get(random(3)));
            if(sec2 == null) return;
            id = sec2.getInt("id");
            name = sec2.getString("name");
            color = ChatColor.DARK_AQUA;
        }
        else if(rate < section.getInt("legendary") + section.getInt("rare") + section.getInt("uncommon")){
            ConfigurationSection configSec = configuration.getConfigurationSection("uncommon");
            if(configSec == null) return;
            ConfigurationSection sec2 = configSec.getConfigurationSection(uncommon.get(random(3)));
            if(sec2 == null) return;
            id = sec2.getInt("id");
            name = sec2.getString("name");
            color = ChatColor.GOLD;
        }
        else{
            ConfigurationSection configSec = configuration.getConfigurationSection("common");
            if(configSec == null) return;
            ConfigurationSection sec2 = configSec.getConfigurationSection(common.get(random(3)));
            if(sec2 == null) return;
            id = sec2.getInt("id");
            name = sec2.getString("name");
            color = ChatColor.WHITE;
        }
    }

    private void spawnItem(Entity entity){
        ItemStack item = itemStack;
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return;
        PersistentDataContainer data = meta.getPersistentDataContainer();
        data.set(ElementalProject.id(), PersistentDataType.INTEGER, id);
        data.set(ElementalProject.rank(), PersistentDataType.INTEGER, 0);
        meta.setCustomModelData(id);
        meta.setDisplayName(color+name);
        item.setItemMeta(meta);
        Item entityItem = (Item)entity.getWorld().spawnEntity(entity.getLocation(), EntityType.DROPPED_ITEM);
        entityItem.setItemStack(item);
    }
}
