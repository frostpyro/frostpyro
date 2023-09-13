package org.example.contentplugin.elementalproject.damageMethod.classes;

import org.bukkit.event.player.PlayerInteractEvent;
import org.example.contentplugin.elementalproject.contents.playerSkill.airSkill.archer.AirBolt;
import org.example.contentplugin.elementalproject.contents.playerSkill.attackMethod.BaseAttack;
import org.example.contentplugin.elementalproject.contents.playerSkill.earthSkill.archer.EarthShot;
import org.example.contentplugin.elementalproject.contents.playerSkill.electronicSkill.archer.ElectronicBolt;
import org.example.contentplugin.elementalproject.contents.playerSkill.fireSkill.archer.FlameArrow;
import org.example.contentplugin.elementalproject.contents.playerSkill.iceSkill.archer.IceBolt;
import org.example.contentplugin.elementalproject.contents.playerSkill.lightSkill.archer.LightShot;
import org.example.contentplugin.elementalproject.damageMethod.ClassAttack;

public class Archer implements ClassAttack {
    BaseAttack elB = new ElectronicBolt();
    BaseAttack erB = new EarthShot();
    BaseAttack liB = new LightShot();
    BaseAttack fiB = new FlameArrow();
    BaseAttack aiB = new AirBolt();
    BaseAttack icB = new IceBolt();
    @Override
    public void lAttack(PlayerInteractEvent event) {
        elB.attacking(event);
        erB.attacking(event);
        liB.attacking(event);
        fiB.attacking(event);
        aiB.attacking(event);
        icB.attacking(event);
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
