package maxmeitner.signedit;

import maxmeitner.signedit.utils.ConfHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length==0 || !args[0].equals("reload")) {
            sender.sendMessage(ConfHandler.improve("messages.help"));
            return false;
        }

        if (!sender.hasPermission("signedit.reload")) {
            sender.sendMessage(ConfHandler.improve("messages.noPerm").replace("{perm}", "signedit.reload"));
            return false;
        }

        SignEdit.getPlugin().reloadConfig();
        sender.sendMessage(ConfHandler.improve("messages.configReloaded"));
        return true;
    }
}
