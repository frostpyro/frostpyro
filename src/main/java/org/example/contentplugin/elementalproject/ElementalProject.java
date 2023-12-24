package org.example.contentplugin.elementalproject;

import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.contentplugin.elementalproject.system.DataBase;
import org.example.contentplugin.elementalproject.contents.dailyQuest.DailyQuestGet;
import org.example.contentplugin.elementalproject.contents.leveling.LevelPoint;
import org.example.contentplugin.elementalproject.listners.ClickEvent;
import org.example.contentplugin.elementalproject.listners.DBSet;
import org.example.contentplugin.elementalproject.listners.EntityInteraction;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
/**
 * This code is main class
 * @author frostpyro
 */
public class ElementalProject extends JavaPlugin {
    DailyQuestGet dailyQuest = new DailyQuestGet("midnight");
    LevelPoint levelPoint;

    private static ElementalProject plugin;
    ConsoleCommandSender console = Bukkit.getConsoleSender();
    private DataBase dataBase;


    /**
     *the main function.
     *
     */
    @Override
    public void onEnable() {
        plugin = this;
        configFunc();
        this.dataBase = new DataBase();
        dataBase.configFunc();
        saveConfig();


        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "main DB site is player_stat");
        levelPoint = new LevelPoint();



        CustomRecipe custom = new CustomRecipe(this);


        reloadConfig();

        try{
            this.dataBase.initialize();
        }
        catch (SQLException e){
            e.printStackTrace();
            console.sendMessage(ChatColor.RED + "Failed to initialize data");
        }
        new DBSet(this);
        new EntityInteraction(this);
        new ClickEvent(this);
        console.sendMessage(ChatColor.GREEN + "PLUGIN ENABLED");
        getServer().getScheduler().scheduleSyncRepeatingTask(this, () ->{
            for(Player p : getServer().getOnlinePlayers()){
                dailyQuest.countReset(p);
            }
        }, 0, 2);
        custom.summary();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }


    /**
     * function that points main plugin.
     * this does not disappear from memory.
     * @return ElementalProject
     */
    public static ElementalProject getPlugin() {
        return plugin;
    }


    public static NamespacedKey dailyList(){
        return new NamespacedKey(getPlugin(), "dailyQuest");
    }
    public static NamespacedKey stat(){
        return new NamespacedKey(plugin, "stat");
    }
    public static NamespacedKey level(){
        return new NamespacedKey(plugin, "level");
    }

    public static NamespacedKey skill(){
        return new NamespacedKey(plugin, "skill");
    }


    private FileConfiguration sqlConfig;

    private void configFunc(){


        File sqlFile = new File(getDataFolder(), "dataBase.yml");

        if(!sqlFile.exists()){
            sqlFile.getParentFile().mkdirs();
            saveResource("dataBase.yml", false);
        }


        sqlConfig = new YamlConfiguration();

        try{

            sqlConfig.load(sqlFile);
        }
        catch (IOException | InvalidConfigurationException e){
            e.printStackTrace();
            return;
        }
        Bukkit.getConsoleSender().sendMessage("config enabled!");
    }
}
