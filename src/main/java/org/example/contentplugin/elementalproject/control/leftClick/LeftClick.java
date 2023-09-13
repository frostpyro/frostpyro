package org.example.contentplugin.elementalproject.control.leftClick;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.example.contentplugin.elementalproject.SQLDB.playerData.PlayerStat;
import org.example.contentplugin.elementalproject.control.Clicking;
import org.example.contentplugin.elementalproject.control.Sequence;
import org.example.contentplugin.elementalproject.damageMethod.Attacking;

public class LeftClick extends Clicking {
    Attacking attack = new Attacking();
    @Override
    public void clicking(PlayerInteractEvent event, PlayerStat playerStat) {
        Player p = event.getPlayer();
        sequence(event);
        if(!sequent(event, Sequence.L)) return;
        attack.left(event);
        p.sendMessage("Left");
    }
}
