package org.example.contentplugin.elementalproject.contents;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.contentplugin.elementalproject.ElementalProject;
import org.example.contentplugin.elementalproject.contents.playerSkill.Damaging;


import java.util.*;

public class Reaction {
    private static Map<UUID, Elements> react = new HashMap<>();


    public void hit(Entity entity, Elements elements){
        Damaging damaging = new Damaging();
        if(!(entity instanceof LivingEntity)) return;
        if(!react.isEmpty()) return;
        react.put(entity.getUniqueId(), elements);
        switch(elements){
            case FIRE ->{
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if(!react.isEmpty()){
                            entity.setFireTicks(1);
                        }
                        else{
                            cancel();
                        }
                    }
                }.runTaskTimer(ElementalProject.getPlugin(), 0, 1);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if(react.isEmpty()){
                            cancel();
                        }
                        Bukkit.getServer().getScheduler().runTask(ElementalProject.getPlugin(), () -> react.remove(entity.getUniqueId()));
                    }
                }.runTaskLater(ElementalProject.getPlugin(), 100L);
            }
            case AIR -> {
                new BukkitRunnable(){
                    @Override
                    public void run() {

                    }
                }.runTaskTimer(ElementalProject.getPlugin(), 0L, 20L);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if(react.isEmpty()){
                            cancel();
                        }
                        Bukkit.getServer().getScheduler().runTask(ElementalProject.getPlugin(), () -> react.remove(entity.getUniqueId()));
                    }
                }.runTaskLater(ElementalProject.getPlugin(), 100L);
            }
            case ICE -> {

            }
            case EARTH -> {

            }
            case LIGHT -> {

            }
            case ELECTRONIC -> {

            }
        }

    }

    public void reaction(Player p, Entity entity, Elements baseElement, Set<UUID> entitySet){
        Damaging damaging = new Damaging();
        if(!(entity instanceof LivingEntity)) return;
        if(react.get(entity.getUniqueId())==null) return;

        Elements elements = react.get(entity.getUniqueId());
        if(baseElement == elements) return;
        switch (elements){
            case FIRE -> {
                switch(baseElement){
                    case AIR -> {
                        reaction(p, entity, Elements.AIR, entitySet);
                        react.remove(entity.getUniqueId());
                    }
                    case ICE -> {
                        damaging.reactDamage1(p, (LivingEntity) entity, entitySet, ((LivingEntity) entity).getLastDamage()*2);
                        entity.getWorld().spawnParticle(Particle.CLOUD, entity.getLocation(), 8, 2);
                        entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 2, 1);
                        react.remove(entity.getUniqueId());
                    }
                    case EARTH -> {
                        damaging.reactSlow((LivingEntity)entity, 3, 3);
                        entity.getWorld().spawnParticle(Particle.LAVA, entity.getLocation(), 8, 2);
                        entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 2, 1);
                        react.remove(entity.getUniqueId());
                    }
                    case LIGHT -> {

                    }
                    case ELECTRONIC -> {

                    }
                }
            }
            case ICE -> {
                switch(baseElement){
                    case AIR -> {
                        reaction(p, entity, Elements.AIR, entitySet);
                        react.remove(entity.getUniqueId());
                    }
                    case FIRE -> {
                        damaging.reactDamage1(p, (LivingEntity) entity, entitySet, ((LivingEntity) entity).getLastDamage()*2);
                        entity.getWorld().spawnParticle(Particle.CLOUD, entity.getLocation(), 8, 2);
                        entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 2, 1);
                        react.remove(entity.getUniqueId());
                    }
                    case EARTH -> {
                        damaging.reactDamage2(p, (LivingEntity) entity, entitySet, ((LivingEntity)entity).getLastDamage()*0.5, 3);
                        entity.getWorld().spawnParticle(Particle.DRIP_LAVA, entity.getLocation(), 8, 2);
                        entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 2, -1);
                        react.remove(entity.getUniqueId());
                    }
                    case LIGHT -> {

                    }
                    case ELECTRONIC -> {

                    }
                }
            }
            case AIR -> {
                List<Entity> entities = entity.getNearbyEntities(5,5,5);
                for(Entity entity1 : entities){
                    if(!(entity1 instanceof LivingEntity)) continue;
                    if(entity1.equals(p))continue;
                    if(react.containsKey(entity1.getUniqueId())){
                        Elements newElement = react.get(entity1.getUniqueId());

                        switch(newElement){
                            case ICE->{
                                reaction(p, entity1, Elements.ICE, entitySet);
                                react.remove(entity1.getUniqueId());
                            }
                            case EARTH -> {
                                reaction(p, entity, Elements.EARTH, entitySet);
                                react.remove(entity1.getUniqueId());
                            }
                            case ELECTRONIC -> {
                                reaction(p, entity1, Elements.ELECTRONIC, entitySet);
                                react.remove(entity1.getUniqueId());
                            }
                            case FIRE -> {
                                reaction(p, entity1, Elements.FIRE, entitySet);
                                react.remove(entity1.getUniqueId());
                            }
                            case LIGHT -> {
                                reaction(p, entity1, Elements.LIGHT, entitySet);
                                react.remove(entity1.getUniqueId());
                            }
                        }
                        react.remove(entity.getUniqueId());
                    }
                    else{
                        react.put(entity1.getUniqueId(), elements);
                        ((LivingEntity)entity1).damage(5, p);
                    }
                }
            }
            case EARTH -> {
                switch(baseElement){
                    case AIR -> {
                        reaction(p, entity, Elements.AIR,entitySet);
                        react.remove(entity.getUniqueId());
                    }
                    case FIRE -> {
                        damaging.reactSlow((LivingEntity)entity, 3, 3);
                        entity.getWorld().spawnParticle(Particle.LAVA, entity.getLocation(), 8, 2);
                        entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 2, 1);
                        react.remove(entity.getUniqueId());
                    }
                    case ICE -> {
                        damaging.reactDamage2(p, (LivingEntity) entity, entitySet, ((LivingEntity)entity).getLastDamage()*0.5, 3);
                        react.remove(entity.getUniqueId());
                    }
                    case LIGHT -> {

                    }
                    case ELECTRONIC -> {

                    }
                }
            }
            case LIGHT -> {
                switch(baseElement){
                    case AIR -> {
                        reaction(p, entity, Elements.AIR, entitySet);
                        react.remove(entity.getUniqueId());
                    }
                    case FIRE -> {

                    }
                    case EARTH -> {

                    }
                    case ICE -> {

                    }
                    case ELECTRONIC -> {

                    }
                }
            }
            case ELECTRONIC -> {
                switch(baseElement){
                    case AIR -> {
                        reaction(p, entity, Elements.AIR, entitySet);
                        react.remove(entity.getUniqueId());
                    }
                    case FIRE -> {

                    }
                    case EARTH -> {

                    }
                    case LIGHT -> {

                    }
                    case ICE -> {

                    }
                }
            }
        }
    }
}
