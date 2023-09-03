package org.example.contentplugin.elementalproject.control.rightLeftRight;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.control.Clicking;
import org.example.contentplugin.elementalproject.control.Sequence;

public class RLR extends Clicking {
    @Override
    public void clicking(PlayerInteractEvent event, PlayerStat playerStat) {
        Player p = event.getPlayer();
        sequence(event);
        if(!sequent(event, Sequence.RLR)) return;
        manaConsume(p, playerStat, 30);
        p.sendMessage("RLR");
        manaReset(p, playerStat);
    }
}