package maxmeitner.signedit;

import org.bukkit.plugin.java.JavaPlugin;

public final class SignEdit extends JavaPlugin {
    private static SignEdit plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getCommand("signedit").setExecutor(new ReloadCommand());
    }

    @Override
    public void onDisable() {}

    public static SignEdit getPlugin() {return plugin;}
}
