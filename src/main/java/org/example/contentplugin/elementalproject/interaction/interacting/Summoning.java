package org.example.contentplugin.elementalproject.interaction.interacting;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.example.contentplugin.elementalproject.ElementalProject;

public class Summoning {


    public void playerItemChange(PlayerItemHeldEvent event){
        Player p = event.getPlayer();
        Location loc = p.getLocation();


    }

    private void spawnInteraction(Player player, boolean bool){
        Interaction interaction = (Interaction) player.getWorld().spawnEntity(player.getLocation(), EntityType.INTERACTION);
        interaction.setInteractionHeight(2.0F);
        interaction.setCustomName("skillCheck");
        new BukkitRunnable(){
            @Override
            public void run() {
                if(bool){
                    Location location = player.getLocation();
                    interaction.teleport(location);
                }
                else{
                    interaction.remove();
                    cancel();
                }
            }
        }.runTaskTimer(ElementalProject.getPlugin(), 0, 1L);
    }

}
