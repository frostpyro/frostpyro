package org.example.contentplugin.elementalproject.interaction.interacting;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.example.contentplugin.elementalproject.ElementalProject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Deprecated
public class Summoning {
    private Map<UUID, UUID> interactionOwn = new HashMap<>();

    private boolean itemBool(ItemStack itemStack){
        Material material = itemStack.getType();
        if(material==Material.NETHERITE_SWORD || material==Material.WOODEN_HOE || material==Material.BOW || material==Material.DIAMOND_SWORD){
            ItemMeta itemMeta = itemStack.getItemMeta();

            if(itemMeta==null) return false;

            return itemMeta.getCustomModelData() == 1;
        }

        return false;
    }

    @Deprecated
    public void playerItemChange(PlayerItemHeldEvent event){
        Player p = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = p.getInventory().getItem(slot);
        if(item == null) return;
        if(itemBool(item))
            spawnInteraction(p);
        else
            removeInteraction(p);
    }
    @Deprecated
    public void joinInteraction(PlayerJoinEvent event){
        Player player = event.getPlayer();
        for(Entity entity : player.getNearbyEntities(5,5,5)){
            if(entity instanceof Interaction){
                entity.remove();
            }
        }
        ItemStack item = player.getInventory().getItemInMainHand();

        if(itemBool(item))
            spawnInteraction(player);
    }
    public void spawn(Player p){
        ItemStack item = p.getInventory().getItemInMainHand();
        if(item.getItemMeta()==null) return;
        if(!itemBool(item)) return;
        spawnInteraction(p);
    }
    @Deprecated
    public void removeOnDead(Player p){
        removeInteraction(p);
    }

    private void spawnInteraction(Player player){
        Interaction interaction = (Interaction) player.getWorld().spawnEntity(player.getLocation(), EntityType.INTERACTION);
        interaction.setInteractionHeight(2F);
        interaction.setInteractionWidth(1.5F);
        interaction.setCustomName("skillCheck");
        interactionOwn.put(player.getUniqueId(), interaction.getUniqueId());
        new BukkitRunnable(){
            @Override
            public void run() {
                if(!player.isOnline()){
                    interactionOwn.remove(player.getUniqueId());
                    interaction.remove();
                    cancel();
                }
                else{
                    Location location = player.getLocation();
                    location.setY(location.getY()+0.5);
                    Vector vector = location.getDirection();
                    Location finalLoc = location.add(vector);
                    interaction.teleport(finalLoc);
                }
            }
        }.runTaskTimer(ElementalProject.getPlugin(), 0L, 0L);
    }
    @Deprecated
    public void removeOnDimensionChange(Player player){
        Interaction interaction = (Interaction) Bukkit.getServer().getEntity(interactionOwn.get(player.getUniqueId()));
        if(interaction == null) return;
        interaction.remove();
    }
    private void removeInteraction(Player player){
        for(Entity interaction : player.getWorld().getEntities()){
            if(!(interaction instanceof Interaction))continue;

            if(interaction.getCustomName().equals("skillCheck")) {
                interaction.remove();
                if (player.getWorld() != interaction.getWorld())
                    interaction.remove();
            }
        }
    }
}
