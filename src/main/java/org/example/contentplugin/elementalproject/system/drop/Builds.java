package org.example.contentplugin.elementalproject.system.drop;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.example.contentplugin.elementalproject.ElementalProject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Builds {
    private ItemStack itemStack;
    private int dropRate;
    private int model;
    private int id;

    private final List<String> common = new ArrayList<>(Arrays.asList(null, null, null));
    private final List<String> uncommon = new ArrayList<>(Arrays.asList(null, null, null));
    private final List<String> rare = new ArrayList<>(Arrays.asList(null, null, null));
    private final List<String> legendary = new ArrayList<>(Arrays.asList(null, null, null));

    private FileConfiguration configuration;

    public Builds(ItemStack itemStack, int dropRate, int model, int id, FileConfiguration configuration){
        //item stack = 구리
        this.itemStack = itemStack;
        this.dropRate = dropRate;
        this.model = model;
        this.id = id;
        this.configuration = configuration;
        initList();
    }

    public void dropItem(Entity entity){
        Random random = new Random();
        int rate = random.nextInt(100);
        if(rate > dropRate) return;
        spawnItem(entity);
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

    private void spawnItem(Entity entity){
        ItemStack item = itemStack;
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return;
        PersistentDataContainer id = meta.getPersistentDataContainer();
        PersistentDataContainer name = meta.getPersistentDataContainer();
        id.set(ElementalProject.id(), PersistentDataType.INTEGER, this.id);
        id.set(ElementalProject.rank(), PersistentDataType.INTEGER, 0);
        meta.setCustomModelData(model);
        item.setItemMeta(meta);
        Item entityItem = (Item)entity.getWorld().spawnEntity(entity.getLocation(), EntityType.DROPPED_ITEM);
        entityItem.setItemStack(item);
    }
}
