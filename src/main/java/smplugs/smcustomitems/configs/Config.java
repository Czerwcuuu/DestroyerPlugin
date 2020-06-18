package smplugs.smcustomitems.configs;

import org.bukkit.configuration.file.FileConfiguration;
import smplugs.smcustomitems.SMCustomItems;

public final class Config {
    public static final String REQUIRE_GOLD = "destroyer.usegold";
    public static final String NO_PERM_KEY = "locale.noperm";
    public static final String INVALID_PLAYER_KEY = "locale.invalidplayer";
    public static final String ONLY_PLAYER_KEY = "locale.onlyplayers";
    public static final String GIVE_KEY = "locale.givedestroyer";
    public static final String CONFIG_RELOAD_KEY = "locale.reload";

    private String noPermMessage, invalidPlayerMessage, onlyPlayersMessage, giveDestroyerMessage, configReloadMessage;

    private boolean useGold;

    public Config(){
        setDefaults();
        loadConfig();
    }

    public void loadConfig(){
        FileConfiguration config = SMCustomItems.getInstance().getConfig();

        useGold = config.getBoolean(REQUIRE_GOLD,true);
        noPermMessage = config.getString(NO_PERM_KEY,"&4You do not have permission for that!");
        invalidPlayerMessage = config.getString(INVALID_PLAYER_KEY,"&4That is not a valid player!");
        onlyPlayersMessage = config.getString(ONLY_PLAYER_KEY,"&4Only players can enter that command!");
        giveDestroyerMessage = config.getString(GIVE_KEY,"&2Gave destroyer to %PLAYER%!");
        configReloadMessage = config.getString(CONFIG_RELOAD_KEY,"&2[Destroyer Config Reloaded]");

    }

    public void setDefaults(){
        FileConfiguration config = SMCustomItems.getInstance().getConfig();

        config.addDefault(REQUIRE_GOLD,true);
        config.addDefault(NO_PERM_KEY,"&4You do not have permission for that!");
        config.addDefault(INVALID_PLAYER_KEY,"&4That is not a valid player!");
        config.addDefault(ONLY_PLAYER_KEY,"&4Only players can enter that command!");
        config.addDefault(GIVE_KEY,"&2Gave destroyer to %PLAYER%!");
        config.addDefault(CONFIG_RELOAD_KEY,"&2[Destroyer Config Reloaded]");

        config.options().copyDefaults(true);

        SMCustomItems.getInstance().saveConfig();
    }

    public void saveConfig(){
        FileConfiguration config = SMCustomItems.getInstance().getConfig();

        config.set(REQUIRE_GOLD,useGold);
        config.set(NO_PERM_KEY,noPermMessage);
        config.set(INVALID_PLAYER_KEY,invalidPlayerMessage);
        config.set(GIVE_KEY,giveDestroyerMessage);
        config.set(CONFIG_RELOAD_KEY,configReloadMessage);

        SMCustomItems.getInstance().saveConfig();
    }

    public void reloadConfig(){
        loadConfig();
    }

    public boolean isUseGold(){
        return useGold;
    }

    public void setUseGold(boolean useGold){
        this.useGold = useGold;
    }

    public String getNoPermMessage(){
        return getNoPermMessage();
    }

    public void  setNoPermMessage(String message){
        this.noPermMessage = message;
    }

    public String getInvalidPlayerMessage(){
        return invalidPlayerMessage;
    }

    public void setInvalidPlayerMessage(String message){
        this.invalidPlayerMessage = message;
    }

    public String getOnlyPlayersMessage(){
        return onlyPlayersMessage;
    }

    public void setOnlyPlayersMessage(String message){
        this.onlyPlayersMessage = message;
    }

    public String getGiveDestroyerMessage(){
        return giveDestroyerMessage;
    }

    public void setGiveDestroyerMessage(String message){
        this.giveDestroyerMessage = message;
    }

    public String getConfigReloadMessage(){
        return configReloadMessage;
    }

    public void setConfigReloadMessage(String message){
        this.configReloadMessage = message;
    }
}
