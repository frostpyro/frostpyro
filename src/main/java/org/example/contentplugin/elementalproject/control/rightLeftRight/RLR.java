package org.example.contentplugin.elementalproject.control.rightLeftRight;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.checkerframework.checker.units.qual.A;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.control.Clicking;
import org.example.contentplugin.elementalproject.control.Sequence;
import org.example.contentplugin.elementalproject.damageMethod.Attacking;

public class RLR extends Clicking {
    Attacking attack = new Attacking();
    @Override
    public void clicking(PlayerInteractEvent event, PlayerStat playerStat) {
        Player p = event.getPlayer();
        sequence(event);
        if(!sequent(event, Sequence.RLR)) return;
        manaConsume(p, playerStat, 30);
        attack.rLR(event);
        p.sendMessage("RLR");
        manaReset(p, playerStat);
    }
}