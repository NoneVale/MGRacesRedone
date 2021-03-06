package com.minegusta.mgracesredone.races;

import com.minegusta.mgracesredone.main.Races;
import com.minegusta.mgracesredone.playerdata.MGPlayer;
import com.minegusta.mgracesredone.races.skilltree.abilities.AbilityType;
import com.minegusta.mgracesredone.util.*;
import org.bukkit.*;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class Elf extends Race {
    @Override
    public double getHealth() {
        return 18;
    }

    @Override
    public String getName() {
        return "Elf";
    }

    @Override
    public String[] getInfectionInfo() {
        return new String[]
                {
                        "To become an Elf, follow these steps:",
                        "Get a bow, and kill 100 creatures with it.",
                        "It will announce how many kills you have.",
                        "When you have 100 kills, craft an Elf Stew.",
                        "To see the recipe for Elf Stew, type /Race Recipes",
                        "Now eat the stew and you will be an Elf."
                };
    }

    @Override
    public String[] getInfo() {
        return new String[]
                {
                        "Elves are closely related to nature.",
                        "They gain regeneration from eating vegetarian food.",
                        "Elves regenerate in water and are skilled with bows.",
                        "All bow damage is thus increased.",
                        "When low on health, nearby animals will sacrifice their life for the perks.",
                        "Fire is one of the weaknesses to elves, though the sun empowers them.",
                        "Elves are most active during the day.",
                        "When shooting a bow, there's a 25% two arrows are shot at once.",
                        "Elves may have less health than other races, but they are also fast.",
                        "They can avoid face-to-face combat by running and using a bow.",
                        "Other than that they feel at home in neutral temperature biomes.",
                        "Elves are capable of sitting on any animal."
                };
    }

    @Override
    public void passiveBoost(Player p) {

        Location loc = p.getLocation();
        WeatherUtil.BiomeType biome = WeatherUtil.getBiomeType(loc);
        MGPlayer mgp = Races.getMGPlayer(p);

        if(mgp.hasAbility(AbilityType.NATURALIST) && ((mgp.getAbilityLevel(AbilityType.NATURALIST) > 1 && PlayerUtil.isInWater(p)) || (mgp.getAbilityLevel(AbilityType.NATURALIST) > 2 && PlayerUtil.isInRain(p))))
        {
            if(p.isDead())return;
            EffectUtil.playParticle(p, Effect.HEART);
            double max = p.getMaxHealth() - p.getHealth();
            if(max >= 1)
            {
                p.setHealth(p.getHealth() + 1);
            }
        }

        if(mgp.hasAbility(AbilityType.FORESTFRIEND) && mgp.getAbilityLevel(AbilityType.FORESTFRIEND) > 2 && biome == WeatherUtil.BiomeType.NEUTRAL && WeatherUtil.isDay(p.getWorld()))
        {
            EffectUtil.playParticle(p, Effect.HAPPY_VILLAGER);
            PotionUtil.updatePotion(p, PotionEffectType.SPEED, 1, 5);
            PotionUtil.updatePotion(p, PotionEffectType.JUMP, 1, 5);
        }
        if(mgp.hasAbility(AbilityType.NATURALIST))
        {
            PotionUtil.updatePotion(p, PotionEffectType.SPEED, 0, 5);
            PotionUtil.updatePotion(p, PotionEffectType.JUMP, 0, 5);
        }
    }
}
