package cn.nekopixel.cancelbreakingevent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CancelBreakingEvent extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("加载完成！");
    }

    @Override
    public void onDisable() {
        getLogger().info("卸载完成！");
    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {
        event.setCancelled(true);
        event.getPlayer().sendBlockChange(event.getBlock().getLocation(), event.getBlock().getBlockData());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
        event.getPlayer().sendBlockChange(event.getBlock().getLocation(), event.getBlock().getBlockData());
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR) {
            event.setCancelled(true);
            if (action == Action.LEFT_CLICK_BLOCK && event.getClickedBlock() != null) {
                event.getPlayer().sendBlockChange(event.getClickedBlock().getLocation(), event.getClickedBlock().getBlockData());
            }
        }
    }
}
