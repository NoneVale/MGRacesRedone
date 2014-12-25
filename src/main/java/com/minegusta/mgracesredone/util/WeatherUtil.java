package com.minegusta.mgracesredone.util;


import org.bukkit.Location;
import org.bukkit.World;

public class WeatherUtil
{
    public enum BiomeType
    {
        HOT,WARM,NEUTRAL,COLD,ICE
    }


    public enum MoonPhase
    {
        FULL,QUARTER,HALF,NEW
    }

    public static BiomeType getBiomeType(Location l)
    {
        double temperature = l.getBlock().getTemperature();

        if(temperature>= 1.5)return BiomeType.HOT;
        if(temperature >= 1.0 && temperature < 1.5)return BiomeType.WARM;
        if(temperature >= 0.5 && temperature <=0.95)return BiomeType.NEUTRAL;
        if(temperature >= 0.2 && temperature <= 0.3)return BiomeType.COLD;
        if(temperature <= 0.15)return BiomeType.ICE;
        return BiomeType.NEUTRAL;
    }
    public static boolean isRaining(World w)
    {
        return w.hasStorm();
    }

    public static boolean isFullMoon(World w)
    {
        return (w.getFullTime()/24000) % 8 == 0;
    }

    public static boolean isDay(World w)
    {
        long time = w.getTime();
        return time < 12300 || time > 23850;
    }

    public static boolean isNight(World w)
    {
        return !isDay(w);
    }

    public static MoonPhase getMoonPhase(World w)
    {
        int day = (int) w.getFullTime()/24000;
        MoonPhase phase;

        switch (day)
        {
            case 1: phase = MoonPhase.QUARTER;
                break;
            case 2: phase = MoonPhase.HALF;
                break;
            case 3: phase = MoonPhase.QUARTER;
                break;
            case 4: phase = MoonPhase.NEW;
                break;
            case 5: phase = MoonPhase.QUARTER;
                break;
            case 6: phase = MoonPhase.HALF;
                break;
            case 7: phase = MoonPhase.QUARTER;
                break;
            case 8: phase = MoonPhase.FULL;
                break;
            default: phase = MoonPhase.NEW;
                break;
        }
        return phase;
    }
}