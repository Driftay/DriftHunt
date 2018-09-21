package me.driftay.drifthunt;

import com.massivecraft.factions.event.LandClaimEvent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Iterator;
import java.util.List;

public class DriftHuntListener implements Listener {
    private Main main;

    public DriftHuntListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        int x = (Integer) this.main.getConfig().get("Drift-Hunt.chest.x");
        int y = (Integer) this.main.getConfig().get("Drift-Hunt.chest.y");
        int z = (Integer) this.main.getConfig().get("Drift-Hunt.chest.z");
        World w = Bukkit.getServer().getWorld(this.main.getConfig().getString("Drift-Hunt.DefaultWorld"));
        Block chest = w.getBlockAt(x, y + 1, z);
        if (e.getBlock().equals(chest)) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Drift-Hunt.messages.cantbreak")));
        }

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        int x = (Integer) this.main.getConfig().get("Drift-Hunt.chest.x");
        int y = (Integer) this.main.getConfig().get("Drift-Hunt.chest.y");
        int z = (Integer) this.main.getConfig().get("Drift-Hunt.chest.z");
        World w = Bukkit.getServer().getWorld(this.main.getConfig().getString("Drift-Hunt.DefaultWorld"));
        Block block = w.getBlockAt(x, y + 1, z);
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().equals(block)) {
            int x1 = (Integer) this.main.getConfig().get("Drift-Hunt.chest.x");
            int y1 = (Integer) this.main.getConfig().get("Drift-Hunt.chest.y");
            int z1 = (Integer) this.main.getConfig().get("Drift-Hunt.chest.z");
            World w1 = Bukkit.getServer().getWorld(this.main.getConfig().getString("Drift-Hunt.DefaultWorld"));
            Block chest = w1.getBlockAt(x1, y1 + 1, z1);
            chest.setType(Material.AIR);
            List<String> runCommands = this.main.getConfig().getStringList("Drift-Hunt.commands");
            Iterator cmd = runCommands.iterator();

            while (cmd.hasNext()) {
                String command = (String) cmd.next();
                command = command.replaceAll("%player%", p.getName());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }

            Player[] player;
            int players = (player = Bukkit.getOnlinePlayers().toArray(new Player[0])).length;

            for (int oof = 0; oof < players; ++oof) {
                Player allP = player[oof];
                allP.playSound(allP.getLocation(), Sound.FIREWORK_BLAST, 1.0F, 0.0F);
                allP.playSound(allP.getLocation(), Sound.FIREWORK_BLAST2, 1.0F, 0.0F);
                allP.playSound(allP.getLocation(), Sound.FIREWORK_LARGE_BLAST, 1.0F, 0.0F);
                allP.playSound(allP.getLocation(), Sound.FIREWORK_LARGE_BLAST2, 1.0F, 0.0F);
                allP.playSound(allP.getLocation(), Sound.FIREWORK_LAUNCH, 1.0F, 0.0F);
                allP.playSound(allP.getLocation(), Sound.FIREWORK_TWINKLE, 1.0F, 0.0F);
                allP.playSound(allP.getLocation(), Sound.FIREWORK_TWINKLE2, 1.0F, 0.0F);
                allP.playSound(allP.getLocation(), Sound.LEVEL_UP, 1.0F, 0.0F);
            }

            this.main.getConfig().set("Drift-Hunt.chest.x", 0);
            this.main.getConfig().set("Drift-Hunt.chest.y", 0);
            this.main.getConfig().set("Drift-Hunt.chest.z", 0);
            this.main.saveConfig();
            List<String> foundby = this.main.getLang().getStringList(ChatColor.translateAlternateColorCodes('&', "Found-By").replace("%x%", String.valueOf(x).replace("%y%", String.valueOf(y).replace("%z%", String.valueOf(z)))));
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "/dhunt stop";
            Bukkit.dispatchCommand(console, command);
        }

    }


}
