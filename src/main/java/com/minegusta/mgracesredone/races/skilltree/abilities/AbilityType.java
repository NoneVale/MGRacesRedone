package com.minegusta.mgracesredone.races.skilltree.abilities;

import com.minegusta.mgracesredone.races.RaceType;
import com.minegusta.mgracesredone.races.skilltree.abilities.perks.*;

import java.util.List;

public enum AbilityType
{
    TREEHUGGING(new TreeHugging()),
    ANIMALRIDER(new AnimalRider()),
    NATURALIST(new Naturalist()),
    FRUITFANATIC(new FruitFanatic()),
    RANGER(new Ranger());

    private IAbility ability;

    private AbilityType(IAbility ability)
    {
        this.ability = ability;
    }

    public IAbility getAbility()
    {
        return ability;
    }

    public String getName()
    {
        return ability.getName();
    }

    public List<RaceType> getRaces()
    {
        return ability.getRaces();
    }

    public int getCost(int level)
    {
        return ability.getPrice(level);
    }

    public int getID()
    {
        return ability.getID();
    }

    public int getMaxLevel()
    {
        return ability.getMaxLevel();
    }

    public void run(int level)
    {
        ability.run(level);
    }
}