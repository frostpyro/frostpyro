package org.example.contentplugin.elementalproject.interaction.interacting;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.example.contentplugin.elementalproject.ElementalProject;

public class Summoning {


    public void playerItemChange(PlayerItemHeldEvent event){
        Player p = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = p.getInventory().getItem(slot);
        if(item != null && item.getType()==Material.NETHERITE_SWORD)
            spawnInteraction(p);
        else
            removeInteraction(p);
    }

    public void joinInteraction(PlayerJoinEvent event){
        Player player = event.getPlayer();
        for(Entity entity : player.getNearbyEntities(5,5,5)){
            if(entity instanceof Interaction){
                entity.remove();
            }
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        if(item != null && item.getType()==Material.NETHERITE_SWORD)
            spawnInteraction(player);
    }
    public void spawnOnReload(Player p){
        spawnInteraction(p);
    }
    public void removeOnDead(Player p){
        removeInteraction(p);
    }
    public void removeOnDimensionChange(Player p){
        removeInteraction(p);
    }
    private void spawnInteraction(Player player){
        Interaction interaction = (Interaction) player.getWorld().spawnEntity(player.getLocation(), EntityType.INTERACTION);
        interaction.setInteractionHeight(2F);
        interaction.setInteractionWidth(1F);
        interaction.setCustomName("skillCheck");
        new BukkitRunnable(){
            @Override
            public void run() {
                if(!player.isOnline()){
                    interaction.remove();
                }
                else{
                    Location location = player.getLocation();
                    location.setY(location.getY()+0.5);
                    Vector vector = location.getDirection();
                    Location finalLoc = location.add(vector);
                    interaction.teleport(finalLoc);
                }
            }
        }.runTaskTimer(ElementalProject.getPlugin(), 0, 1L);
    }

    private void removeInteraction(Player player){
        for(Entity interaction : player.getNearbyEntities(1.5,1.5,1.5)){
            if(!(interaction instanceof Interaction))continue;

            if(interaction.getCustomName().equals("skillCheck")) {
                interaction.remove();
                if (player.getWorld() != interaction.getWorld())
                    interaction.remove();
            }
        }
    }
}
