package smplugs.smcustomitems.utils;

import com.sun.tools.javac.jvm.Items;
import jdk.tools.jlink.internal.plugins.SystemModulesPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import smplugs.smcustomitems.SMCustomItems;

import java.util.ArrayList;

//klasa Utility z różnymi statycznymi metodami żeby zapewnic czyste i łatwe API
//Deklarujemy jako final, bo są tutaj metody których nie chcemy przypadkowo rozszerzeyc
public final class DestroyerUtil {

    //NameSpaceKey uzywany dla recepty destroyera
    public static final NamespacedKey DESTROYER_KEY = new NamespacedKey(SMCustomItems.getInstance(),"destroyer");

    //Permisje
    public static final String DESTROYER_USE_PERM = "destroyer.use";
    public static final String DESTROYER_GIVE_SELF_PERM = "destroyer.give.self";
    public static final String DESTROYER_GIVE_OTHERS_PERM = "destroyer.give.others";
    public static final String DESTROYER_RELOAD_PERM = "destroyer.reload";


    //Lista wszystkich zarejestrowanych permisji
    private static final ArrayList<Permission> perms  = new ArrayList<>();

    //Zapobiega inicjowaniu tej klasy, poniewaz ma byc uzywana tylko jako statyczne narzedzie
    private DestroyerUtil(){}

        //tworzy destroyera
        public static ItemStack createDestroyer(){
            //stworz zloty miecz
            ItemStack sword = new ItemStack(Material.GOLDEN_SWORD);

            //pobierz metainfo przedmiotu
            ItemMeta meta = sword.getItemMeta();

            //Zmiana display name'u
            meta.setDisplayName(ChatColor.DARK_PURPLE + "Niszczyciel");

            //Ustaw nowe metainfo dla itemu
            sword.setItemMeta(meta);

            //Zwróc stworzony przedmiot
            return sword;
        }

        //Sprawdza czy dany przedmiot jest Destroyerem
        public static boolean isDestroyer(ItemStack stack){
            //Upewnij sie ze IteStack nie jest nullem, jest mieczem i ma itemmete + nazwe - zwroc false jesli jeden z tych warunkow jest spelniony
            if (stack == null || stack.getType() != Material.GOLDEN_SWORD || !stack.hasItemMeta() || !stack.getItemMeta().hasDisplayName()) return false;

            //Jezeli nazwa jest taka sama jak ItemStack to zwroc true
            else if(stack.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Niszczyciel")) return true;

            //W przeciwnym wypadku zwroc false
            else return false;
        }

        //Rejestruj recepte na Destroyera
        public static boolean registerDestroyerRecipe(){
            //Pobierz ItemStack destroyera
            ItemStack destroyer = DestroyerUtil.createDestroyer();

            //Stwórz obiekt ShapedRecipe - odpowiedzialny za crafting
            ShapedRecipe destro = new ShapedRecipe(DESTROYER_KEY,destroyer);

            destro.shape("EEE",
                         "EDE",
                         "EEE");

            destro.setIngredient('E',Material.DIAMOND);
            destro.setIngredient('D',Material.BEACON);

            //Dodaj recepte na serwer i przechowuj ją
            boolean success = Bukkit.addRecipe(destro);

            //Jeżeli sukces to wyświetl:
           if(success)SMCustomItems.getInstance().getLogger().fine("Registered recipe of "+ DESTROYER_KEY.getNamespace()+":"+DESTROYER_KEY.getKey());
            //Jeżeli błąd to wyświetl:
           else SMCustomItems.getInstance().getLogger().fine("Failed to register recipe of "+ DESTROYER_KEY.getNamespace()+":"+DESTROYER_KEY.getKey());
            //Zwróc sukces/błąd (true/false)
            return success;
        }

        //Rejestracja permisji
        public static void registerPermissions(){
            //Dodawanie permisji do tablicy
            perms.add(new Permission(DESTROYER_USE_PERM,"Allows player to use Destroyer", PermissionDefault.TRUE));
            perms.add(new Permission(DESTROYER_GIVE_SELF_PERM,"Allows player to give themselves and Destroyer", PermissionDefault.OP));
            perms.add(new Permission(DESTROYER_GIVE_OTHERS_PERM,"Allows players to give others an Destroyer", PermissionDefault.OP));
            perms.add(new Permission(DESTROYER_RELOAD_PERM,"Allows player to reload configr", PermissionDefault.OP));

            //Dodanie kazdej permisji z tablicy do stosu.
            for (Permission perm : perms){
                Bukkit.getPluginManager().addPermission(perm);
                SMCustomItems.getInstance().getLogger().fine("Registered Permission:"+perm.getName());
            }
        }

        //Usuniecie wszystkich permisji z stosu
        public static void unregisterPermissions(){
        for (Permission perm : perms){
            Bukkit.getPluginManager().removePermission(perm);
            SMCustomItems.getInstance().getLogger().fine("Unregistered Permission:"+perm.getName());

        }
        //wyczyść liste
        perms.clear();
        }

        //Wyczyszczenie recepty na destroyera
        public static boolean unregisterDestroyerRecipe(){
            boolean success = Bukkit.removeRecipe(DESTROYER_KEY);

            if(success)SMCustomItems.getInstance().getLogger().fine("Unregistered recipe:" + DESTROYER_KEY.getNamespace()+":"+DESTROYER_KEY.getKey());
            else SMCustomItems.getInstance().getLogger().fine("Failed to unregister recipe: " + DESTROYER_KEY.getNamespace()+":"+DESTROYER_KEY.getKey());

            return success;

    }



}
