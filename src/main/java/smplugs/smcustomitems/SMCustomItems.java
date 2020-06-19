package smplugs.smcustomitems;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;
import smplugs.smcustomitems.utils.DestroyerUtil;
import smplugs.smcustomitems.commands.DestroyerCommand;
import smplugs.smcustomitems.configs.Config;
import smplugs.smcustomitems.events.DestroyerEvents;

public final class SMCustomItems extends JavaPlugin {

    private final Logger logger = Logger.getLogger("smcustomitems");
    private static Config config;

    private static SMCustomItems plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new DestroyerEvents(),this);
        config = new Config();
        this.getCommand("destroyer").setExecutor(new DestroyerCommand());
        DestroyerUtil.registerDestroyerRecipe();
        DestroyerUtil.registerPermissions();

    }

    @Override
    public void onDisable() {
        DestroyerUtil.unregisterDestroyerRecipe();
        DestroyerUtil.unregisterPermissions();
    }

    public Logger getLogger(){
        return logger;
    }

    public static SMCustomItems getInstance(){
        return plugin;
    }

    public static Config getInternalConfig(){
        return config;
    }
}
