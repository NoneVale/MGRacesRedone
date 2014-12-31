package com.minegusta.mgracesredone.util;

import com.google.common.collect.Lists;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.List;

public class Missile
{
    private static List<Missile> missiles = Lists.newArrayList();

    private Location location;
    private double xSpeed;
    private double yspeed;
    private double zspeed;
    private Effect[] effects;
    private int duration;
    private int lifeTime = 0;


    private Missile(Location location, double xSpeed, double ySpeed, double zSpeed, Effect[] effects, int duration)
    {
        this.location = location;
        this.xSpeed = xSpeed;
        this.yspeed = ySpeed;
        this.zspeed = zSpeed;
        this.effects = effects;
        this.duration = duration;
    }

    public static Missile createMissile(Location location, double xSpeed, double ySpeed, double zSpeed, Effect[] effects, int duration)
    {
        Missile missile = new Missile(location, xSpeed, ySpeed, zSpeed, effects, duration);
        missiles.add(missile);
        return missile;
    }

    public static Missile createMissile(Location location, Vector velocity, Effect[] effects, int duration)
    {
        Missile missile = new Missile(location, velocity.getX(), velocity.getY(), velocity.getZ(), effects, duration);
        missiles.add(missile);
        return missile;
    }

    public static void update()
    {
        for(Missile m : missiles)
        {
            m.updateLocation();
            for(Effect effect : m.getEffects())
            {
                EffectUtil.playParticle(m.getLocation(), effect);
            }
            m.updateLifeTime();
            if(m.outlived()) m.stop();
        }
    }

    public void stop()
    {
        missiles.remove(this);
    }

    public void updateLifeTime()
    {
        lifeTime++;
    }

    public boolean outlived()
    {
        return lifeTime >= duration;
    }

    public void updateLocation()
    {
        setLocation(getLocation().add(xSpeed, yspeed, zspeed));
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public Effect[] getEffects()
    {
        return effects;
    }

    public Location getLocation()
    {
        return location;
    }
}