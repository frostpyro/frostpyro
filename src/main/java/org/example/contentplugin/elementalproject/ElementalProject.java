package org.example.contentplugin.elementalproject;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.contentplugin.elementalproject.SQLDB.DataBase;
import org.example.contentplugin.elementalproject.contents.dailyQuest.DailyQuest;
import org.example.contentplugin.elementalproject.contents.dailyQuest.DailyQuestGet;
import org.example.contentplugin.elementalproject.contents.leveling.LevelPoint;

import java.io.File;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

public class ElementalProject extends JavaPlugin {
    DailyQuestGet dailyQuest = new DailyQuestGet("midnight");
    LevelPoint levelPoint;

    private static ElementalProject plugin;
    ConsoleCommandSender console = Bukkit.getConsoleSender();
    private DataBase dataBase;
    @Override
    public void onEnable() {
        this.dataBase = new DataBase();



        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "main DB site is player_stat");
        levelPoint = new LevelPoint(1);


        plugin = this;
        CustomRecipe custom = new CustomRecipe(this);
        dataBase.configFunc();

        try{
            this.dataBase.initialize();
        }
        catch (SQLException e){
            e.printStackTrace();
            console.sendMessage(ChatColor.RED + "Failed to initialize data");
        }

        new Listeners(this);
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
}
