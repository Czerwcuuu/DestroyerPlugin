package smplugs.smcustomitems.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import smplugs.smcustomitems.SMCustomItems;
import smplugs.smcustomitems.utils.DestroyerUtil;

import smplugs.smcustomitems.utils.DestroyerUtil;
import smplugs.smcustomitems.SMCustomItems;

public class DestroyerCommand  implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length == 0){
            if(sender.hasPermission(DestroyerUtil.DESTROYER_GIVE_SELF_PERM)){
                if(sender instanceof Player){
                    Player player = (Player)sender;

                    ItemStack destroyer = DestroyerUtil.createDestroyer();
                    player.getInventory().addItem(destroyer);

                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SMCustomItems.getInternalConfig().
                    getGiveDestroyerMessage().replaceAll("%PLAYER%",player.getDisplayName())));

                    return true;
                }
                else{
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',SMCustomItems.getInternalConfig().
                            getOnlyPlayersMessage()));

                    return false;
                }

            }
            else{
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',SMCustomItems.getInternalConfig().getNoPermMessage()));
                return false;
            }
        }
        else if(args.length == 1){
            if(Bukkit.getPlayer(args[0]) != null){
                if(sender.hasPermission(DestroyerUtil.DESTROYER_GIVE_OTHERS_PERM)){
                    Player player = Bukkit.getPlayer(args[0]);

                    ItemStack destroyer = DestroyerUtil.createDestroyer();

                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SMCustomItems.getInternalConfig().getGiveDestroyerMessage()).replaceAll("%PLAYER%", player.getDisplayName()));

                    return true;
                }
                else{
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SMCustomItems.getInternalConfig().getNoPermMessage()));

                    return false;
                }
            }
            else if(args[0].equalsIgnoreCase("reload")){
                if(sender.hasPermission(DestroyerUtil.DESTROYER_RELOAD_PERM)){
                    SMCustomItems.getInternalConfig().reloadConfig();

                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SMCustomItems.getInternalConfig().getConfigReloadMessage()));

                    return true;
                }
                else{
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SMCustomItems.getInternalConfig().getNoPermMessage()));

                    return false;
                }
            }
            else{
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SMCustomItems.getInternalConfig().getInvalidPlayerMessage()));

                return false;
            }
        }
        else{
            sender.sendMessage(ChatColor.AQUA + "/destroyer <player>");

            return false;
        }
    }
}
