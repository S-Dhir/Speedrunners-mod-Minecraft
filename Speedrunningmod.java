package com.lord_sive.speedrunningmod;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Speedrunningmod extends JavaPlugin implements Listener {
    private static Location spawnLocation;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("respawn")) {
            if (sender instanceof Player) {
                spawnLocation = ((Player) sender).getLocation();
                ((Player) sender).sendMessage(ChatColor.MAGIC+"Done!");

            }
        }
        return true;

    }

    public void runCommand(String command) {
        getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    @EventHandler
    public void whenPlayerDies(PlayerRespawnEvent event) {

        Player whut = event.getPlayer();
        System.out.println(whut.getDisplayName() + " just died");
        getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamerule doImmediateRespawn true");
        if(event.getPlayer().getBedSpawnLocation() == null) {
            runCommand("say Player does not have bed");
            event.setRespawnLocation(spawnLocation);
        } else {
            runCommand("say p's got a bed ma man");
        }
        event.getPlayer().getInventory().setItemInOffHand(new ItemStack(Material.COMPASS));
//        whut.performCommand("/gamerule doImmediateRespawn true");
//        whut.performCommand("/tp @p 0,128,0");
    }

}
