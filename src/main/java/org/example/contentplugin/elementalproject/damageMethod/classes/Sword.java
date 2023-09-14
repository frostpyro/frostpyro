package org.example.contentplugin.elementalproject.damageMethod.classes;

import org.bukkit.event.player.PlayerInteractEvent;
import org.example.contentplugin.elementalproject.contents.playerSkill.airSkill.sword.AirWhip;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.earthSkill.sword.EarthStrike;
import org.example.contentplugin.elementalproject.contents.playerSkill.electronicSkill.sword.ElectronicCharge;
import org.example.contentplugin.elementalproject.contents.playerSkill.fireSkill.sword.FIreSlash;
import org.example.contentplugin.elementalproject.contents.playerSkill.iceSkill.sword.IceSlash;
import org.example.contentplugin.elementalproject.contents.playerSkill.lightSkill.sword.LightSlash;
import org.example.contentplugin.elementalproject.damageMethod.ClassAttack;

public class Sword implements ClassAttack {
    BaseAttack icB = new IceSlash();
    BaseAttack fiB = new FIreSlash();
    BaseAttack elB = new ElectronicCharge();
    BaseAttack eaB = new EarthStrike();
    BaseAttack liB = new LightSlash();
    BaseAttack aiB = new AirWhip();
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
