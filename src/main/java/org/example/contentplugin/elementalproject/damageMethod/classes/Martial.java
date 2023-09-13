package org.example.contentplugin.elementalproject.damageMethod.classes;

import org.bukkit.event.player.PlayerInteractEvent;
import org.example.contentplugin.elementalproject.contents.playerSkill.airSkill.mage.AirEmit;
import org.example.contentplugin.elementalproject.contents.playerSkill.airSkill.martial.AirPierce;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.earthSkill.mage.EarthRock;
import org.example.contentplugin.elementalproject.contents.playerSkill.earthSkill.martial.EarthHook;
import org.example.contentplugin.elementalproject.contents.playerSkill.electronicSkill.mage.ElectronicChain;
import org.example.contentplugin.elementalproject.contents.playerSkill.electronicSkill.martial.ElectronicJab;
import org.example.contentplugin.elementalproject.contents.playerSkill.fireSkill.mage.FireBall;
import org.example.contentplugin.elementalproject.contents.playerSkill.fireSkill.martial.FlameWave;
import org.example.contentplugin.elementalproject.contents.playerSkill.iceSkill.mage.IceSpike;
import org.example.contentplugin.elementalproject.contents.playerSkill.iceSkill.martial.FrozenPunch;
import org.example.contentplugin.elementalproject.contents.playerSkill.lightSkill.mage.LightEmit;
import org.example.contentplugin.elementalproject.contents.playerSkill.lightSkill.martial.LightPunch;
import org.example.contentplugin.elementalproject.damageMethod.ClassAttack;


public class Martial implements ClassAttack {
    BaseAttack icB = new FrozenPunch();
    BaseAttack fiB = new FlameWave();
    BaseAttack elB = new ElectronicJab();
    BaseAttack eaB = new EarthHook();
    BaseAttack liB = new LightPunch();
    BaseAttack aiB = new AirPierce();
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
