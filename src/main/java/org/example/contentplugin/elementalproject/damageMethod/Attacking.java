package org.example.contentplugin.elementalproject.damageMethod;

import org.bukkit.block.data.type.Fire;
import org.bukkit.event.player.PlayerInteractEvent;
import org.checkerframework.checker.units.qual.A;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.fireSkill.mage.FireBall;
import org.example.contentplugin.elementalproject.damageMethod.classes.Archer;
import org.example.contentplugin.elementalproject.damageMethod.classes.Mage;
import org.example.contentplugin.elementalproject.damageMethod.classes.Martial;
import org.example.contentplugin.elementalproject.damageMethod.classes.Sword;

public class Attacking {
    ClassAttack sword = new Sword();
    ClassAttack mage = new Mage();
    ClassAttack archer = new Archer();
    ClassAttack martial = new Martial();

    public void left(PlayerInteractEvent event){
        sword.lAttack(event);
        mage.lAttack(event);
        archer.lAttack(event);
        martial.lAttack(event);
    }

    public void rLR(PlayerInteractEvent event){
        sword.rLRAttack(event);
        mage.rLRAttack(event);
        archer.rLRAttack(event);
        martial.rLRAttack(event);
    }
    public void rRL(PlayerInteractEvent event){
        sword.rRLAttack(event);
        mage.rRLAttack(event);
        archer.rRLAttack(event);
        martial.rRLAttack(event);

    }
    public void rLL(PlayerInteractEvent event){
        sword.rLLAttack(event);
        mage.rLLAttack(event);
        archer.rLLAttack(event);
        martial.rLLAttack(event);
    }
    public void rRR(PlayerInteractEvent event){
        sword.rRRAttack(event);
        mage.rRRAttack(event);
        archer.rRRAttack(event);
        martial.rRRAttack(event);
    }
}
