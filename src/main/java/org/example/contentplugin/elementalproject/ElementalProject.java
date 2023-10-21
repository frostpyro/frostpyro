package org.example.contentplugin.elementalproject;

import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.contentplugin.elementalproject.SQLDB.DataBase;
import org.example.contentplugin.elementalproject.contents.dailyQuest.DailyQuestGet;
import org.example.contentplugin.elementalproject.contents.leveling.LevelPoint;
import org.example.contentplugin.elementalproject.interaction.interacting.Summoning;
import org.example.contentplugin.elementalproject.listners.DBSet;
import org.example.contentplugin.elementalproject.listners.EntityInteraction;

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
    Summoning summoning = new Summoning();



    @Override
    public void onEnable() {

        this.dataBase = new DataBase();
        saveConfig();


        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "main DB site is player_stat");
        levelPoint = new LevelPoint();


        plugin = this;
        CustomRecipe custom = new CustomRecipe(this);
        dataBase.configFunc();

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
        console.sendMessage(ChatColor.GREEN + "PLUGIN ENABLED");
        getServer().getScheduler().scheduleSyncRepeatingTask(this, () ->{
            for(Player p : getServer().getOnlinePlayers()){
                dailyQuest.countReset(p);
            }
        }, 0, 2);
        custom.summary();
        for(Player p : Bukkit.getOnlinePlayers()){
            for(Entity entity : p.getNearbyEntities(10, 10, 10)){
                if(entity instanceof Interaction){
                    entity.remove();
                }
            }
            ItemStack item = p.getInventory().getItemInMainHand();
            if(item.getType()== Material.NETHERITE_SWORD){
                summoning.spawnOnReload(p);
            }
        }
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
