package maxmeitner.signedit;

import org.bukkit.ChatColor;

public class ConfHandler {
    public static String improve(String path) {
        return ChatColor.translateAlternateColorCodes('&', SignEdit.getPlugin().getConfig().getString(path)
                .replaceAll("#([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])", "&x&$1&$2&$3&$4&$5&$6"));
    }
}
