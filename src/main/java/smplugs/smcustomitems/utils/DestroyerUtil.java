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

public class DestroyerUtil {

    public static final NamespacedKey DESTROYER_KEY = new NamespacedKey(SMCustomItems.getInstance(),"destroyer");
    public static final String DESTROYER_USE_PERM = "destroyer.use";
    public static final String DESTROYER_GIVE_SELF_PERM = "destroyer.give.self";
    public static final String DESTROYER_GIVE_OTHERS_PERM = "destroyer.give.others";
    public static final String DESTROYER_RELOAD_PERM = "destroyer.reload";

    private static final ArrayList<Permission> perms  = new ArrayList<>();

    private DestroyerUtil(){}

        public static ItemStack createDestroyer(){
            ItemStack sword = new ItemStack(Material.GOLDEN_SWORD);

            ItemMeta meta = sword.getItemMeta();

            meta.setDisplayName(ChatColor.DARK_PURPLE + "Niszczyciel");

            sword.setItemMeta(meta);

            return sword;
        }

        public static boolean isDestroyer(ItemStack stack){
            if (stack == null || stack.getType() != Material.GOLDEN_SWORD || !stack.hasItemMeta() || !stack.getItemMeta().hasDisplayName()) return false;

            else if(stack.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Niszczyciel")) return true;

            else return false;
        }

        public static boolean registerDestroyerRecipe(){
            ItemStack destroyer = DestroyerUtil.createDestroyer();
            ShapedRecipe destro = new ShapedRecipe(DESTROYER_KEY,destroyer);

            destro.shape("EEE",
                         "EDE",
                         "EEE");

            destro.setIngredient('E',Material.DIAMOND);
            destro.setIngredient('D',Material.BEACON);

            boolean success = Bukkit.addRecipe(destro);

           if(success)SMCustomItems.getInstance().getLogger().fine("Registered recipe of "+ DESTROYER_KEY.getNamespace()+":"+DESTROYER_KEY.getKey());
           else SMCustomItems.getInstance().getLogger().fine("Failed to register recipe of "+ DESTROYER_KEY.getNamespace()+":"+DESTROYER_KEY.getKey());

            return success;
        }

        public static void registerPermissions(){
            perms.add(new Permission(DESTROYER_USE_PERM,"Allows player to use Destroyer", PermissionDefault.TRUE));
            perms.add(new Permission(DESTROYER_GIVE_SELF_PERM,"Allows player to give themselves and Destroyer", PermissionDefault.OP));
            perms.add(new Permission(DESTROYER_GIVE_OTHERS_PERM,"Allows players to give others an Destroyer", PermissionDefault.OP));
            perms.add(new Permission(DESTROYER_RELOAD_PERM,"Allows player to reload configr", PermissionDefault.OP));

            for (Permission perm : perms){
                Bukkit.getPluginManager().addPermission(perm);
                SMCustomItems.getInstance().getLogger().fine("Registered Permission:"+perm.getName());
            }
        }

        public static void unregisterPermissions(){
        for (Permission perm : perms){
            Bukkit.getPluginManager().removePermission(perm);
            SMCustomItems.getInstance().getLogger().fine("Unregistered Permission:"+perm.getName());

        }
        perms.clear();
        }

        public static boolean unregisterDestroyerRecipe(){
            boolean success = Bukkit.removeRecipe(DESTROYER_KEY);

            if(success)SMCustomItems.getInstance().getLogger().fine("Unregistered recipe:" + DESTROYER_KEY.getNamespace()+":"+DESTROYER_KEY.getKey());
            else SMCustomItems.getInstance().getLogger().fine("Failed to unregister recipe: " + DESTROYER_KEY.getNamespace()+":"+DESTROYER_KEY.getKey());

            return success;

    }



}
