package com.minegusta.mgracesredone.commands;

import com.minegusta.mgracesredone.races.RaceType;
import com.minegusta.mgracesredone.util.MGItem;
import com.minegusta.mgracesredone.recipes.Recipe;
import com.minegusta.mgracesredone.util.ChatUtil;
import com.minegusta.mgracesredone.util.Races;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RaceCommand implements CommandExecutor {
    private Player p;

    //Help list
    private String[] help = {"/Race Help " + ChatColor.GRAY + "- Show this help menu.", "/Race Amount " + ChatColor.GRAY + "- Show how many people there are per race.", "/Race List " + ChatColor.GRAY + "- Display a list of races.", "/Race Info <Race> " + ChatColor.GRAY + "- Info on the given race.", "/Race Show " + ChatColor.GRAY + "- Show info about your race.", "/Race Cure " + ChatColor.GRAY + "- Display cure info.", "/Race Infection <Race> " + ChatColor.GRAY + "- Show how to become a race.", "/Race Recipes " + ChatColor.GRAY + "- Show all recipes related to races."};

    //Command

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args)
    {
        if (!(s instanceof Player)) return true;
        this.p = (Player) s;


        //Check for args here

        if(args.length == 0)
        {
            sendList(help);
            return true;
        }

        if(args.length == 1)
        {
            if(args[0].equalsIgnoreCase("help"))
            {
                sendList(help);
                return true;
            }
            if(args[0].equalsIgnoreCase("amount"))
            {
                sendList(Races.getRaceAmounts());
                return true;
            }
            if(args[0].equalsIgnoreCase("list"))
            {
                sendList(RaceType.values());
                return true;
            }
            if(args[0].equalsIgnoreCase("show"))
            {
                sendOwnInfo();
                return true;
            }
            if(args[0].equalsIgnoreCase("cure"))
            {
                sendList(RaceType.HUMAN.getInfectionInfo());
                return true;
            }
            if(args[0].equalsIgnoreCase("recipes") || args[0].equalsIgnoreCase("recipe"))
            {
                sendList(Recipe.values());
                return true;
            }
            sendList(help);
            return true;
        }
        if(args.length == 2)
        {
            if(args[0].equalsIgnoreCase("info") && RaceType.valueOf(args[1]) != null)
            {
                RaceType race = RaceType.valueOf(args[1]);
                sendInfo(race);
                return true;
            }
            if((args[0].equalsIgnoreCase("infect") || args[0].equalsIgnoreCase("infection")) && RaceType.valueOf(args[1])!= null)
            {
                RaceType race = RaceType.valueOf(args[1]);
                sendList(race.getInfectionInfo());
                return true;
            }
            sendList(help);
            return true;
        }
        sendList(help);
        return true;
    }



    private void sendList(String[] list)
    {
        ChatUtil.sendFancyBanner(p);
        for(String s : list)
        {
            p.sendMessage(ChatColor.LIGHT_PURPLE + s);
        }
        ChatUtil.sendFooter(p);
    }

    private void sendList(RaceType[] list)
    {
        ChatUtil.sendFancyBanner(p);
        for(RaceType type : list)
        {
            p.sendMessage(ChatColor.LIGHT_PURPLE + type.getName());
        }
        ChatUtil.sendFooter(p);
    }

    private void sendList(Recipe[] list)
    {
        ChatUtil.sendFancyBanner(p);
        for(Recipe recipe : list)
        {
            p.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + recipe.getRecipeName());
            for(MGItem item : recipe.getRecipe().getIngriedients())
            {
                p.sendMessage(ChatColor.YELLOW + " - " + ChatColor.DARK_PURPLE + item.getAmount() + ChatColor.LIGHT_PURPLE + item.getMaterial().toString());
            }
        }
        ChatUtil.sendFancyBanner(p);
    }

    private void sendMessage(String[] text)
    {
        ChatUtil.sendFancyBanner(p);
        for(String s : text)
        {
            p.sendMessage(ChatColor.LIGHT_PURPLE + s);
        }
    }

    private void sendInfo(RaceType race)
    {
        ChatUtil.sendFancyBanner(p);
        p.sendMessage(ChatColor.LIGHT_PURPLE + "Race: " + ChatColor.YELLOW + race.getName());
        p.sendMessage(ChatColor.LIGHT_PURPLE + "Health: " + ChatColor.DARK_RED + (race.getHealth() / 2));
        p.sendMessage("");
        for(String s : race.getInfo())
        {
            p.sendMessage(ChatColor.LIGHT_PURPLE + s);
        }
        ChatUtil.sendFooter(p);
    }

    private void sendOwnInfo()
    {
        RaceType race = Races.getRace(p);
        ChatUtil.sendFancyBanner(p);
        p.sendMessage(ChatColor.LIGHT_PURPLE + "You are a(n) " + ChatColor.YELLOW + race.getName());
        p.sendMessage(ChatColor.LIGHT_PURPLE + "Your maximum health is " + ChatColor.DARK_RED + (race.getHealth() / 2));
        p.sendMessage("");
        for(String s : race.getInfo())
        {
            p.sendMessage(ChatColor.LIGHT_PURPLE + s);
        }
        ChatUtil.sendFooter(p);
    }



}
