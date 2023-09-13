package org.example.contentplugin.elementalproject.damageMethod.classes;

import org.bukkit.event.player.PlayerInteractEvent;
import org.example.contentplugin.elementalproject.contents.playerSkill.airSkill.mage.AirEmit;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.earthSkill.mage.EarthRock;
import org.example.contentplugin.elementalproject.contents.playerSkill.electronicSkill.mage.ElectronicChain;
import org.example.contentplugin.elementalproject.contents.playerSkill.fireSkill.mage.FireBall;
import org.example.contentplugin.elementalproject.contents.playerSkill.iceSkill.mage.IceSpike;
import org.example.contentplugin.elementalproject.contents.playerSkill.iceSkill.sword.IceSlash;
import org.example.contentplugin.elementalproject.contents.playerSkill.lightSkill.mage.LightEmit;
import org.example.contentplugin.elementalproject.damageMethod.ClassAttack;

public class Mage implements ClassAttack {
    BaseAttack icB = new IceSpike();
    BaseAttack fiB = new FireBall();
    BaseAttack elB = new ElectronicChain();
    BaseAttack eaB = new EarthRock();
    BaseAttack liB = new LightEmit();
    BaseAttack aiB = new AirEmit();
    @Override
    public void lAttack(PlayerInteractEvent event) {
        icB.attacking(event);
        fiB.attacking(event);
        elB.attacking(event);
        eaB.attacking(event);
        liB.attacking(event);
        aiB.attacking(event);
    }

    @Override
    public void rLLAttack(PlayerInteractEvent event) {

    }

    @Override
    public void rLRAttack(PlayerInteractEvent event) {

    }

    @Override
    public void rRLAttack(PlayerInteractEvent event) {

    }

    @Override
    public void rRRAttack(PlayerInteractEvent event) {

    }
}
