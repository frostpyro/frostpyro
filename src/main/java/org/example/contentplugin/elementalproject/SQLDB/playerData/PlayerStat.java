package org.example.contentplugin.elementalproject.SQLDB.playerData;

import java.util.Date;

public class PlayerStat {
    private String playerUUID;

    private int level;
    private int killCount;
    private Date lastLogin;
    private Date lastLogout;
    private double exp;
    private double balance;
    private int mana;
    private int point;
    private int dailyQuest;
    public PlayerStat(String playerUUID, int level, int killCount, double exp, double balance, int mana, int point, int dailyQuest, Date lastLogin, Date lastLogout){
        this.playerUUID = playerUUID;
        this.level = level;
        this.killCount = killCount;
        this.lastLogin = lastLogin;
        this.lastLogout = lastLogout;
        this.exp = exp;
        this.balance = balance;
        this.mana = mana;
        this.dailyQuest = dailyQuest;
        this.point = point;
    }

    public String getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(String playerUUID) {
        this.playerUUID = playerUUID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public int getKillCount() {
        return killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }


    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }


    public Date getLastLogout() {
        return lastLogout;
    }

    public void setLastLogout(Date lastLogout) {
        this.lastLogout = lastLogout;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getDailyQuest() {
        return dailyQuest;
    }

    public void setDailyQuest(int dailyQuest) {
        this.dailyQuest = dailyQuest;
    }
}
