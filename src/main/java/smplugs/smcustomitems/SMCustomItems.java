package smplugs.smcustomitems;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;
import smplugs.smcustomitems.utils.DestroyerUtil;
import smplugs.smcustomitems.commands.DestroyerCommand;
import smplugs.smcustomitems.configs.Config;
import smplugs.smcustomitems.events.DestroyerEvents;

public final class SMCustomItems extends JavaPlugin {

    //Instancja loggera - loguje wiadomosci debugera i informacje o tym co plugin robi
    private final Logger logger = Logger.getLogger("smcustomitems");

    //wewnętrzny obiekt config - przechowywuje configuracje opcji które wlasciciel serwera moze ustawic
    private static Config config;

    //instancja pluginu dla latwego dostepu
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

    //Bierze loggera dla tego pluginu
    public Logger getLogger(){
        return logger;
    }

    //Bierze instancje tego pluginu
    //zwraca statyczna instancje tego pluginu
    public static SMCustomItems getInstance(){
        return plugin;
    }

    //Bierze wewnętrzny config
    //Zwraca config
    public static Config getInternalConfig(){
        return config;
    }
}
