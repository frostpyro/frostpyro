package org.example.contentplugin.elementalproject.contents;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Reaction {
    private Map<UUID, Elements> react = new HashMap<>();

    public void hit(Player p, Entity entity, Elements elements){
        if(!react.isEmpty()) return;
        react.put(entity.getUniqueId(), elements);
    }

    public void reaction(Player p, Entity entity, Elements baseElement){
        if(react.get(entity.getUniqueId())==null) return;

        Elements elements = react.get(entity.getUniqueId());
        if(baseElement == elements) return;
        switch (elements){
            case FIRE -> {
                switch(baseElement){
                    case AIR -> {
                        reaction(p, entity, Elements.AIR);
                        react.remove(entity.getUniqueId());
                    }
                    case ICE -> {
                        react.remove(entity.getUniqueId());
                    }
                    case EARTH -> {

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
                        reaction(p, entity, Elements.AIR);
                        react.remove(entity.getUniqueId());
                    }
                    case FIRE -> {

                    }
                    case EARTH -> {

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
                                reaction(p, entity1, Elements.ICE);
                                react.remove(entity1.getUniqueId());
                            }
                            case EARTH -> {
                                reaction(p, entity1, Elements.EARTH);
                                react.remove(entity1.getUniqueId());
                            }
                            case ELECTRONIC -> {
                                reaction(p, entity1, Elements.ELECTRONIC);
                                react.remove(entity1.getUniqueId());
                            }
                            case FIRE -> {
                                reaction(p, entity1, Elements.FIRE);
                                react.remove(entity1.getUniqueId());
                            }
                            case LIGHT -> {
                                reaction(p, entity1, Elements.LIGHT);
                                react.remove(entity1.getUniqueId());
                            }
                        }
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
                        reaction(p, entity, Elements.AIR);
                        react.remove(entity.getUniqueId());
                    }
                    case FIRE -> {

                    }
                    case ICE -> {

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
                        reaction(p, entity, Elements.AIR);
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
                        reaction(p, entity, Elements.AIR);
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
