package com.minegusta.mgracesredone.listeners.general;

import com.minegusta.mgracesredone.data.Storage;
import com.minegusta.mgracesredone.files.FileManager;
import com.minegusta.mgracesredone.playerdata.MGPlayer;
import com.minegusta.mgracesredone.playerdata.MapManager;
import com.minegusta.mgracesredone.races.skilltree.abilities.AbilityType;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GeneralListener implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        MapManager.add(e.getPlayer(), FileManager.getFile(e.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        MapManager.remove(e.getPlayer());
    }

    @EventHandler
    public void onWorldSwitch(PlayerChangedWorldEvent e)
    {
        MGPlayer mgp = Storage.getPlayer(e.getPlayer());

        mgp.updateHealth();
    }
}
