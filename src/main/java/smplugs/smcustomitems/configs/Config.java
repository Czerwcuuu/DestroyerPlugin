package smplugs.smcustomitems.configs;

import org.bukkit.configuration.file.FileConfiguration;
import smplugs.smcustomitems.SMCustomItems;

//Config sluzacy do przechowywania wszystkich wartosci w pliku konfiguracyjnym
public final class Config {
    //Klucze dostepu informacji w configu plugina
    //Uzywamy ich zeby uniknac "Magic Values"
    public static final String REQUIRE_GOLD = "destroyer.usegold";
    public static final String NO_PERM_KEY = "locale.noperm";
    public static final String INVALID_PLAYER_KEY = "locale.invalidplayer";
    public static final String ONLY_PLAYER_KEY = "locale.onlyplayers";
    public static final String GIVE_KEY = "locale.givedestroyer";
    public static final String CONFIG_RELOAD_KEY = "locale.reload";

    //Stringi do przechowywania wiadomosci
    private String noPermMessage, invalidPlayerMessage, onlyPlayersMessage, giveDestroyerMessage, configReloadMessage;

    //UseGold boolean
    private boolean useGold;

    //Defaultowy konstruktor
    public Config(){
        setDefaults();
        loadConfig();
    }

    //Wczytaj dane z configu do naszych zmiennych
    public void loadConfig(){
        FileConfiguration config = SMCustomItems.getInstance().getConfig();

        useGold = config.getBoolean(REQUIRE_GOLD,true);
        noPermMessage = config.getString(NO_PERM_KEY,"&4You do not have permission for that!");
        invalidPlayerMessage = config.getString(INVALID_PLAYER_KEY,"&4That is not a valid player!");
        onlyPlayersMessage = config.getString(ONLY_PLAYER_KEY,"&4Only players can enter that command!");
        giveDestroyerMessage = config.getString(GIVE_KEY,"&2Gave destroyer to %PLAYER%!");
        configReloadMessage = config.getString(CONFIG_RELOAD_KEY,"&2[Destroyer Config Reloaded]");

    }

    //Ustaw domyslne wartosci configu
    //Stworzy to plik jesli on jeszcze nie istnieje
    //Ustawi on tez domyslne wartosci dla pól lub napisze je jesli ich brakuje
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

    //Zapisuje zmiany w configu
    public void saveConfig(){
        FileConfiguration config = SMCustomItems.getInstance().getConfig();

        config.set(REQUIRE_GOLD,useGold);
        config.set(NO_PERM_KEY,noPermMessage);
        config.set(INVALID_PLAYER_KEY,invalidPlayerMessage);
        config.set(GIVE_KEY,giveDestroyerMessage);
        config.set(CONFIG_RELOAD_KEY,configReloadMessage);

        SMCustomItems.getInstance().saveConfig();
    }

    //Reloaduje config
    public void reloadConfig(){
        loadConfig();
    }

    //Sprawdza czy zloto jest potrzebne do uzywania przedmiotu
    public boolean isUseGold(){
        return useGold;
    }

    //Ustawia czy uzywac/nieuzywac zlota
    public void setUseGold(boolean useGold){
        this.useGold = useGold;
    }

    //Pobiera wiadomosc
    public String getNoPermMessage(){
        return getNoPermMessage();
    }

    //Ustawia wiadomosc
    public void  setNoPermMessage(String message){
        this.noPermMessage = message;
    }

    //Pobiera wiadomosc
    public String getInvalidPlayerMessage(){
        return invalidPlayerMessage;
    }

    //Ustawia wiadomosc
    public void setInvalidPlayerMessage(String message){
        this.invalidPlayerMessage = message;
    }

    //Pobiera wiadomosc
    public String getOnlyPlayersMessage(){
        return onlyPlayersMessage;
    }

    //Ustawia wiadomosc
    public void setOnlyPlayersMessage(String message){
        this.onlyPlayersMessage = message;
    }

    //Pobiera wiadomosc
    public String getGiveDestroyerMessage(){
        return giveDestroyerMessage;
    }

    //Ustawia wiadomosc
    public void setGiveDestroyerMessage(String message){
        this.giveDestroyerMessage = message;
    }

    //Pobiera wiadomosc
    public String getConfigReloadMessage(){
        return configReloadMessage;
    }

    //Ustawia wiadomosc
    public void setConfigReloadMessage(String message){
        this.configReloadMessage = message;
    }
}
