package maxmeitner.signedit.utils;

import org.bukkit.ChatColor;

public class StrHandler {
    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str
                .replaceAll("#([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])", "&x&$1&$2&$3&$4&$5&$6"));
    }
}
