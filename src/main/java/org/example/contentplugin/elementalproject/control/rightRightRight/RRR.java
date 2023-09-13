package org.example.contentplugin.elementalproject.control.rightRightRight;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.control.Clicking;
import org.example.contentplugin.elementalproject.control.Sequence;
import org.example.contentplugin.elementalproject.damageMethod.Attacking;

public class RRR extends Clicking {
    Attacking attack = new Attacking();
    @Override
    public void clicking(PlayerInteractEvent event, PlayerStat playerStat) {
        Player p = event.getPlayer();
        sequence(event);
        if(!sequent(event, Sequence.RRR)) return;
        manaConsume(p, playerStat, 70);
        attack.rRR(event);
        p.sendMessage("RRR");
        manaReset(p, playerStat);
    }
}