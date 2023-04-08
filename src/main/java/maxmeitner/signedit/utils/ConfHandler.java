package maxmeitner.signedit.utils;

import maxmeitner.signedit.SignEdit;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfHandler {
    private static final FileConfiguration conf = SignEdit.getPlugin().getConfig();

    public static String improve(String path) {return StrHandler.color(conf.getString(path));}

    public static Boolean getBoolean(String path) {return conf.getBoolean(path);}
}
