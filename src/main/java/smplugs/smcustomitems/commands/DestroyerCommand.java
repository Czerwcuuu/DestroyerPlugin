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

//klasa egzekturoa komendy
public class DestroyerCommand  implements CommandExecutor {


    //Odpala się kiedy ktoś wpisz komende 'destroyer'
    //CommandSender - Player/CommandBlock/Console - zawsze powinniśmy sprawdzać kto nim jest
    //Command zawsze daje komende która jest odpalana
    //label - alias używany zamiast destroyera
    //args - argumenty wpisane w komendzie
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        //Jezeli nie ma zadnych argumentow
        if(args.length == 0){

            //Sprawdz czy gracz ma permisje do uzywania tej komendy
            if(sender.hasPermission(DestroyerUtil.DESTROYER_GIVE_SELF_PERM)){

                //Zanim odpalimy komende musimy sprawdzic czy odpala ja gracz
                if(sender instanceof Player){

                    //Jezeli jestesmy pewni ze gracz, to ustawiamy typ sendera na Player
                    Player player = (Player)sender;

                    //Tworzymy przedmiot reprezentujacy destroyera
                    ItemStack destroyer = DestroyerUtil.createDestroyer();

                    //Dodajemy go do ekwipunku
                    player.getInventory().addItem(destroyer);

                    //Wyslij graczowi wiadomosc o otrzymanym przedmiocie
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SMCustomItems.getInternalConfig().
                    getGiveDestroyerMessage().replaceAll("%PLAYER%",player.getDisplayName())));

                    //Zwróc true jezeli udalo sie
                    return true;
                }

                else{
                    //Wyslij wiadomosc ze tylko gracz moze uzywac tej komendy
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',SMCustomItems.getInternalConfig().
                            getOnlyPlayersMessage()));

                    //Zwróc false bo komenda sie nie powiodla
                    return false;
                }

            }
            else{
                //Zwroc wiadomosc ze nie ma permisji do uzywania tej komendy
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',SMCustomItems.getInternalConfig().getNoPermMessage()));
                return false;
            }
        }
        //Jezeli podany jest argument
        else if(args.length == 1){
            //Sprawdz czy na serwerze jest gracz z takim nickiem
            if(Bukkit.getPlayer(args[0]) != null){
                //Sprawdz czy egzekutor ma permisje do dawania destroyera innym
                if(sender.hasPermission(DestroyerUtil.DESTROYER_GIVE_OTHERS_PERM)){
                    //Pobierz informacje o graczu z tym nickiem
                    Player player = Bukkit.getPlayer(args[0]);

                    //Stworz destroyera
                    ItemStack destroyer = DestroyerUtil.createDestroyer();

                    //Wyslij informacje o przekazaniu destroyera
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SMCustomItems.getInternalConfig().getGiveDestroyerMessage()).replaceAll("%PLAYER%", player.getDisplayName()));

                    return true;
                }
                else{
                    //Nie ma permisji do dawania innym graczom
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SMCustomItems.getInternalConfig().getNoPermMessage()));

                    return false;
                }
            }
            //Jezeli argument rowna sie 'reload'
            else if(args[0].equalsIgnoreCase("reload")){
                //Jezeli posiada permisje do reloadu
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
