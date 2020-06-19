package smplugs.smcustomitems.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import smplugs.smcustomitems.SMCustomItems;
import smplugs.smcustomitems.utils.DestroyerUtil;

public class DestroyerEvents implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void WhenAttack(EntityDamageByEntityEvent event){
    if(event.isCancelled()) return;

    if(event.getEntity() instanceof Mob){
        Player player = (Player) event.getDamager();

        if(!player.hasPermission(DestroyerUtil.DESTROYER_USE_PERM)) return;

        ItemStack hand = player.getInventory().getItemInMainHand();

        if(DestroyerUtil.isDestroyer(hand)){
            if(SMCustomItems.getInternalConfig().isUseGold()){
                if(player.getGameMode()!= GameMode.CREATIVE){
                    if(!player.getInventory().contains(Material.GOLD_INGOT)){
                        event.setCancelled(true);
                        return;
                    }
                    else{
                        ItemStack gold = new ItemStack(Material.GOLD_INGOT,1);

                        player.getInventory().removeItem(gold);
                    }
                }
            }
            World w = player.getWorld();
            w.createExplosion(event.getEntity().getLocation(),3,true);
        }
    }

    }
}
