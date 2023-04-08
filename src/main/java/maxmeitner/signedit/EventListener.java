package maxmeitner.signedit;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventListener implements Listener {
    private final Server server = SignEdit.getPlugin().getServer();

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent event){
        Block block = event.getClickedBlock();

        if (event.getAction()!=Action.RIGHT_CLICK_BLOCK || !(block.getState() instanceof Sign)) return;
        if (event.getItem()==null || event.getItem().getType()!=Material.FLINT) return;

        event.setCancelled(true);

        SignEditEvent signEditEvent = new SignEditEvent((Sign) block.getState(), event.getPlayer());
        server.getPluginManager().callEvent(signEditEvent);
    }


    @EventHandler
    private void onSignEdit(SignEditEvent event){
        Player player = event.getPlayer();
        Sign sign = event.getSign();
        Location loc = sign.getLocation();

        if (!player.hasPermission("signedit.edit") && !player.isOp()) {
            player.sendMessage(ConfHandler.improve("messages.noPerm").replace("{perm}", "signedit.edit"));
            return;
        }
        if (!player.hasPermission("signedit.bypass") || !player.isOp()) {
            if (server.getPluginManager().getPlugin("WorldGuard")!=null) {
                RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
                if (!query.testState(BukkitAdapter.adapt(loc), WorldGuardPlugin.inst().wrapPlayer(player), Flags.BUILD)) {
                    player.sendMessage(ConfHandler.improve("messages.non-yourRegion"));
                    return;
                }
            }
            if (loc.toVector().subtract(loc.getWorld().getSpawnLocation().toVector()).length()<=server.getSpawnRadius()) {
                player.sendMessage(ConfHandler.improve("messages.spawnProtection"));
                return;
            }
        }

        sign.setEditable(true);
        player.openSign(sign);
    }
}
