package org.example.contentplugin.elementalproject.contents.leveling;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.SQLDB.DataBase;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;


import java.sql.SQLException;
import java.util.*;

public class LevelPoint {
    DataBase dataBase = new DataBase();
    Random random = new Random();
    private double rad;

    public LevelPoint(double rad){
        this.rad = rad;
    }
    private PlayerStat getPlayerStat(Player p) throws SQLException {
        PlayerStat playerStat = dataBase.findUUID(p.getUniqueId().toString());
        if(playerStat == null){
            playerStat = new PlayerStat(p.getUniqueId().toString(), 0, 0, 0, 0, 0, 0, 0, new Date(), new Date());
            dataBase.createData(playerStat);
        }
        return playerStat;
    }
    private int randomLevel(int amplify){
        int randValue = random.nextInt(4);
        return 1 + randValue + amplify;
    }

    private void levelModBase(PlayerStat playerStat, int level, int point, double exp, double expMod){
        if(level >= 100) return;
        try{
            if(exp <= expMod) return;
            playerStat.setLevel(level + 1);
            playerStat.setPoint(point + 1);
            playerStat.setExp(exp - expMod);
            dataBase.updateData(playerStat);
            Player p = Bukkit.getPlayer(UUID.fromString(playerStat.getPlayerUUID()));
            p.sendMessage(ChatColor.YELLOW + "LEVEL UP!!! : " + ChatColor.GREEN + (playerStat.getLevel()));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void levelSetting(Entity entity){

        Location overWorld = Objects.requireNonNull(Bukkit.getServer().getWorld("world")).getSpawnLocation();

        Location nether = new Location(Bukkit.getServer().getWorld("world_nether"),0 , 70, 0);

        Location endWorld = new Location(Bukkit.getServer().getWorld("world_the_end"), 0, 70, 0);

        switch (entity.getWorld().getName()){
            case "world" ->{
                if(!(entity instanceof LivingEntity)) return;
                if((entity instanceof Player)) return;

                PersistentDataContainer data = entity.getPersistentDataContainer();
                if(data.has(ElementalProject.level(), PersistentDataType.INTEGER)) return;
                double distance = entity.getLocation().distance(overWorld);
                //mob leveling by distance
                if(distance <= rad) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(0));

                else if(distance <= rad * 1.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(5));

                else if(distance <= rad * 2) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(10));

                else if(distance <= rad * 2.5)  data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(15));

                else if(distance <= rad * 3) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(20));

                else if(distance <= rad * 3.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(25));

                else if(distance <= rad * 4.0) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(30));

                else if(distance <= rad * 4.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(35));

                else if(distance <= rad * 5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(40));

                else if(distance <= rad * 5.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(45));

                else if(distance <= rad * 6) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(50));

                else if(distance <= rad * 6.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(55));

                else if(distance <= rad * 7) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(60));

                else if(distance <= rad * 7.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(65));

                else if(distance <= rad * 8) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(70));

                else if(distance <= rad * 8.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(75));

                else if(distance <= rad * 9) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(80));

                else if(distance <= rad * 9.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(85));

                else if(distance <= rad * 10) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(90));

                else if(distance <= rad * 10.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(95));

                else data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(100));
            }

            case "world_nether" ->{
                if(!(entity instanceof LivingEntity)) return;
                if((entity instanceof Player)) return;
                PersistentDataContainer data = entity.getPersistentDataContainer();
                if(data.has(ElementalProject.level(), PersistentDataType.INTEGER)) return;
                double distance = entity.getLocation().distance(nether);
                //mob leveling by distance
                if(distance <= rad) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(10));

                else if(distance <= rad * 1.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(20));

                else if(distance <= rad * 2) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(25));

                else if(distance <= rad * 2.5)  data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(30));

                else if(distance <= rad * 3) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(35));

                else if(distance <= rad * 3.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(40));

                else if(distance <= rad * 4.0) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(45));

                else if(distance <= rad * 4.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(50));

                else if(distance <= rad * 5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(55));

                else if(distance <= rad * 5.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(60));

                else if(distance <= rad * 6) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(65));

                else if(distance <= rad * 6.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(70));

                else if(distance <= rad * 7) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(75));

                else if(distance <= rad * 7.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(80));

                else if(distance <= rad * 8) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(85));

                else if(distance <= rad * 8.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(90));

                else if(distance <= rad * 9) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(95));

                else if(distance <= rad * 9.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(100));

                else if(distance <= rad * 10) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(105));

                else if(distance <= rad * 10.5) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(110));

                else data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(115));
            }
            case "world_the_end" ->{
                if(!(entity instanceof LivingEntity)) return;
                if((entity instanceof Player)) return;
                PersistentDataContainer data = entity.getPersistentDataContainer();
                if(data.has(ElementalProject.level(), PersistentDataType.INTEGER)) return;
                double distance = entity.getLocation().distance(endWorld);
                //mob leveling by distance
                if(distance <= rad) data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(100));

                else data.set(ElementalProject.level(), PersistentDataType.INTEGER, randomLevel(150));
            }
        }
    }
    public void expModifyByMob(Player p, Entity entity){
        try{
            PlayerStat playerStat = getPlayerStat(p);
            if(!(entity instanceof LivingEntity)) return;


            PersistentDataContainer data = entity.getPersistentDataContainer();

            int amplify;
            //level amplifying method by getting level
            if(!data.has(new NamespacedKey(ElementalProject.getPlugin(), "level"), PersistentDataType.INTEGER)){
                amplify = 0;
            }
            else{
                amplify = data.get(new NamespacedKey(ElementalProject.getPlugin(), "level"), PersistentDataType.INTEGER);
            }
            EntityType entityType = entity.getType();
            switch(entityType){
                case BAT, COW, MUSHROOM_COW, SHEEP, PIG, CHICKEN, LLAMA, FOX-> {
                    if(playerStat.getLevel() == 100) return;
                    playerStat.setExp(playerStat.getExp() + 2 + amplify/10.0);
                    dataBase.updateData(playerStat);
                }
                case SLIME, MAGMA_CUBE ->{
                    if(playerStat.getLevel() >= 100) return;
                    playerStat.setExp(playerStat.getExp() + amplify/5.0);
                    dataBase.updateData(playerStat);
                }
                case ZOMBIE, SKELETON, SPIDER, CREEPER, ZOMBIE_VILLAGER-> {
                    if(playerStat.getLevel() >= 100) return;
                    playerStat.setExp(playerStat.getExp() + (8 + amplify * 1.5));
                    dataBase.updateData(playerStat);
                }
                case PIGLIN, HOGLIN, WITCH -> {
                    if(playerStat.getLevel() >= 100) return;
                    playerStat.setExp(playerStat.getExp() + (12 + amplify * 1.5));
                    dataBase.updateData(playerStat);
                }
                case ZOMBIFIED_PIGLIN, ENDERMAN, WITHER_SKELETON, BLAZE  -> {
                    if(playerStat.getLevel() >= 100) return;
                    playerStat.setExp(playerStat.getExp() + (20 + amplify * 1.5));
                    dataBase.updateData(playerStat);
                }
                case ZOGLIN, PIGLIN_BRUTE-> {
                    if(playerStat.getLevel() >= 100) return;
                    playerStat.setExp(playerStat.getExp() + (34 + amplify * 1.5));
                    dataBase.updateData(playerStat);
                }

                case PILLAGER, VINDICATOR  -> {
                    if(playerStat.getLevel() >= 100) return;
                    playerStat.setExp(playerStat.getExp() + (40 + amplify * 1.5));
                    dataBase.updateData(playerStat);
                }

                case EVOKER, ELDER_GUARDIAN, RAVAGER -> {
                    if(playerStat.getLevel() >= 100) return;
                    playerStat.setExp(playerStat.getExp() + (50 + amplify * 1.5));
                    dataBase.updateData(playerStat);
                }

                case ENDER_DRAGON, WITHER -> {
                    if(playerStat.getLevel() >= 100) return;
                    playerStat.setExp(playerStat.getExp() + 10000 + amplify * 1.5);
                    dataBase.updateData(playerStat);
                }

                case WARDEN -> {
                    if(playerStat.getPoint() <= 100) return;
                    playerStat.setPoint(playerStat.getPoint() + 2);
                    dataBase.updateData(playerStat);
                }

                case PLAYER ->{
                    if(playerStat.getLevel() <= 100) return;
                    if(playerStat.getLevel() >= 200) return;
                    playerStat.setExp(playerStat.getExp() + 100 + ((Player)entity).getLevel());
                    dataBase.updateData(playerStat);
                }
                default -> playerStat.setExp(playerStat.getExp());
            }

        }
        catch (SQLException e){
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "error occur while loading player level");
        }
    }
    public void expByQuest(){

    }
    public void levelModifier(Player p){
        try{
            PlayerStat playerStat = getPlayerStat(p);
            int level = playerStat.getLevel();
            double exp = playerStat.getExp();
            int point = playerStat.getPoint();

            switch (level){
                case 0, 1, 2 -> {
                    levelModBase(playerStat, level, point, exp, 50);
                }

                case 3, 4, 5 -> levelModBase(playerStat, level, point, exp, 150);

                case 6, 7, 8 -> levelModBase(playerStat, level, point, exp, 250);

                case 9, 10, 11 -> levelModBase(playerStat, level, point, exp, 350);

                case 12, 13, 14 -> levelModBase(playerStat, level, point, exp, 450);

                case 15, 16, 17 -> levelModBase(playerStat, level, point, exp, 550);

                case 18, 19, 20 -> levelModBase(playerStat, level, point, exp, 650);

                case 21, 22, 23 -> levelModBase(playerStat, level, point, exp, 750);

                case 24, 25, 26 -> levelModBase(playerStat, level, point, exp, 850);

                case 27, 28, 29 -> levelModBase(playerStat, level, point, exp, 950);

                case 30, 31, 32 -> levelModBase(playerStat, level, point, exp, 1200);

                case 33, 34, 35 -> levelModBase(playerStat, level, point, exp, 1500);

                case 36, 37, 38 -> levelModBase(playerStat, level, point, exp, 1800);

                case 39, 40, 41 -> levelModBase(playerStat, level, point, exp, 2100);

                case 42, 43, 44 -> levelModBase(playerStat, level, point ,exp, 2400);

                case 45, 46, 47 -> levelModBase(playerStat, level, point, exp, 2700);

                case 48, 49, 50 -> levelModBase(playerStat, level, point, exp, 3000);

                case 51, 52, 53 -> levelModBase(playerStat, level, point, exp, 3600);

                case 54, 55, 56 -> levelModBase(playerStat, level, point, exp, 4200);

                case 57, 58, 59 -> levelModBase(playerStat, level, point, exp, 4800);

                case 60, 61, 62 -> levelModBase(playerStat, level, point, exp, 5400);

                case 63, 64, 65 -> levelModBase(playerStat, level, point, exp, 6000);

                case 66, 67, 68 -> levelModBase(playerStat, level, point, exp, 6600);

                case 69, 70, 71 -> levelModBase(playerStat, level, point, exp, 7600);

                case 72, 73, 74 -> levelModBase(playerStat, level, point, exp, 8600);

                case 75, 76, 77 -> levelModBase(playerStat, level, point, exp, 9600);

                case 78, 79, 80 -> levelModBase(playerStat, level, point, exp, 11000);

                case 81, 82, 83 -> levelModBase(playerStat, level, point, exp, 15000);

                case 84, 85, 86 -> levelModBase(playerStat, level, point, exp, 19000);

                case 87, 88, 89 -> levelModBase(playerStat, level, point, exp, 23000);

                case 90, 91, 92 -> levelModBase(playerStat, level, point, exp, 30000);

                case 93, 94, 95 -> levelModBase(playerStat, level, point, exp, 36000);

                case 96, 97, 98 -> levelModBase(playerStat, level, point, exp, 46000);

                case 99 -> levelModBase(playerStat, level, point, exp, 60000);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
            p.kickPlayer("error while updating data!!" + "error : levelUp");
        }
    }
}
