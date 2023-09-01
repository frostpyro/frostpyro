package org.example.contentplugin.elementalproject.contents.dailyQuest;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.example.contentplugin.elementalproject.contents.dailyQuest.object.CollectItem;
import org.example.contentplugin.elementalproject.contents.dailyQuest.object.KillBoss;
import org.example.contentplugin.elementalproject.contents.dailyQuest.object.KillMob;
import org.example.contentplugin.elementalproject.contents.dailyQuest.object.NPCInteract;

public class DailyQuestGet extends DailyQuest{
    public DailyQuestGet(){

    }
    private String str;
    public DailyQuestGet(String str){

        this.str = str;

    }

    CollectItem collectItem = new CollectItem();
    KillMob killMob = new KillMob();
    NPCInteract npcInteract = new NPCInteract();
    KillBoss killBoss = new KillBoss();


    public void countReset(Player p) {
        super.countReset(p, str);
    }

    public void killMob(Player p, Entity entity){
        killMob.dailyQuestKillMob(p, entity);
    }

    public void killBoss(Player p, Entity entity){
        killBoss.killBoss(p, entity);
    }
}
